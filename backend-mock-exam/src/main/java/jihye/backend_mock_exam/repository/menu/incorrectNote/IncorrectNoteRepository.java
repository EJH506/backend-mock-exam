package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncorrectNoteRepository {

    // 주제 목록 조회 (항목이 있는 주제만)
    List<Subject> findAllSubjectsWithItem(Long userId);

    // 사용자의 오답노트 속 나만의 문제 갯수
    int countOfMyQuestionInIncorrectNote(Long userId);

    // 문항 ID로 오답노트 저장 유무 조회
    IncorrectNote findIncorrectNoteById(Long userId, Long questionId);

    // 주제, 난이도, 페이지 선택에 따른 사용자의 오답노트 목록
    List<IncorrectNote> findIncorrectList(Long userId, Long subjectId, int level, String searchKeyword, int offset, int pageSize);

    // 사용자의 오답노트 총 개수 (페이징을 제외하고, 필터는 전부 적용)
    int findIncorrectTotalCount(Long userId, Long subjectId, int level, String searchKeyword);

    // 오답노트에서 문항 삭제
    void deleteQuestionFromIncorrectNote(Long userId, Long questionId, boolean isMyQuestion);

    // 오답노트에 문항 추가
    void insertQuestionFromIncorrectNote(Long userId, Long questionId, boolean isMyQuestion);
}
