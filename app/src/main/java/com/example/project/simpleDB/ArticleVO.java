package com.example.project.simpleDB;

public class ArticleVO {
    private int articleNo;
    private String subject;
    private String description;
    private String author;

    public ArticleVO(int articleNo, String subject, String description, String author) {
        this.articleNo = articleNo;
        this.subject = subject;
        this.description = description;
        this.author = author;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
