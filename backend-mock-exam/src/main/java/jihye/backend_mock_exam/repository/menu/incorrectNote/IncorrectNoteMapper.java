package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncorrectNoteMapper {

    // 주제 목록 조회 (항목이 있는 주제만)
    List<Subject> findAllSubjectsWithItem(Long userId);

    // 사용자의 오답노트 속 나만의 문제 갯수
    int countOfMyQuestionInIncorrectNote(Long userId);

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    List<Integer> levelListOfSubjectInIncorrectNoteWithItem(@Param("userId") Long userId, @Param("subjectId") Long subjectId, @Param("isMyQuestion") boolean isMyQuestion);

    // 문항 ID로 오답노트 조회
    IncorrectNote findIncorrectNoteById(@Param("userId") Long userId, @Param("questionId") Long questionId, @Param("isMyQuestion") boolean isMyQuestion);

    // 주제, 난이도, 페이지 선택에 따른 사용자의 오답노트 목록
    List<IncorrectNote> findIncorrectList(@Param("userId") Long userId, @Param("subjectId") Long subjectId, @Param("level") int level, @Param("searchKeyword") String searchKeyword, @Param("offset") int offset, @Param("pageSize") int pageSize);

    // 사용자의 오답노트 총 개수
    int findIncorrectTotalCount(@Param("userId") Long userId, @Param("subjectId") Long subjectId, @Param("level") int level, @Param("searchKeyword") String searchKeyword);

    // 오답노트에서 문항 삭제
    void deleteQuestionFromIncorrectNote(@Param("userId") Long userId, @Param("questionId") Long questionId, @Param("isMyQuestion") boolean isMyQuestion);

    // 오답노트에 문항 추가
    void insertQuestionFromIncorrectNote(@Param("userId") Long userId, @Param("questionId") Long questionId, @Param("isMyQuestion") boolean isMyQuestion);

}
