package jihye.backend_mock_exam.service.menu.memo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.menu.memo.MemoRepository;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoEditDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoSelectDeleteDto;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    
    // 메모 목록 조회
    public Page<Memo> memoList(Long userId, HttpServletRequest request, String searchKeyword, int page) {

        int pagePerItem = 5;                   // 페이지당 아이템 수
        int blockPerPage = 5;                  // 블럭당 페이지 수
        int offset = (page - 1) * pagePerItem; // limit 시작위치

        List<Memo> memos = null;
        int totalCount = 0;
        // 회원일 경우
        if (userId > 0) {
            memos = memoRepository.findAllMemoById(userId, searchKeyword, offset, pagePerItem);
            totalCount = memoRepository.findTotalMemoCountByUser(userId, searchKeyword);
        // 비회원일 경우
        } else {
            List<Memo> totalMemo = request.getSession().getAttribute("guestMemo") != null ? (List<Memo>) request.getSession().getAttribute("guestMemo") : new ArrayList<Memo>();
            int toIndex = Math.min(offset + pagePerItem, totalMemo.size()); // 5보다 적은 경우를 처리하기 위해 Math.min 사용
            memos = new ArrayList<>(totalMemo.subList(offset, toIndex));
            totalCount = totalMemo.size();
        }

        log.info("memos={}", memos);

        Page<Memo> memoList = new Page<>(memos, page, totalCount, pagePerItem, blockPerPage);
        log.info("memoList={}", memoList);
        return memoList;
    }

    // 메모 보기
    public Memo memoDetail(Long memoId, Role user, HttpServletRequest request) {

        // 회원일 경우
        if (user instanceof User) {
            return memoRepository.findMemoById(memoId);
        } else {
            List<Memo> memos = (List<Memo>) request.getSession().getAttribute("guestMemo");
            for (Memo memo : memos) {
                if (memo.getMemoId().equals(memoId)) {
                    return memo;
                }
            }
            return null;
        }
    }

    // 선택한 메모 삭제
    public List<Long> memoSelectDelete(MemoSelectDeleteDto dto, Role user, HttpServletRequest request) {

        // 회원일 경우
        if (user instanceof User) {
            memoRepository.deleteMemoList(dto.getDeleteMemosId());

        // 비회원일 경우
        } else {
            List<Long> deleteMemosId = dto.getDeleteMemosId();
            List<Memo> memos = (List<Memo>) request.getSession(false).getAttribute("guestMemo");

            memos.removeIf(memo -> deleteMemosId.contains(memo.getMemoId()));
        }
        return dto.getDeleteMemosId();
    }

    // 메모 등록
    public Memo saveMemo(MemoWriteDto dto, HttpServletRequest request) {

        // 회원일 경우
        if (dto.getUserId() > 0) {
            return memoRepository.memoInsert(new Memo(dto.getUserId(), dto.getMemoText()));

        // 비회원일 경우
        } else {
            HttpSession session = request.getSession();
            List<Memo> guestMemo = session.getAttribute("guestMemo") != null ? (List<Memo>) session.getAttribute("guestMemo") : new ArrayList<>();
            Memo memo = new Memo(dto.getUserId(), dto.getMemoText());

            // memoId 이전것과 비교해 +1씩 증가
            List<Memo> prevMemo = (List<Memo>) session.getAttribute("guestMemo");
            Long memoId = prevMemo == null ? 1L : prevMemo.get(0).getMemoId() + 1;

            memo.setMemoId(memoId);

            // 생성시각 생성
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            memo.setCreatedAt(LocalDateTime.now().format(formatter));

            guestMemo.add(0, memo);
            session.setAttribute("guestMemo", guestMemo);
            return memo;
        }
    }
    
    // 메모 수정
    public void editMemo(MemoEditDto dto, Role user, HttpServletRequest request) {

        // 회원일 경우
        if (user instanceof User) {
            memoRepository.memoUpdate(dto);
            
        // 비회원일 경우
        } else {
            Memo memo = memoDetail(dto.getMemoId(), user, request);
            memo.setMemoText(dto.getMemoText());

            // 수정시각 생성
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            memo.setUpdateAt(LocalDateTime.now().format(formatter));
        }
    }
}
