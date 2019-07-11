package pl.coderslab.model;

public class MainPageResult {


    private String title;
    private String author;
    private String updated;
    private int exId;

    public MainPageResult() {
    }

    public MainPageResult(String title, String author, String updated, int exId) {
        this.title = title;
        this.author = author;
        this.updated = updated;
        this.exId = exId;
    }

    public int getExId() {
        return exId;
    }

    public void setExId(int exId) {
        this.exId = exId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
