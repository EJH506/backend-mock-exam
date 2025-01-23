package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyQuestionsMapper {

    // 나만의 문제 목록 조회 (페이징)
    List<Question> findMyQuestions(@Param("userId") Long userId, @Param("level") int level, @Param("searchKeyword") String searchKeyword, @Param("offset") int offset, @Param("pagePerItem") int pagePerItem);

    // 사용자의 나만의 문제 전체 갯수 조회
    int myQuestionsTotalCount(@Param("userId") Long userId, @Param("level") int level, @Param("searchKeyword") String searchKeyword);

    // 나만의 문제에 존재하는 레벨 목록 조회
    List<Integer> findLevelsOfMyQuestion(Long userId);

    // 선택한 문항 삭제
    void deleteMyQuestionList(List<Long> myQuestionsId);
}
