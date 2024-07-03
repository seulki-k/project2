package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoListDate;
import project2.vo.TodoListItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class TodoListUpdate {
    public static void updateView(ArrayList<TodoListItem> todoItems) {
        LocalDate currentDate = LocalDate.now();
        String title; //제목
        String content; //내용
        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일
        int count = 1;

        while (true) {
            System.out.println(Ansi.GREEN + "====== 변경 ======" + Ansi.RESET);
            for (TodoListItem todoListItem : todoItems) {
                String completed = todoListItem.isCompleted() ? Ansi.BLUE + "수행" + Ansi.RESET :
                        (todoListItem.getDate().getYear() < titleYear) || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() < titleMonth)
                                || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() == titleMonth && todoListItem.getDate().getDay() < tileDay)
                                ? Ansi.GRAY + "미수행/기간만료" + Ansi.RESET : Ansi.RED + "미수행" + Ansi.RESET;

                System.out.println(count + ". 제목 : " + todoListItem.getTitle() + "  날짜:" + todoListItem.getDate().getYear() + " . " + todoListItem.getDate().getMonth()
                        + " . " + todoListItem.getDate().getDay() + "  " + completed);
                count++;
            }
            try {
                count = 1;
                int updateNo = Prompt.inputInt("\n변경할 번호(종료 : 0) : ");
                if (updateNo == 0) {
                    System.out.println(" 종료");
                    return;
                }
                if ((updateNo > todoItems.size())) {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
                int year, month, day;
                // year,month,day 합해서 TodoItem의 Date

                year = Prompt.inputInt2(1900, 2100, "변경할 연도(변경 전:" + todoItems.get(updateNo - 1).getDate().getYear() + ") : ");

                month = Prompt.inputInt2(1, 12, "변경할 월(변경 전:" + todoItems.get(updateNo - 1).getDate().getMonth() + ") : ");

                TodoListCommand.calanders.setCalendar(year, month);
                System.out.println(" ");
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, 1);
                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                day = Prompt.inputInt2(1, lastDay, "변경할 일(변경 전 :" + todoItems.get(updateNo - 1).getDate().getDay() + ") : ");

                TodoListDate toDate = new TodoListDate(year, month, day);

                title = Prompt.input("변경할 제목(변경 전:" + todoItems.get(updateNo - 1).getTitle() + ") : ");

                content = Prompt.input("변경할 내용(변경 전:" + todoItems.get(updateNo - 1).getContent() + ") : ");

                boolean check = false;
                while (true) {
                    String complete = Prompt.input("완료 여부(Y/N):");
                    if (complete.equalsIgnoreCase("y")) {
                        check = true;
                        break;
                    }
                    if (complete.equalsIgnoreCase("n")) {
                        check = false;
                        break;
                    } else {
                        System.out.println(" Y 와 N 중에 골라주세요.");
                    }
                }
                System.out.println("변경했습니다.");

                TodoListItem todo = new TodoListItem(title, toDate, content, check);

                todoItems.set((updateNo - 1), todo);
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 시도해 주세요.\n");
            }
        }

    }
}
