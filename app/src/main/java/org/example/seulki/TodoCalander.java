package org.example.seulki;

import java.util.ArrayList;
import java.util.Calendar;

public class TodoCalander {
    ArrayList<TodoItem> calanderItems;

    public TodoCalander() {

    }
    public void setTodoCalander(ArrayList<TodoItem> todoItems) {
        this.calanderItems = todoItems;
    }

    public void setCalendar(int year, int month) {
        // Calendar 인스턴스 생성

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // 월은 0부터 시작하므로 month - 1

        // 해당 월의 첫 번째 날의 요일 구하기 (1: 일요일, 2: 월요일, ..., 7: 토요일)
        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 해당 월의 마지막 날 구하기
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 달력 출력
        System.out.println();
        System.out.println(Ansi.RED +"\t\t  " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " " + year + Ansi.RESET);
        System.out.println(" 일  월  화  수  목  금  토");

        // 첫 번째 날의 요일 위치에 맞춰서 공백 출력
        for (int i = 1; i < startDayOfWeek; i++) {
            System.out.print("    ");
        }


        // 날짜 출력
        for (int day = 1; day <= lastDayOfMonth; day++) {
            boolean check = true;
            for(TodoItem todo : calanderItems){
                if(todo.date.year==year && todo.date.month==month && todo.date.day ==day && todo.isCompleted()==false) {
                        System.out.printf(Ansi.RED + "%3d ", day);
                        System.out.print(Ansi.RESET);
                        check = false;
                }
                if(todo.date.year==year && todo.date.month==month && todo.date.day ==day && todo.isCompleted()==true) {
                    System.out.printf(Ansi.BLUE + "%3d ", day);
                    System.out.print(Ansi.RESET);
                    check = false;
                }
            }
            if(check){
                System.out.printf("%3d ", day);
            }
            if ((day + startDayOfWeek - 1) % 7 == 0) {
                System.out.println();
            }
        }
    }


}
