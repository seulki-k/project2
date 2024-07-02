package org.example.seulki;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListCommand {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<TodoItem> todoItems = new ArrayList<>();
    static TodoCalander calanders = new TodoCalander();
    static public void MainCommand(){
        printMain();

        int year;
        int month;
        int day;
        // year,month,day 합해서 TodoItem의 Date
        String title; //제목
        String content; //내용
        boolean completed = false; //계획한 일 완료 여부

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
    public static void printMain(){
        //테스트 자료 true, false에 캘린더 변화 확인
        TodoDate toDate = new TodoDate(2024,7,1);
        TodoItem testTodo = new TodoItem("Test",(toDate),"test",true);
        todoItems.add(testTodo);
        calanders.setTodoCalander(todoItems);
        TodoDate toDate2 = new TodoDate(2024,7,3);
        TodoItem testTodo2 = new TodoItem("Test2",(toDate2),"test2",false);
        todoItems.add(testTodo2);

        LocalDate currentDate = LocalDate.now();

        // 연도와 월 출력
        int titleYear = currentDate.getYear();
        int titleMonth = currentDate.getMonthValue();
        calanders.setCalendar(titleYear,titleMonth);
        System.out.println();
        System.out.println();
        System.out.println(Ansi.YELLOW +  "TodoList 실행.....");
        System.out.println("Enter Key를 입력하면 프로그램이 시작됩니다." + Ansi.RESET);
        String a = scanner.nextLine();
    }

    public  static void printAllTodoList(ArrayList<TodoItem> todoItem){

        for (TodoItem todo : todoItem){
            System.out.println("제목 : " + todo.title);
            System.out.println("날짜 : "+ todo.date.year +"."+ todo.date.month +"."+ todo.date.day);
        }


    }
}