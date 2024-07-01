package org.example.seulki;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoListCommand {

    static ArrayList<TodoItem> todoItems = new ArrayList<>();

    static public void MainCommand(){
        TodoCalander calanders = new TodoCalander();

        int year;
        int month;
        int day;
        String title; //제목
        String content; //내용
        boolean completed = false;

        Scanner scanner = new Scanner(System.in);

        System.out.print("작성할 연도 : ");
        year = scanner.nextInt();
        System.out.print("작성할 월 : ");
        month = scanner.nextInt();

        calanders.setCalendar(year,month);
        System.out.println(" ");

        System.out.print("작성할 일 : ");
        day = scanner.nextInt();
        scanner.nextLine();

        TodoDate toDate = new TodoDate(year,month,day);

        System.out.print("작성할 제목 : ");
        title = scanner.nextLine();

        System.out.print("작성할 내용 : ");
        content = scanner.nextLine();

        System.out.print("완료 여부(Y/N) : ");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y")){
            completed = true;
        } else if (answer.equalsIgnoreCase("N")) {
            completed = false;
        }

        TodoItem todo = new TodoItem(title,toDate,content,completed);
        todoItems.add(todo);

        printAllTodoList(todoItems);

    }

    public  static void printAllTodoList(ArrayList<TodoItem> todoItem){
        for (TodoItem todo : todoItem){
            System.out.println("제목 : " + todo.title);
            System.out.println("날짜 : "+ todo.date.year +"."+ todo.date.month +"."+ todo.date.day);
        }

    }
}
