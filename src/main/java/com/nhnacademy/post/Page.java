package com.nhnacademy.post;

import java.util.List;

public class Page<T extends Post> {
    private final List<T> pageList;
    private int currentPageNumber;   // 현재 페이지 번호
    private int pageSize;            // 1페이지 당 게시물 개수

    public Page(List<T> pageList, int currentPageNumber, int pageSize) {
        this.pageList = pageList;
        this.currentPageNumber =currentPageNumber;
        this.pageSize = pageSize;
    }

    public int getCurrentPageNumber(){
        return this.currentPageNumber;
    }

    public int getPageSize(){
        return this.pageSize;
    }

    public int getTotalPageCount(){ // 총 페이지 수
        return getTotalCount() / pageSize +
            (getTotalCount() % pageSize > 0 ? 1 : 0);
    }

    public int getTotalCount() {  // 총 게시물 수
        return this.pageList.size();
    }

    public List<T> getList(){
        int startIndex = getStartIndex();
        int endIndex = getEndIndex(); //11 - 10 = -9
        return this.pageList.subList(startIndex, endIndex);
    }

    private int getStartIndex() {
        return (currentPageNumber - 1) * 10;
    }

    private int getEndIndex() {
        int endIndex = getTotalCount() - 10 * currentPageNumber;
        if(endIndex < 0){
            return getTotalCount();
        }
        return 10 * currentPageNumber;
    }
}
