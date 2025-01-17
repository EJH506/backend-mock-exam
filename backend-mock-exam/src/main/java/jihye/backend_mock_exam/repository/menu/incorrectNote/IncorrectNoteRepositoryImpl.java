package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncorrectNoteRepositoryImpl implements IncorrectNoteRepository {

    private final IncorrectNoteMapper incorrectNoteMapper;

    // 주제, 난이도 선택에 따른 오답노트 목록
    @Override
    public List<IncorrectNote> findIncorrectList(Long userId, Long subjectId, int level, String searchKeyword) {
        return incorrectNoteMapper.findIncorrectList(userId, subjectId, level, searchKeyword);
    }

    // 오답노트에서 문항 삭제
    @Override
    public void deleteQuestionFromIncorrectNote(Long userId, Long questionId) {
        incorrectNoteMapper.deleteQuestionFromIncorrectNote(userId, questionId);
    }

    // 오답노트에 문항 추가
    @Override
    public void insertQuestionFromIncorrectNote(Long userId, Long questionId) {
        incorrectNoteMapper.insertQuestionFromIncorrectNote(userId, questionId);
    }
}
