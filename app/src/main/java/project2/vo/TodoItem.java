package project2.vo;


import java.util.Objects;

public class TodoItem {

    String title; // 제목
    TodoListDate date; //날짜
    String content; //내용
    private boolean completed; //완료 여부

    public TodoItem(String title, TodoListDate date, String content, boolean completed) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.completed = completed;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TodoItem todoItem = (TodoItem) object;
        return completed == todoItem.completed && Objects.equals(title, todoItem.title) && Objects.equals(date, todoItem.date) && Objects.equals(content, todoItem.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, content, completed);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", completed=" + completed +
                '}';
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
