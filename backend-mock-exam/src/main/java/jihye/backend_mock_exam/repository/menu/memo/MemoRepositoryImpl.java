package jihye.backend_mock_exam.repository.menu.memo;

import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepository {

    private final MemoMapper memoMapper;

    // 메모 목록 조회
    @Override
    public List<Memo> findAllMemoById(Long userId, String searchKeyword, int offset, int pageSize) {
        return memoMapper.findAllMemoById(userId, searchKeyword, offset, pageSize);
    }

    // 메모 목록 조회 전체 수 조회
    @Override
    public int findTotalMemoCountByUser(Long userId, String searchKeyword) {
        return memoMapper.findTotalMemoCountByUser(userId, searchKeyword);
    }

    // 아이디로 메모 조회
    @Override
    public Memo findMemoById(Long memoId) {
        return memoMapper.findMemoById(memoId);
    }

    // 선택한 메모 삭제
    @Override
    public List<Long> deleteMemoList(List<Long> memosId) {
        memoMapper.deleteMemoList(memosId);
        return memosId;
    }

    // 메모 등록
    @Override
    public Memo memoInsert(Memo memo) {
        memoMapper.saveMemo(memo);
        return memo;
    }

    // 메모 수정
    @Override
    public void memoUpdate(MemoEditDto dto) {
        memoMapper.memoUpdate(dto);
    }
}
