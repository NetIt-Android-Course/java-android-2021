package com.teo.a53_multipletypesrecviewdemo;

public class Book implements AdapterData {

    private final String name;
    private final String author;
    private final int totalPageCount;
    private int pagesReadCount;
    private int lastPageReadIndex;

    public Book(String name, String author, int totalPageCount) {
        this.name = name;
        this.author = author;
        this.totalPageCount = totalPageCount;
    }

    public void setPagesReadCount(int pagesReadCount) {
        this.pagesReadCount = pagesReadCount;
    }

    public void setLastPageReadIndex(int lastPageReadIndex) {
        this.lastPageReadIndex = lastPageReadIndex;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getPagesReadCount() {
        return pagesReadCount;
    }

    public int getLastPageReadIndex() {
        return lastPageReadIndex;
    }
}
