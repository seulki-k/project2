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

    static public void MainCommand() {
        //첫 페이지 출력
        printMain();
        //메뉴 페이지 출력
        Menu.mainMenu();
    }

    public static void printMain() {
        //테스트 자료 true, false에 캘린더 변화 확인
        // 미수행 : 빨강,날짜가 지난 미수행 : 회색 ,수행 : 파랑색
        TodoListDate toDate = new TodoListDate(2024, 7, 2);
        TodoListItem testTodo = new TodoListItem("Test", (toDate), "test", true);
        todoItems.add(testTodo);
        TodoListDate toDate2 = new TodoListDate(2024, 7, 1);
        TodoListItem testTodo2 = new TodoListItem("Test2", (toDate2), "test2", false);
        todoItems.add(testTodo2);
        TodoListDate toDate3 = new TodoListDate(2024, 7, 13);
        TodoListItem testTodo3 = new TodoListItem("Test3", (toDate3), "test3", true);
        todoItems.add(testTodo3);
        TodoListItem testTodo4 = new TodoListItem("Test4", (toDate3), "test4", false);
        todoItems.add(testTodo4);


        calanders.setTodoCalander(todoItems);

        LocalDate currentDate = LocalDate.now();

        // 연도와 월 출력
        int titleYear = currentDate.getYear();
        int titleMonth = currentDate.getMonthValue();
        calanders.setCalendar(titleYear, titleMonth);

        System.out.println(Ansi.YELLOW + "\nTodoList 실행.....");
        System.out.println("Enter Key를 입력하면 프로그램이 시작됩니다." + Ansi.RESET);
        String a = scanner.nextLine();
    }


}

