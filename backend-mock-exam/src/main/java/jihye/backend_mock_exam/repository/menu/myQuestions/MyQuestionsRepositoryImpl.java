package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.service.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyQuestionsRepositoryImpl implements MyQuestionsRepository {

    private final MyQuestionsMapper myQuestionsMapper;

    // 나만의 문제 목록 조회 (페이징)
    @Override
    public List<Question> findMyQuestions(Long userId, int level, String searchKeyword, int page, int pagePerItem) {
        return myQuestionsMapper.findMyQuestions(userId, level, searchKeyword, page, pagePerItem);
    }

    // 사용자의 나만의 문제 전체 갯수 조회
    @Override
    public int myQuestionsTotalCount(Long userId, int level, String searchKeyword) {
        return myQuestionsMapper.myQuestionsTotalCount(userId, level, searchKeyword);
    }

    // 나만의 문제에 존재하는 레벨 목록 조회
    @Override
    public List<Integer> findLevelsOfMyQuestion(Long userId) {
        return myQuestionsMapper.findLevelsOfMyQuestion(userId);
    }

    // 선택한 문항 삭제
    @Override
    public void deleteMyQuestionList(List<Long> deleteQuestionsId) {
        myQuestionsMapper.deleteMyQuestionList(deleteQuestionsId);
    }

    // 문항 ID로 문항 조회
    @Override
    public Question findMyQuestionById(Long questionId) {
        return myQuestionsMapper.findMyQuestionById(questionId);
    }

    // 문항에 속한 보기 조회
    @Override
    public List<Answer> findMyAnswersByQuestion(Long questionId) {
        return myQuestionsMapper.findMyAnswersByQuestion(questionId);
    }

    // 문항의 질문 등록
    @Override
    public MyQuestion insertQuestionOfMyQuestion(MyQuestion question) {
        myQuestionsMapper.insertQuestionOfMyQuestion(question);
        return question;
    }

    // 문항의 보기 등록
    @Override
    public List<Answer> insertAnswersOfMyQuestion(List<Answer> answers) {
        myQuestionsMapper.insertAnswersOfMyQuestion(answers);
        return answers;
    }

    // 문항의 질문 업데이트
    @Override
    public MyQuestion updateQuestionOfMyQuestion(MyQuestion question) {
        myQuestionsMapper.updateQuestionOfMyQuestion(question);
        return question;
    }

    // 문항의 보기 업데이트
    @Override
    public Answer updateAnswersOfMyQuestion(Answer answer) {
        myQuestionsMapper.updateAnswersOfMyQuestion(answer);
        return answer;
    }

    // 난이도에 속한 문항 삭제
    @Override
    public void deleteMyQuestionOfDeletedLevel(Long userId, Integer level) {
        myQuestionsMapper.deleteMyQuestionOfDeletedLevel(userId, level);
    }

    // 난이도 삭제
    @Override
    public void deleteMyQuestionsLevel(Long userId, Integer level) {
        myQuestionsMapper.deleteMyQuestionsLevel(userId, level);
    }
}
