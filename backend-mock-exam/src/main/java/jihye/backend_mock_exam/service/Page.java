package jihye.backend_mock_exam.service;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private List<T> content;
    private int currentPage;
    private int totalPages;
    private int currentBlock;
    private int totalCount;

    int pagePerItem;
    int blockPerPage;

    public Page(List<T> content, int currentPage, int totalPages, int currentBlock, int totalCount) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.currentBlock = currentBlock;
        this.totalCount = totalCount;

        this.pagePerItem = 5;
        this.blockPerPage = 5;
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
