import java.util.List;
import java.util.Scanner;


public class Main {
    private static StepTracker stepTracker;
    private static Scanner scanner;

    public static void main(String[] args) {
        int defaultStepsTarget = 10000;
        stepTracker = new StepTracker(defaultStepsTarget);
        scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                int monthNumber = selectMonthNumber();
                int dayNumber = selectDayNumber();
                printDailyData(monthNumber, dayNumber);
                System.out.println("Введите новое количество пройденных шагов:");
                int stepCount = scanner.nextInt();
                setDailyData(monthNumber, dayNumber, stepCount);
            } else if (command == 2) {
                int monthNumber = selectMonthNumber();
                printStatistic(monthNumber);
            } else if (command == 3) {
                System.out.println("Текущая цель: " + stepTracker.getStepTargetCount() + " шагов в день.");
                System.out.println("Введите новое количество шагов (значение должно быть положительным):");
                int stepTargetCount = scanner.nextInt();
                setNewTarget(stepTargetCount);
            } else if (command == 4) {
                System.out.println("Программа завершена.");
                scanner.close();
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("4 - Выйти из приложения");
    }

    public static void printErrorMessage() {
        System.out.println("Введённое значение не может быть отрицательным. Повторите ввод.");
    }

    public static void printLongestSeries(List<Integer> longestSessionDays) {
        if (longestSessionDays.size() < 2) {
            System.out.println("Нет ни одной серрии в этом месяце");
        } else {
            System.out.println("Длинна вашей лушей серии - " + longestSessionDays.size() + " дней. " +
                    "Это были дни с: " + (longestSessionDays.get(0) + 1) +
                    " по " + (longestSessionDays.get(longestSessionDays.size() - 1) + 1));
        }
    }

    public static void printStatistic(int monthNumber) {
        MonthData monthData = stepTracker.monthData(monthNumber);
        int[] stepsPerDays = monthData.getAllDaysData();
        System.out.println("Статистика пройденных шагов по дням месяца " + monthNumber + ":");
        for (int i = 0; i < stepsPerDays.length; i++) {
            System.out.println(i + 1 + " День: " + stepsPerDays[i]);
        }
        int monthlySteps = monthData.getSumOfSteps();
        System.out.println("Общее количество шагов за месяц: " + monthlySteps);
        System.out.println("Среднее количество шагов за месяц: " + monthData.getAvgOfSteps());
        System.out.println("Пройденная дистанция за месяц: " + Converter.convertStepsToKilometers(monthlySteps, 0.75));
        System.out.println("Сожжено килокаллорий за месяц: " + Converter.convertStepsToKiloCalories(monthlySteps, 50));
        if (monthlySteps > 0) {
            System.out.println("Максимальное количество шагов за месяц: " + monthData.getMaxDailySteps()[1] +
                    " пройдено в день " + monthData.getMaxDailySteps()[0]);
            printLongestSeries(monthData.getLongestSeries());
        } else {
            System.out.println("Вы не прошли ни шага в этом месяце");
        }
    }

    public static void setNewTarget(int stepTargetCount) {
        if (stepTargetCount < 0) {
            printErrorMessage();
        } else {
            stepTracker.setStepTargetCount(stepTargetCount);
            System.out.println("Новая цель: " + stepTracker.getStepTargetCount() + " шагов в день!");
        }
    }

    public static void printDailyData(int monthNumber, int dayNumber) {
        System.out.println("Вы прошли в день " + dayNumber + " месяца " + monthNumber + ": " +
                stepTracker.monthData(monthNumber).getDailyData(dayNumber - 1) + " шагов.");
    }

    public static void setDailyData(int monthNumber, int dayNumber, int stepCount) {
        if (stepCount < 0) {
            printErrorMessage();
        } else {
            stepTracker.monthData(monthNumber).setDailyData(dayNumber - 1, stepCount);
            System.out.println("Количество шагов изменено на: " +
                    stepTracker.monthData(monthNumber).getDailyData(dayNumber - 1) + " шагов.");
        }
    }

    public static int selectMonthNumber() {
        System.out.println("Выберите месяц (0-11):");
        return scanner.nextInt();
    }

    public static int selectDayNumber() {
        System.out.println("Выберите день (1-30):");
        return scanner.nextInt();
    }
}
