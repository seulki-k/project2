package project2.command;

import project2.util.Prompt;
import project2.vo.Ansi;

import static project2.command.TodoListCommand.todoItems;


public class Menu {
    static String[] mainmenus = {"등록", "목록", "조회", "변경", "삭제", "종료"};


    public static void mainMenu() {

        printMenu();

        while (true) {
            try {
                String command = Prompt.input("메뉴를 선택하세요:");
                if (command.equals("menu")) {
                    printMenu();
                } else {
                    int menuNo = Integer.parseInt(command);
                    if (menuNo == 6){
                        break;
                    }
                    String menuTitle = getMenuTitle(menuNo, mainmenus);
                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else {
                        processMenu(menuTitle);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }

        System.out.println("종료합니다.");
        Prompt.close();
    }


    public static void printMenu() {
        System.out.println(Ansi.GREEN + "====== 메뉴 ======" + Ansi.RESET);
        for (int i = 0; i < mainmenus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), mainmenus[i]);
        }
        System.out.println(Ansi.GREEN + "==================" + Ansi.RESET);
    }

    public static String getMenuTitle(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length ? menus[menuNo - 1] : null;
    }

    public static void processMenu(String menuTitle) {
        switch (menuTitle) {
            case "등록":
                TodoListAdd.printAdd();
                break;
            case "목록":
                TodoListList.printList(todoItems);
                break;
            case "조회":
                TodoListView.printView(todoItems);
                break;
            case "변경":
                TodoListUpdate.updateView(todoItems);
                break;
            case "삭제":
                TodoListDelete.deleteView(todoItems);
                break;

            default:
                System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
        }
        printMenu();
    }
}
