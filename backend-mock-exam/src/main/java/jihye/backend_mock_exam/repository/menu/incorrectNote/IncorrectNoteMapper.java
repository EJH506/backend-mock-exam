package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncorrectNoteMapper {

    // 주제, 난이도 선택에 따른 오답노트 목록
    List<IncorrectNote> findIncorrectList(@Param("userId") Long userId, @Param("subjectId") Long subjectId, @Param("level") int level);

    // 오답노트에서 문항 삭제
    void deleteQuestionFromIncorrectNote(@Param("userId") Long userId, @Param("questionId") Long questionId);

    // 오답노트에 문항 추가
    void insertQuestionFromIncorrectNote(@Param("userId") Long userId, @Param("questionId") Long questionId);
}
