package jihye.backend_mock_exam.service.menu.myQuestions;

import jakarta.servlet.http.HttpServletRequest;
import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.ExamItem;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.menu.myQuestions.MyQuestionsRepository;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.CommonService;
import jihye.backend_mock_exam.service.menu.QuestionFilter;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionSelectDeleteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyQuestionsService {

    private final MyQuestionsRepository myQuestionsRepository;
    private final CommonService commonService;

    // 나만의 문제 목록 보기
    public Page<Question> myQuestionsList(Long userId, String level, String searchKeyword, int page) {

        int pagePerItem = 5;
        int blockPerPage = 5;
        int offset = (page - 1) * pagePerItem;

        QuestionFilter questionFilter = commonService.questionFilterConvert(level);
        List<Question> myQuestions = myQuestionsRepository.findMyQuestions(userId, questionFilter.getLevelInt(), searchKeyword, offset, pagePerItem);

        int totalCount = myQuestionsRepository.myQuestionsTotalCount(userId, questionFilter.getLevelInt(), searchKeyword);

        return new Page<>(myQuestions, page, totalCount, pagePerItem, blockPerPage);
    }

    // 나만의 문제에 존재하는 레벨 목록 보기
    public List<Integer> levelListOfMyQuestion(Long userId) {
        return myQuestionsRepository.findLevelsOfMyQuestion(userId);
    }

    // 선택한 문항 삭제
    public List<Long> myQuestionSelectDelete(MyQuestionSelectDeleteDto dto) {
        myQuestionsRepository.deleteMyQuestionList(dto.getDeleteQuestionsId());
        return dto.getDeleteQuestionsId();
    }

    // 문항 조회
    public ExamItem myQuestionDetail(Long questionId) {

        Question question = myQuestionsRepository.findMyQuestionById(questionId);
        List<Answer> answers = myQuestionsRepository.findMyAnswersByQuestion(questionId);

        return new ExamItem(question, answers);
    }

    // 문제 등록
    @Transactional
    public MyQuestion addMyQuestion(MyQuestionAddDto dto) {
        MyQuestion question = new MyQuestion();
        question.setUserId(dto.getUserId());
        question.setLevel(dto.getLevel());
        question.setQuestionText(dto.getQuestionText());
        MyQuestion savedQuestion = myQuestionsRepository.insertQuestionOfMyQuestion(question);

        List<Answer> answers = new ArrayList<>();
        Answer correctAnswer = new Answer();
        correctAnswer.setQuestionId(question.getQuestionId());
        correctAnswer.setAnswerText(dto.getCorrectAnswer());
        correctAnswer.setCorrect(true);
        answers.add(correctAnswer);

        for (int i=0; i<3; i++) {
            Answer wrongAnswer = new Answer();
            wrongAnswer.setQuestionId(question.getQuestionId());
            wrongAnswer.setAnswerText(dto.getWrongAnswers().get(i));
            wrongAnswer.setCorrect(false);
            answers.add(wrongAnswer);
        }

        List<Answer> savedAnswers = myQuestionsRepository.insertAnswersOfMyQuestion(answers);

        return savedQuestion;
    }
}
