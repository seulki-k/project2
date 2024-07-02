package project2.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import project2.util.Prompt;
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

    static void deleteView(ArrayList<TodoListItem> todoItems) {
        LocalDate currentDate = LocalDate.now();

        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일
        int count = 1;
        while (true) {

            for (TodoListItem todoListItem : todoItems) {
                String completed = todoListItem.isCompleted() ? Ansi.BLUE + "수행" + Ansi.RESET :
                        (todoListItem.getDate().getYear() < titleYear) || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() < titleMonth)
                                || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() == titleMonth && todoListItem.getDate().getDay() < tileDay)
                                ? Ansi.GRAY + "미수행/기간만료" + Ansi.RESET : Ansi.RED + "미수행" + Ansi.RESET;

                System.out.println(count + ". 제목 : " + todoListItem.getTitle() + "  날짜:" + todoListItem.getDate().getYear() + " . " + todoListItem.getDate().getMonth()
                        + " . " + todoListItem.getDate().getDay() + "  " + completed);
                count++;
            }
            count =1;
            int deleteNo = Prompt.inputInt("\n삭제할 연도(종료 : 0) : ");
            if (deleteNo == 0) {
                System.out.println(" 종료");
                return;
            }
            if ((deleteNo > todoItems.size() - 1)) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            todoItems.remove(deleteNo - 1);
        }

    }

    public static void printAdd() {
        int year, month, day;
        // year,month,day 합해서 TodoItem의 Date
        String title; //제목
        String content; //내용


        try {
            year = Prompt.inputInt("작성할 연도: ");
            month = Prompt.inputInt("작성할 월: ");

            calanders.setCalendar(year, month);
            System.out.println(" ");

            day = Prompt.inputInt("작성할 일:");

            TodoListDate toDate = new TodoListDate(year, month, day);

            title = Prompt.input("작성할 제목:");
            content = Prompt.input("작성할 내용:");

            System.out.println("등록했습니다.");

            TodoListItem todo = new TodoListItem(title, toDate, content, false);
            todoItems.add(todo);
        } catch (InputMismatchException e){
            System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
            scanner.nextLine();
        }
    }

    public static void printList(ArrayList<TodoListItem> todoItem) {
        LocalDate currentDate = LocalDate.now();

        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일

        System.out.println("====== 목록 ======");
        System.out.println("1. 수행");
        System.out.println("2. 미수행");
        System.out.println("0. 종료");
        System.out.println("==================");
        while (true) {
            int command = Prompt.inputInt("\n확인할 목록(종료 : 0) : ");
            switch (command) {
                case 1:
                    for (TodoListItem todo : todoItem) {
                        if (todo.isCompleted()) {
                            System.out.println(Ansi.BLUE + "제목 : " + todo.getTitle() + "  날짜 : "
                                    + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                        }
                    }
                    continue;
                case 2:
                    for (TodoListItem todo : todoItem) {
                        if (!todo.isCompleted() && todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay)) {
                            System.out.println(Ansi.GRAY + "제목 : " + todo.getTitle() + "  날짜 : "
                                    + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                        }
                    }
                    for (TodoListItem todo : todoItem) {
                        if (!todo.isCompleted() && !(todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
                            System.out.println(Ansi.RED + "제목 : " + todo.getTitle() + "  날짜 : "
                                    + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                        }
                    }
                    continue;
                case 0:
                    System.out.println("조회를 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 값입니다.");
                    continue;
            }
        }

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
        TodoListItem testTodo4 = new TodoListItem("Test3", (toDate3), "test3", false);
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


    public static void printView(ArrayList<TodoListItem> todoItem) { //조회 기능

        int year, month, day;


        while (true) {
            boolean check = true;
            try {
                year = Prompt.inputInt("\n확인할 연도(종료 : 0) : ");
                if (year == 0) {
                    System.out.println("조회를 종료합니다.");
                    return;
                }
                month = Prompt.inputInt("확인할 월(종료 : 0) : ");
                if (month == 0) {
                    System.out.println("조회를 종료합니다.");
                    return;
                }
                calanders.setCalendar(year, month);

                day = Prompt.inputInt("\n확인할 날짜(종료 : 0) : ");
                for (TodoListItem todo : todoItem) {
                    LocalDate currentDate = LocalDate.now();

                    int titleYear = currentDate.getYear(); //년
                    int titleMonth = currentDate.getMonthValue(); ///월
                    int tileDay = currentDate.getDayOfMonth(); //일

                    if (todo.getDate().getDay() == day && todo.getDate().getYear() == year && todo.getDate().getMonth() == month) {
                        System.out.println("\n" + Ansi.GREEN + "==================================================" + Ansi.RESET);
                        System.out.print(Ansi.BOLD + "제목 : " + todo.getTitle() + Ansi.RESET);
                        System.out.print("  /  날짜 : " + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay());
                        String a = (todo.isCompleted() == true) ? Ansi.BLUE + "  수행" + Ansi.RESET :
                                (year < titleYear || (year == titleYear && month < titleMonth) || (year == titleYear && month == titleMonth && day < tileDay))
                                        ? Ansi.GRAY + "  미수행/기간만료" + Ansi.RESET : Ansi.RED + "  미수행" + Ansi.RESET;
                        System.out.println(a);
                        System.out.println(Ansi.GREEN + "==================================================" + Ansi.RESET);
                        System.out.println(todo.getContent());
                        check = false;

                    } else if (day == 0) {
                        System.out.println("조회를 종료합니다.");
                        return;
                    }
                }
                if (check) {
                    System.out.println("\n입력한 날짜에는 계획되어 있는 일이 없습니다.");
                }

            } catch (NumberFormatException e) {
                System.out.println("숫자 값을 입력하세요.");
            }
        }
    }
}

