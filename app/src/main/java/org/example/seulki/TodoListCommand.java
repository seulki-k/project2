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
        LocalDate currentDate = LocalDate.now();

        // 연도와 월 출력
        int titleYear = currentDate.getYear();
        int titleMonth = currentDate.getMonthValue();
        calanders.setCalendar(titleYear,titleMonth);
        System.out.println();
        System.out.println();
        System.out.println(Ansi.YELLOW +  "TodoList 실행....." + Ansi.RESET);
        System.out.println("Enter Key를 입력하면 프로그램이 시작됩니다.");
        String a = scanner.nextLine();
    }

    public  static void printAllTodoList(ArrayList<TodoItem> todoItem){ //메인 화면 출력
        for (TodoItem todo : todoItem){
            System.out.println("제목 : " + todo.title);
            System.out.println("날짜 : "+ todo.date.year +"."+ todo.date.month +"."+ todo.date.day);
        }


    }
}