package org.co.connecthub.payload;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Integer TotalNumberOfElements;
    private Boolean isLastPage;

    @Override
    public String toString() {
        return "PageResponse{" +
                "content=" + content +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", TotalNumberOfElements=" + TotalNumberOfElements +
                ", isLastPage=" + isLastPage +
                '}';
    }
}
