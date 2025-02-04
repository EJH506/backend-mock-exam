package jihye.backend_mock_exam.service.menu.incorrectNote;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.service.Page;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncorrectNoteService {

    private final IncorrectNoteRepository incorrectNoteRepository;
    private final CommonService commonService;

    // 주제 목록 조회 (항목이 있는 주제만)
    public List<Subject> findAllSubjectsWithItem(Long userId) {
        List<Subject> allSubjectsWithItem = incorrectNoteRepository.findAllSubjectsWithItem(userId);
        if (incorrectNoteRepository.countOfMyQuestionInIncorrectNote(userId) > 0) {
            allSubjectsWithItem.add(new Subject(null, ExamConst.SUBJECT_MYQUESTIONS));
        }
        return allSubjectsWithItem;
    }

    // 주제별 난이도 목록 조회
    public List<Integer> levelListOfSubject(Long userId, String subjectName) {
        return commonService.levelListOfSubject(userId, subjectName);
    }

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    public List<Integer> levelListOfSubjectInIncorrectNoteWithItem(Long userId, String subjectName) {
        boolean isMyQuestion = ExamConst.SUBJECT_MYQUESTIONS.equals(subjectName);
        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, null);
        return incorrectNoteRepository.levelListOfSubjectInIncorrectNoteWithItem(userId, questionFilter.getSubjectId(), isMyQuestion);
    }

    // 주제, 난이도, 페이지 선택에 따른 사용자의 오답노트 목록
    @Transactional
    public Page<IncorrectItem> incorrectList(Long userId, String subjectName, String level, String searchKeyword, int page) {

        int pagePerItem = 5;                   // 페이지당 아이템 수
        int blockPerPage = 5;                  // 블럭당 페이지 수
        int offset = (page - 1) * pagePerItem; // limit 시작위치

        QuestionFilter questionFilter = commonService.questionFilterConvert(subjectName, level);
        log.info("subjectId={}", questionFilter.getSubjectId());
        List<IncorrectNote> incorrectList = incorrectNoteRepository.findIncorrectList(userId, questionFilter.getSubjectId(), questionFilter.getLevelInt(), searchKeyword, offset, pagePerItem);

        int totalCount = incorrectNoteRepository.findIncorrectTotalCount(userId, questionFilter.getSubjectId(), questionFilter.getLevelInt(), searchKeyword);

        List<Long> questionsId = new ArrayList<>();
        List<Boolean> isMyQuestion = new ArrayList<>();
        for (IncorrectNote incorrectNote : incorrectList) {
            questionsId.add(incorrectNote.getQuestionId());
            isMyQuestion.add(incorrectNote.isMyQuestion());
        }

        // 오답노트 목록의 question id에서 문제 정보 추출
        List<? extends Questions> questions = commonService.findFilteredHistoryQuestions(questionsId, isMyQuestion);

        List<IncorrectItem> incorrectItemList = new ArrayList<>();

        for (Questions question : questions) {
            IncorrectItem item = new IncorrectItem();
            item.setQuestion(question);

            List<Answer> answers = commonService.shuffledAnswerListByQuestion(question, false);
            item.setAnswers(answers);

            for (Answer answer : answers) {
                if (answer.isCorrect()) {
                    item.setCorrectAnswer(answer);
                }
            }

            item.setSubjectId(question.getSubjectId());
            item.setLevel(question.getLevel());
            item.setSaved(true);
            item.setMyQuestion(question instanceof MyQuestion);

            incorrectItemList.add(item);
        }

        return new Page<>(incorrectItemList, page, totalCount, pagePerItem, blockPerPage);
    }

    // 오답노트에 문항 저장해제
    public Long unsaveIncorrectNote(Long userId, Long questionId, boolean isMyQuestion) {
        incorrectNoteRepository.deleteQuestionFromIncorrectNote(userId, questionId, isMyQuestion);
        return questionId;
    }

    // 오답노트에 문항 저장
    public Long saveIncorrectNote(Long userId, Long questionId, boolean isMyQuestion) {
        incorrectNoteRepository.insertQuestionFromIncorrectNote(userId, questionId, isMyQuestion);
        return questionId;
    }

    // 오답노트에 오답 문항 전체 저장
    public List<Long> saveIncorrectAll(Long userId, List<saveIncorrectAllDto.WrongQuestion> questions) {

        List<Long> savedQuestionsId = new ArrayList<>();

        for (saveIncorrectAllDto.WrongQuestion question : questions) {
            // 저장 되어있지 않은 것만
            if (!question.getIsSaved()) {
                incorrectNoteRepository.insertQuestionFromIncorrectNote(userId, question.getQuestionId(), question.getIsMyQuestion());
                savedQuestionsId.add(question.getQuestionId());
            }
        }

        return savedQuestionsId;
    }
}
