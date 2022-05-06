import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int defaultStepsTarget = 10000;
        StepTracker stepTracker = new StepTracker(defaultStepsTarget);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                System.out.println("Выберите месяц (0-11):");
                int monthNumber = scanner.nextInt();
                System.out.println("Выберите день (1-30):");
                int dayNumber = scanner.nextInt();
                System.out.println("Вы прошли в день " + dayNumber + " месяца " + monthNumber + ": " +
                        stepTracker.monthData(monthNumber).getDailyData(dayNumber - 1) + " шагов.");
                System.out.println("Выберите новое количество пройденных шагов:");
                int stepCount = scanner.nextInt();
                if (stepCount < 0) {
                    printErrorMessage();
                } else {
                    stepTracker.monthData(monthNumber).setDailyData(dayNumber - 1, stepCount);
                    System.out.println("Количество шагов изменено на: " +
                            stepTracker.monthData(monthNumber).getDailyData(dayNumber - 1) + " шагов.");
                }
            } else if (command == 2) {
                System.out.println("Выберите месяц (0-11):");
                int monthNumber = scanner.nextInt();
                int[] stepsPerDays = stepTracker.monthData(monthNumber).getAllDaysData();
                System.out.println("Статистика пройденных шагов по дням месяца " + monthNumber + ":");
                for (int i = 0; i < stepsPerDays.length; i++) {
                    System.out.println(i + 1 + " День: " + stepsPerDays[i]);
                }
                int monthlySteps = stepTracker.monthData(monthNumber).getSumOfSteps();
                System.out.println("Общее количество шагов за месяц: " + monthlySteps);
                System.out.println("Среднее количество шагов за месяц: " + stepTracker.monthData(monthNumber).getAvgOfSteps());
                System.out.println("Пройденная дистанция за месяц: " + Converter.convertStepsToDistance(monthlySteps));
                System.out.println("Сожжено килокаллорий за месяц: " + Converter.convertStepsToKiloCalories(monthlySteps));
                if (monthlySteps > 0) {
                    System.out.println("Максимальное количество шагов за месяц: "
                            + stepTracker.monthData(monthNumber).getMaxDailySteps()[1] +
                            " пройдено в день " + +stepTracker.monthData(monthNumber).getMaxDailySteps()[0]);
                    printLongestSeries(stepTracker.monthData(monthNumber).getLongestSeries());
                } else {
                    System.out.println("Вы не прошли ни шага в этом месяце");
                }
            } else if (command == 3) {
                System.out.println("Текущая цель: " + stepTracker.getStepTargetCount() + " шагов в день.");
                System.out.println("Введите новое количество шагов (значение должно быть положительным):");
                int stepTargetCount = scanner.nextInt();
                if (stepTargetCount < 0) {
                    printErrorMessage();
                } else {
                    stepTracker.setStepTargetCount(stepTargetCount);
                    System.out.println("Новая цель: " + stepTracker.getStepTargetCount() + " шагов в день!");
                }
            } else if (command == 4) {
                System.out.println("Программа завершена.");
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
}
