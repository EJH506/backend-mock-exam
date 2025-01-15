package jihye.backend_mock_exam.service.menu.incorrectNote;

import jihye.backend_mock_exam.controller.menu.ExamConst;
import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectItem;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import jihye.backend_mock_exam.repository.menu.incorrectNote.IncorrectNoteRepository;
import jihye.backend_mock_exam.service.menu.exam.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncorrectNoteService {

    private final ExamService examService;
    private final IncorrectNoteRepository incorrectNoteRepository;

    // 주제 목록 조회
    public List<Subject> findAllSubjects() {
        return examService.findAllSubjects();
    }

    // 주제별 난이도 목록 조회
    public List<Integer> levelListOfSubject(String subjectName) {
        return examService.levelListOfSubject(subjectName);
    }

    // 주제, 난이도 선택에 따른 오답노트 목록
    public List<IncorrectItem> incorrectList(Long userId, String subjectName, String level) {
        QuestionFilter questionFilter = incorrectQuestionFilterConvert(subjectName, level);
        List<IncorrectNote> incorrectList = incorrectNoteRepository.findIncorrectList(userId, questionFilter.getSubjectId(), questionFilter.getLevelInt());

        List<Long> questionsId = new ArrayList<>();
        for (IncorrectNote incorrectNote : incorrectList) {
            questionsId.add(incorrectNote.getQuestionId());
        }

        // 오답노트 목록의 question id에서 문제 정보 추출
        List<Question> questions = examService.findFilteredHistoryQuestions(questionsId);

        List<IncorrectItem> incorrectItemList = new ArrayList<>();

        for (Question question : questions) {
            IncorrectItem item = new IncorrectItem();
            item.setQuestion(question);

            List<Answer> answers = examService.shuffledAnswerListByQuestion(question.getQuestionId());
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


    // 매개변수로 사용될 subject와 level의 값을 통합인지 아닌지에 따라 적절히 변환
    private QuestionFilter incorrectQuestionFilterConvert(String subjectName, String level) {

        Long subjectId = 0L;
        int levelInt = 0;

        // 통합 문제가 아닐 시
        if (subjectName != null && !(ExamConst.SUBJECT_ALL.equals(subjectName))) {
            subjectId = examService.findSubjectByName(subjectName).getSubjectId();
        }

        // 전체 레벨이 아닐 시
        if (level != null && !(ExamConst.LEVEL_ALL.equals(level))) {
            levelInt = Integer.parseInt(level);
        }

        return new QuestionFilter(subjectId, levelInt);
    }

    public static class QuestionFilter {

        private Long subjectId;
        private int levelInt;

        public QuestionFilter(Long subjectId, int levelInt) {
            this.subjectId = subjectId;
            this.levelInt = levelInt;
        }

        public Long getSubjectId() {
            return subjectId;
        }

        public int getLevelInt() {
            return levelInt;
        }
    }

}
