package project2.vo;

import project2.vo.TodoListDate;
import project2.util.Prompt;
public class TodoListItem {

    String title; // 제목
    TodoListDate date; //날짜
    String content; //내용
    boolean completed; //완료 여부

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
        int checkline = content.length()/30;
        String answer = "";
        for (int i = 0; i < checkline; i++) {
            answer += content.substring(i*30,(i+1)*30) + "\n";
        }
        answer += content.substring(content.length()- (content.length()%30));
        return answer;
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
