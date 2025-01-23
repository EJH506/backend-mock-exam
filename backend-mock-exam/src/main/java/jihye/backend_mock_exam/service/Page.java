package jihye.backend_mock_exam.service;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private List<T> content;
    private int currentPage;
    private int totalCount;
    private int pagePerItem;
    private int blockPerPage;

    private int totalPages;
    private int currentBlock;
    private int offset;


    public Page(List<T> content, int currentPage, int totalCount, int pagePerItem, int blockPerPage) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pagePerItem = pagePerItem;
        this.blockPerPage = blockPerPage;

        this.totalPages = (int) Math.ceil((double) totalCount / pagePerItem);
        this.currentBlock = (int) Math.floor((double) (currentPage - 1) / blockPerPage);
        this.offset = (currentPage - 1) * pagePerItem;
    }

    public boolean hasNextBlock() {
        return (currentBlock + 1) * 5 < totalPages;
    }

    public boolean hasPrevBlock() {
        return currentBlock > 0;
    }

    public int nextBlockStartPage() {
        return (currentBlock + 1) * 5 + 1;
    }

    public int prevBlockStartPage() {
        return (currentBlock - 1) * 5 + 1;
    }

    public int getEndPageOfBlock() {
        int endPage = (currentBlock + 1) * 5;
        return Math.min(endPage, totalPages);
    }
}
