package jihye.backend_mock_exam.repository.menu.memo;

import jihye.backend_mock_exam.domain.memo.Memo;
import jihye.backend_mock_exam.service.menu.memo.dto.MemoEditDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemoMapper {

    // 메모 목록 조회
    List<Memo> findAllMemoById(@Param("userId") Long userId, @Param("searchKeyword") String searchKeyword, @Param("offset") int offset, @Param("pageSize") int pageSize);

    // 메모 목록 조회 전체 수 조회
    int findTotalMemoCountByUser(@Param("userId") Long userId, @Param("searchKeyword") String searchKeyword);

    // 아이디로 메모 조회
    Memo findMemoById(Long memoId);

    // 선택한 메모 삭제
    void deleteMemoList(List<Long> memosId);

    // 메모 등록
    void saveMemo(Memo memo);

    // 메모 수정
    void memoUpdate(MemoEditDto dto);
}
