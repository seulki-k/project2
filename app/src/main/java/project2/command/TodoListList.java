package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoListItem;

import java.time.LocalDate;
import java.util.ArrayList;

public class TodoListList {
    public static void printList(ArrayList<TodoListItem> todoItem) {
        LocalDate currentDate = LocalDate.now();

        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일


        while (true) {
            System.out.println(Ansi.GREEN + "====== 목록 ======" + Ansi.RESET);
            System.out.println("1. 수행");
            System.out.println("2. 미수행");
            System.out.println("0. 종료");
            System.out.println(Ansi.GREEN + "==================" + Ansi.RESET);
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
                        if (!todo.isCompleted() && (todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
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
}
