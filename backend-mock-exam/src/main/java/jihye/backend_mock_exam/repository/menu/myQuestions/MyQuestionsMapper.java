package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
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

    // 나만의 문제에 존재하는 레벨 목록 조회 (문항이 존재하는 것만 조회)
    List<Integer> findLevelsOfMyQuestionWithItem(Long userId);

    // 사용자의 나만의 문제 문항 수 조회
    Integer findNumberOfMyQuestion(@Param("userId") Long userId, @Param("level") int level);

    // 나만의 문제의 난이도, 문항수에 해당하는 문제 목록 조회
    List<Long> findShuffledMyQuestions(@Param("userId") Long userId, @Param("level") int level, @Param("number") int number);

    // 선택한 문항 삭제
    void deleteMyQuestionList(List<Long> deleteQuestionsId);

    // 문항 ID로 문항 조회
    Question findMyQuestionById(Long questionId);

    // 문항에 속한 보기 조회
    List<Answer> findMyAnswersByQuestion(Long questionId);

    // 문항의 질문 등록
    void insertQuestionOfMyQuestion(MyQuestion question);

    // 문항의 보기 등록
    void insertAnswersOfMyQuestion(List<Answer> answers);

    // 문항의 질문 업데이트
    void updateQuestionOfMyQuestion(MyQuestion question);

    // 문항의 보기 업데이트
    void updateAnswersOfMyQuestion(Answer answer);

    // 난이도에 속한 문항 삭제
    void deleteMyQuestionOfDeletedLevel(@Param("userId") Long userId, @Param("level") Integer level);

    // 난이도 삭제
    void deleteMyQuestionsLevel(@Param("userId") Long userId, @Param("level") Integer level);

    // 사용자의 최대 난이도 조회
    Integer findMyQuestionsMaximumLevel(Long userId);

    // 난이도 추가
    void addMyQuestionsLevel(Long userId, int level);
}
