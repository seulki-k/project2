package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoListDate;
import project2.vo.TodoListItem;

import java.util.Calendar;

public class TodoListAdd {
    public static void printAdd() {
        int year, month, day;
        // year,month,day 합해서 TodoItem의 Date

        String title; //제목
        String content; //내용

        while (true) {
            try {
                System.out.println(Ansi.GREEN + "====== 등록 ======" + Ansi.RESET);
                year = Prompt.inputInt2(1900, 2100, "작성할 연도: ");

                month = Prompt.inputInt2(0, 12, "작성할 월: ");

                TodoListCommand.calanders.setCalendar(year, month);
                System.out.println(" ");

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, 1);
                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                day = Prompt.inputInt2(1, lastDay, "작성할 일:");

                TodoListDate toDate = new TodoListDate(year, month, day);

                    title = Prompt.input("작성할 제목(종료 : 0):");
                    if ("0".equals(title)) {
                        System.out.println("작성을 종료합니다.");
                        return;
                    }

                    content = Prompt.input("작성할 내용(종료 : 0):");
                    if ("0".equals(content)) {
                        System.out.println("작성을 종료합니다.");
                        return;
                    }

                    System.out.println("등록했습니다.");

                TodoListItem todo = new TodoListItem(title, toDate, content, false);
                TodoListCommand.todoItems.add(todo);
                break;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
            }
        }
    }
}
