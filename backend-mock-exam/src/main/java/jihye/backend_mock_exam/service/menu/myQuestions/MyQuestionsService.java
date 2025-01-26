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
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionEditDto;
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
        Answer correctAnswer = null;
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                correctAnswer = answer;
                answers.remove(correctAnswer);
                break;
            }
        }

        return new ExamItem(question, answers, correctAnswer);
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
        Answer correctAnswer = new Answer(question.getQuestionId(), dto.getCorrectAnswer().getAnswerText(), true);
        answers.add(correctAnswer);

        Answer wrongAnswer1 = new Answer(question.getQuestionId(), dto.getWrongAnswer1().getAnswerText(), false);
        Answer wrongAnswer2 = new Answer(question.getQuestionId(), dto.getWrongAnswer2().getAnswerText(), false);
        Answer wrongAnswer3 = new Answer(question.getQuestionId(), dto.getWrongAnswer3().getAnswerText(), false);
        answers.add(wrongAnswer1);
        answers.add(wrongAnswer2);
        answers.add(wrongAnswer3);

        myQuestionsRepository.insertAnswersOfMyQuestion(answers);

        return savedQuestion;
    }

    // 문제 수정
    @Transactional
    public MyQuestion editMyQuestion(MyQuestionEditDto dto) {

        MyQuestion question = new MyQuestion();
        question.setQuestionId(dto.getQuestionId());
        question.setLevel(dto.getLevel());
        question.setQuestionText(dto.getQuestionText());
        MyQuestion editedQuestion = myQuestionsRepository.updateQuestionOfMyQuestion(question);

        Answer correctAnswer = new Answer(dto.getCorrectAnswer().getAnswerId(), dto.getCorrectAnswer().getAnswerText(), true);
        Answer wrongAnswer1 = new Answer(dto.getWrongAnswer1().getAnswerId(), dto.getWrongAnswer1().getAnswerText(), false);
        Answer wrongAnswer2 = new Answer(dto.getWrongAnswer2().getAnswerId(), dto.getWrongAnswer2().getAnswerText(), false);
        Answer wrongAnswer3 = new Answer(dto.getWrongAnswer3().getAnswerId(), dto.getWrongAnswer3().getAnswerText(), false);


        myQuestionsRepository.updateAnswersOfMyQuestion(correctAnswer);
        myQuestionsRepository.updateAnswersOfMyQuestion(wrongAnswer1);
        myQuestionsRepository.updateAnswersOfMyQuestion(wrongAnswer2);
        myQuestionsRepository.updateAnswersOfMyQuestion(wrongAnswer3);

        return editedQuestion;
    }

    // 난이도 삭제
    @Transactional
    public Integer removeLevel(Long userId, Integer level) {

        // 난이도에 속한 문항 삭제
        myQuestionsRepository.deleteMyQuestionOfDeletedLevel(userId, level);
        // 난이도 삭제
        myQuestionsRepository.deleteMyQuestionsLevel(userId, level);

        return level;
    }
}
