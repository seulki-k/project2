package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoListItem;

import java.time.LocalDate;
import java.util.ArrayList;

public class TodoListDelete {
    static void deleteView(ArrayList<TodoListItem> todoItems) {
        LocalDate currentDate = LocalDate.now();

        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일
        int count = 1;
        while (true) {
            try {
                System.out.println(Ansi.GREEN + "====== 삭제 ======" + Ansi.RESET);
                for (TodoListItem todoListItem : todoItems) {
                    String completed = todoListItem.isCompleted() ? Ansi.BLUE + "수행" + Ansi.RESET :
                            (todoListItem.getDate().getYear() < titleYear) || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() < titleMonth)
                                    || (todoListItem.getDate().getYear() == titleYear && todoListItem.getDate().getMonth() == titleMonth && todoListItem.getDate().getDay() < tileDay)
                                    ? Ansi.GRAY + "미수행/기간만료" + Ansi.RESET : Ansi.RED + "미수행" + Ansi.RESET;

                    System.out.println(count + ". 제목 : " + todoListItem.getTitle() + "  날짜:" + todoListItem.getDate().getYear() + " . " + todoListItem.getDate().getMonth()
                            + " . " + todoListItem.getDate().getDay() + "  " + completed);
                    count++;
                }
                count = 1;
                int deleteNo = Prompt.inputInt("\n삭제할 번호(종료 : 0) : ");
                if (deleteNo == 0) {
                    System.out.println(" 종료");
                    return;
                }
                if ((deleteNo > todoItems.size())) {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
                todoItems.remove(deleteNo - 1);
            } catch (NumberFormatException e) {
                System.out.println("\n잘못된 입력입니다.\n");
                continue;
            }
        }
    }
}
