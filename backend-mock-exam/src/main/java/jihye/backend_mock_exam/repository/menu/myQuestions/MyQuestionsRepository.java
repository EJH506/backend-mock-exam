package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.service.Page;

import java.util.List;

public interface MyQuestionsRepository {

    // 나만의 문제 목록 조회 (페이징)
    List<Question> findMyQuestions(Long userId, int level, String searchKeyword, int offset, int pagePerItem);

    // 사용자의 나만의 문제 전체 갯수 조회
    int myQuestionsTotalCount(Long userId, int level, String searchKeyword);

    // 나만의 문제에 존재하는 레벨 목록 조회
    List<Integer> findLevelsOfMyQuestion(Long userId);
    
    // 선택한 문항 삭제
    void deleteMyQuestionList(List<Long> myQuestionsId);
}
