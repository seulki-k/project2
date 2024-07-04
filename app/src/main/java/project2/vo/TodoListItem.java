package project2.vo;

import project2.vo.TodoListDate;
import project2.util.Prompt;

import java.util.Objects;

public class TodoListItem {

    String title; // 제목
    TodoListDate date; //날짜
    String content; //내용
    boolean completed; //완료 여부

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TodoListItem that = (TodoListItem) object;
        return completed == that.completed && Objects.equals(title, that.title) && Objects.equals(date, that.date) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, content, completed);
    }

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
