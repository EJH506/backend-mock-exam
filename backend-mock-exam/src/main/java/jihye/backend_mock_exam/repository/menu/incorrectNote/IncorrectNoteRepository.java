package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;

import java.util.List;

public interface IncorrectNoteRepository {

    // 주제, 난이도, 검색어 선택에 따른 오답노트 목록
    List<IncorrectNote> findIncorrectList(Long userId, Long subjectId, int level, String searchKeyword);

    // 오답노트에서 문항 삭제
    void deleteQuestionFromIncorrectNote(Long userId, Long questionId);

    // 오답노트에 문항 추가
    void insertQuestionFromIncorrectNote(Long userId, Long questionId);
}
