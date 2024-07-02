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
        MainMenu();

    }

    public static void MainMenu() {
        while (true) {

            System.out.println("====== 메뉴 ======");
            System.out.println("1. 등록");
            System.out.println("2. 목록");
            System.out.println("3. 조회");
            System.out.println("4. 종료");
            System.out.println("==================");
            System.out.print("메뉴를 선택하세요: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        printAdd();
                        break;
                    case 2:
                        printList(todoItems);
                        break;
                    case 3:
                        printView(todoItems);
                        break;
                    case 4:
                        System.out.println("종료.");
                        return;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
    }

        public static void printAdd () {
            int year, month, day;
            // year,month,day 합해서 TodoItem의 Date

            String title; //제목
            String content; //내용
            boolean completed = false; //계획한 일 완료 여부

            System.out.print("작성할 연도 : ");
            year = scanner.nextInt();
            System.out.print("작성할 월 : ");
            month = scanner.nextInt();

            calanders.setCalendar(year, month);
            System.out.println(" ");

            System.out.print("작성할 일 : ");
            day = scanner.nextInt();
            scanner.nextLine();

            TodoListDate toDate = new TodoListDate(year, month, day);

            System.out.print("작성할 제목 : ");
            title = scanner.nextLine();

            System.out.print("작성할 내용 : ");
            content = scanner.nextLine();

            System.out.print("완료 여부(Y/N) : ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                completed = true;
            } else if (answer.equalsIgnoreCase("N")) {
                completed = false;
            }
            System.out.println("등록했습니다.");

            TodoListItem todo = new TodoListItem(title, toDate, content, completed);
            todoItems.add(todo);

        }

        public static void  printList (ArrayList < TodoListItem > todoItem) {
            for (TodoListItem todo : todoItem) {
                System.out.println("제목 : " + todo.getTitle());
                System.out.println("날짜 : " + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay());
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

    public static void printAllTodoList(ArrayList<TodoListItem> todoItem) { //전체 리스트 출력

        for (TodoListItem todo : todoItem) {
            System.out.println("제목 : " + todo.getTitle());
            System.out.println("날짜 : " + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay());
        }
    }

        public static void printView (ArrayList < TodoListItem > todoItem) { //조회 기능

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
                        String a = (todo.isCompleted() == true) ? Ansi.BLUE +  "  수행" + Ansi.RESET :
                                (year < titleYear || (year == titleYear && month < titleMonth) || (year == titleYear && month == titleMonth&& day <tileDay))
                                ? Ansi.GRAY + "  미수행/기간만료" + Ansi.RESET : Ansi.RED +  "  미수행"  + Ansi.RESET ;
                        System.out.println(a);
                        System.out.println(Ansi.GREEN + "==================================================" + Ansi.RESET);
                        System.out.println(todo.getContent());
                        check = false;

                    } else if (day == 0) {
                        System.out.println("조회를 종료합니다.");
                        return;
                    }
                }
                if(check){
                    System.out.println("\n입력한 날짜에는 계획되어 있는 일이 없습니다.");
                }

                } catch (NumberFormatException e) {
                    System.out.println("숫자 값을 입력하세요.");
                }
            }
        }
    }

