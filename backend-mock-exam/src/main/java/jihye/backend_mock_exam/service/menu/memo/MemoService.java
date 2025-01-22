package jihye.backend_mock_exam.service.menu.memo;

import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.repository.menu.memo.MemoRepository;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoEditDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    
    // 메모 목록 조회
    public Page<Memo> memoList(Long userId, String searchKeyword, int page) {

        int pagePerItem = 5;                   // 페이지당 아이템 수
        int blockPerPage = 5;                  // 블럭당 페이지 수
        int offset = (page - 1) * pagePerItem; // limit 시작위치

        List<Memo> memos = memoRepository.findAllMemoById(userId, searchKeyword, offset, pagePerItem);

        int totalCount = memoRepository.findTotalMemoCountByUser(userId, searchKeyword);
        int totalPages = (int) Math.ceil((double) totalCount / pagePerItem);
        int currentBlock = (int) Math.floor((double) (page - 1) / blockPerPage);

        return new Page<>(memos, page, totalPages, currentBlock, totalCount);
    }

    // 메모 보기
    public Memo memoDetail(Long memoId) {
        return memoRepository.findMemoById(memoId);
    }

    // 선택한 메모 삭제
    public List<Long> memoSelectDelete(MemoSelectDeleteDto dto) {
        return memoRepository.deleteMemoList(dto.getDeleteMemosId());
    }

    // 메모 등록
    public Memo saveMemo(MemoWriteDto dto) {
        return memoRepository.memoInsert(new Memo(dto.getUserId(), dto.getMemoText()));
    }
    
    // 메모 수정
    public void editMemo(MemoEditDto dto) {
        memoRepository.memoUpdate(dto);
    }
}
