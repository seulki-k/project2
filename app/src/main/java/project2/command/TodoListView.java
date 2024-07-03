package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoListItem;

import java.time.LocalDate;
import java.util.ArrayList;

public class TodoListView {
    public static void printView(ArrayList<TodoListItem> todoItem) { //조회 기능

        int year, month, day;

        while (true) {
            boolean check = true;
            System.out.println(Ansi.GREEN + "====== 조회 ======" + Ansi.RESET);
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
                TodoListCommand.calanders.setCalendar(year, month);

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
