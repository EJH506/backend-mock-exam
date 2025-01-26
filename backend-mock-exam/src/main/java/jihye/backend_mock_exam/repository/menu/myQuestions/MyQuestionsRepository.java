package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;

import java.util.List;

public interface MyQuestionsRepository {

    // 나만의 문제 목록 조회 (페이징)
    List<Question> findMyQuestions(Long userId, int level, String searchKeyword, int offset, int pagePerItem);

    // 사용자의 나만의 문제 전체 갯수 조회
    int myQuestionsTotalCount(Long userId, int level, String searchKeyword);

    // 나만의 문제에 존재하는 레벨 목록 조회
    List<Integer> findLevelsOfMyQuestion(Long userId);
    
    // 선택한 문항 삭제
    void deleteMyQuestionList(List<Long> deleteQuestionsId);

    // 문항 ID로 문항 조회
    Question findMyQuestionById(Long questionId);

    // 문항에 속한 보기 조회
    List<Answer> findMyAnswersByQuestion(Long questionId);

    // 문항의 질문 등록
    MyQuestion insertQuestionOfMyQuestion(MyQuestion question);

    // 문항의 보기 등록
    List<Answer> insertAnswersOfMyQuestion(List<Answer> answers);

    // 문항의 질문 업데이트
    MyQuestion updateQuestionOfMyQuestion(MyQuestion question);

    // 문항의 보기 업데이트
    Answer updateAnswersOfMyQuestion(Answer answer);
}
