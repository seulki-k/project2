package project2.vo;

import project2.vo.TodoListDate;

public class TodoListItem {

    String title; // 제목
    TodoListDate date; //날짜
    String content; //내용
    private boolean completed; //완료 여부

    public TodoListItem(String title, TodoListDate date, String content, boolean completed) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TodoListDate getDate() {
        return date;
    }

    public void setDate(TodoListDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
