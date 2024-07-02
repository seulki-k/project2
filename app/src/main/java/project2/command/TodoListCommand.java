package project2.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import project2.vo.TodoListDate;
import project2.vo.TodoListItem;
import project2.util.TodoListCalander;
import project2.vo.Ansi;

public class TodoListCommand {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<TodoListItem> todoItems = new ArrayList<>();
    static TodoListCalander calanders = new TodoListCalander();
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

        TodoListDate toDate = new TodoListDate(year,month,day);

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

        TodoListItem todo = new TodoListItem(title,toDate,content,completed);
        todoItems.add(todo);

        printAllTodoList(todoItems);

    }
    public static void printMain(){
        //테스트 자료 true, false에 캘린더 변화 확인
        TodoListDate toDate = new TodoListDate(2024,7,1);
        TodoListItem testTodo = new TodoListItem("Test",(toDate),"test",true);
        todoItems.add(testTodo);
        calanders.setTodoCalander(todoItems);
        TodoListDate toDate2 = new TodoListDate(2024,7,3);
        TodoListItem testTodo2 = new TodoListItem("Test2",(toDate2),"test2",false);
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

    public  static void printAllTodoList(ArrayList<TodoListItem> todoItem){

        for (TodoListItem todo : todoItem){
            System.out.println("제목 : " + todo.getTitle());
            System.out.println("날짜 : "+ todo.getDate().getYear() +"."+ todo.getDate().getMonth() +"."+ todo.getDate().getDay());
        }


    }
}
