package jihye.backend_mock_exam.service.menu.incorrectNote;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectItem;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import jihye.backend_mock_exam.repository.menu.incorrectNote.IncorrectNoteRepository;
import jihye.backend_mock_exam.service.menu.CommonService;
import jihye.backend_mock_exam.service.menu.QuestionFilter;
import jihye.backend_mock_exam.service.menu.incorrectNote.dto.saveIncorrectAllDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncorrectNoteService {

    private final IncorrectNoteRepository incorrectNoteRepository;
    private final CommonService commonService;

    // 주제 목록 조회
    public List<Subject> findAllSubjects() {
        return commonService.findAllSubjects();
    }

    // 주제별 난이도 목록 조회
    public List<Integer> levelListOfSubject(String subjectName) {
        return commonService.levelListOfSubject(subjectName);
    }

    // 주제, 난이도 선택에 따른 오답노트 목록
    public List<IncorrectItem> incorrectList(Long userId, String subjectName, String level, String searchKeyword) {
        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);
        List<IncorrectNote> incorrectList = incorrectNoteRepository.findIncorrectList(userId, questionFilter.getSubjectId(), questionFilter.getLevelInt(), searchKeyword);

        List<Long> questionsId = new ArrayList<>();
        for (IncorrectNote incorrectNote : incorrectList) {
            questionsId.add(incorrectNote.getQuestionId());
        }

        // 오답노트 목록의 question id에서 문제 정보 추출
        List<Question> questions = commonService.findFilteredHistoryQuestions(questionsId);

        List<IncorrectItem> incorrectItemList = new ArrayList<>();

        for (Question question : questions) {
            IncorrectItem item = new IncorrectItem();
            item.setQuestion(question);

            List<Answer> answers = commonService.shuffledAnswerListByQuestion(question.getQuestionId());
            item.setAnswers(answers);

            for (Answer answer : answers) {
                if (answer.isCorrect()) {
                    item.setCorrectAnswer(answer);
                }
            }

            item.setSubjectId(question.getSubjectId());
            item.setLevel(question.getLevel());
            item.setSaved(true);

            incorrectItemList.add(item);
        }

        return incorrectItemList;
    }

    // 오답노트에 문항 저장해제
    public Long unsaveIncorrectNote(Long userId, Long questionId) {
        incorrectNoteRepository.deleteQuestionFromIncorrectNote(userId, questionId);
        return questionId;
    }

    // 오답노트에 문항 저장
    public Long saveIncorrectNote(Long userId, Long questionId) {
        incorrectNoteRepository.insertQuestionFromIncorrectNote(userId, questionId);
        return questionId;
    }

    // 오답노트에 오답 문항 전체 저장
    public List<Long> saveIncorrectAll(Long userId, List<saveIncorrectAllDto.wrongQuestion> questions) {

        List<Long> savedQuestionsId = new ArrayList<>();

        for (saveIncorrectAllDto.wrongQuestion question : questions) {
            // 저장 되어있지 않은 것만
            if (!question.getIsSaved()) {
                incorrectNoteRepository.insertQuestionFromIncorrectNote(userId, question.getQuestionId());
                savedQuestionsId.add(question.getQuestionId());
            }
        }

        return savedQuestionsId;
    }
}
