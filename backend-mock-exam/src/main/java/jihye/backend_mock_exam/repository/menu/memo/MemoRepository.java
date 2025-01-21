package jihye.backend_mock_exam.repository.menu.memo;

import jihye.backend_mock_exam.domain.memo.Memo;

import java.util.List;

public interface MemoRepository {

    // 메모 목록 조회 (페이징)
    List<Memo> findAllMemoById(Long userId, String searchKeyword, int offset, int pageSize);

    // 메모 목록 조회 전체 수 조회
    int findTotalMemoCountByUser(Long userId, String searchKeyword);

    // 아이디로 메모 조회
    Memo findMemoById(Long memoId);

    // 선택한 메모 삭제
    List<Long> deleteMemoList(List<Long> memosId);
}
