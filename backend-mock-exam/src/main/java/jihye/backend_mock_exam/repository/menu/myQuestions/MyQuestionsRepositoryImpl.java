package jihye.backend_mock_exam.repository.menu.myQuestions;

import jihye.backend_mock_exam.domain.exam.Question;
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
}
