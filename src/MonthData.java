import java.util.ArrayList;
import java.util.List;

public class MonthData {
    private final int[] daysData;
    private int stepTargetCount;

    public MonthData(int stepTargetCount) {
        this.daysData = new int[30];
        this.stepTargetCount = stepTargetCount;
    }

    public void setStepTargetCount(int value) {
        stepTargetCount = value;
    }

    public int getDailyData(int dayIndex) {
        return daysData[dayIndex];
    }

    public int[] getAllDaysData() {
        return daysData;
    }

    public void setDailyData(int dayIndex, int value) {
        this.daysData[dayIndex] = value;
    }

    public int getSumOfSteps() {
        int sum = 0;
        for (var dailyValue : daysData) {
            sum += dailyValue;
        }
        return sum;
    }

    public int getAvgOfSteps() {
        int sum = getSumOfSteps();
        return sum / daysData.length;
    }

    public int[] getMaxDailySteps() {
        int maxValue = 0;
        int dayNumber = 1;
        for (int i = 0; i < daysData.length; i++) {
            if (maxValue < daysData[i]) {
                maxValue = daysData[i];
                dayNumber = i + 1;
            }
        }
        return new int[]{dayNumber, maxValue};
    }

    public List<Integer> getLongestSeries() {
        List<Integer> longestSeriesDays = new ArrayList<>();
        int longestSeriesSteps = 0;
        for (int i = 0; i < daysData.length; i++) {
            List<Integer> tempSeriesDays = new ArrayList<>();
            int tempSeriesSteps = 0;
            if (daysData[i] >= stepTargetCount) {
                tempSeriesDays.add(i);
                tempSeriesSteps += daysData[i];
                for (int j = i + 1; j < daysData.length; j++) {
                    if (daysData[j] >= stepTargetCount) {
                        tempSeriesDays.add(j);
                        tempSeriesSteps += daysData[j];
                    }
                    if (daysData[j] < stepTargetCount || j == daysData.length - 1) {
                        i = j;
                        if (longestSeriesDays.size() < tempSeriesDays.size() && tempSeriesDays.size() > 1) {
                            longestSeriesDays.clear();
                            longestSeriesDays.addAll(tempSeriesDays);
                            longestSeriesSteps = tempSeriesSteps;
                        } else if (longestSeriesDays.size() == tempSeriesDays.size()) {
                            if (tempSeriesSteps > longestSeriesSteps) {
                                longestSeriesDays.clear();
                                longestSeriesDays.addAll(tempSeriesDays);
                                longestSeriesSteps = tempSeriesSteps;
                            }
                        }
                        tempSeriesDays.clear();
                        break;
                    }
                }
            }
        }
        return longestSeriesDays;
    }
}
