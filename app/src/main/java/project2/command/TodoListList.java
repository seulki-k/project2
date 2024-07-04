package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;
import project2.vo.TodoItem;
import project2.vo.TodoListItem;
import project2.command.TodoListCommand;

import java.time.LocalDate;
import java.util.ArrayList;

public class TodoListList {
    public static void printList(ArrayList<TodoListItem> todoItem) {
        LocalDate currentDate = LocalDate.now();

        int titleYear = currentDate.getYear(); //년
        int titleMonth = currentDate.getMonthValue(); ///월
        int tileDay = currentDate.getDayOfMonth(); //일

        a:
        while (true) {
            try {
                System.out.println(Ansi.GREEN + "====== 목록 ======" + Ansi.RESET);
                System.out.println("1. 수행");
                System.out.println("2. 미수행");
                System.out.println("0. 종료");
                System.out.println(Ansi.GREEN + "==================" + Ansi.RESET);
                int count = 1;
                int command = Prompt.inputInt("\n확인할 목록(종료 : 0) : ");
                switch (command) {
                    case 1:
                        for (TodoListItem todo : todoItem) {
                            if (todo.isCompleted()) {
                                System.out.println(Ansi.BLUE + count + ". 제목 : " + todo.getTitle() + "  날짜 : "
                                        + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                                count++;
                            }
                        }
                        if(count ==1) {
                            System.out.println("수행 항목이 없습니다.");
                            continue ;
                        }
                        count = 1;
                        continue;
                    case 2:
                        for (TodoListItem todo : todoItem) {
                            if (!todo.isCompleted() && (todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                    || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
                                System.out.println(Ansi.GRAY + count + ". 제목 : " + todo.getTitle() + "  날짜 : "
                                        + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                                count++;
                            }
                        }

                        for (TodoListItem todo : todoItem) {
                            if (!todo.isCompleted() && !(todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                    || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
                                System.out.println(Ansi.RED + count + ". 제목 : " + todo.getTitle() + "  날짜 : "
                                        + todo.getDate().getYear() + "." + todo.getDate().getMonth() + "." + todo.getDate().getDay() + Ansi.RESET);
                                count++;
                            }
                        }
                        if (count ==1){
                            System.out.println("미수행 항목이 없습니다.");
                            continue ;
                        }
                        while (true) {
                            String command3 = Prompt.input("수행한 항목이 있으신가요(Y/N)?");
                            if (command3.equalsIgnoreCase("y")) {
                                break;
                            } else if (command3.equalsIgnoreCase("n")) {
                                continue a;
                            } else {
                            }
                        }
                        int command2 = Prompt.inputInt("수행한 항목(종료 : 0) : ");
                        if (command2 == 0) {
                            return;
                        }
                        int count2 = 0;
                        for (TodoListItem todos : todoItem) {
                            count = 1;
                            for (TodoListItem todo : todoItem) {
                                if (!todo.isCompleted() && (todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                        || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
                                    if (command2 == count) {
                                        TodoListItem todo2 = new TodoListItem(todo.getTitle(), todo.getDate(), todo.getContent(), true);
                                        if (todos.equals(todo)) {
                                            TodoListCommand.todoItems.set(count2, todo2);
                                            continue a;
                                        }
                                    }
                                    count++;
                                }
                            }
                            for (TodoListItem todo : todoItem) {
                                if (!todo.isCompleted() && !(todo.getDate().getYear() < titleYear || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() < titleMonth)
                                        || (todo.getDate().getYear() == titleYear && todo.getDate().getMonth() == titleMonth && todo.getDate().getDay() < tileDay))) {
                                    if (command2 == count) {
                                        TodoListItem todo2 = new TodoListItem(todo.getTitle(), todo.getDate(), todo.getContent(), true);
                                        if (todos.equals(todo)) {
                                            TodoListCommand.todoItems.set(count2, todo2);
                                            continue a;
                                        }
                                    }
                                    count++;
                                }
                            }
                            count2++;
                        }


                        continue;
                    case 0:
                        System.out.println("조회를 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 값입니다.");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n잘못된 값입니다.\n");
                continue;
            }
        }
    }
}
