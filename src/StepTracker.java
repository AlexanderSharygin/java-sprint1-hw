public class StepTracker {
    private int stepTargetCount;
    private final MonthData[] monthsData;

    public StepTracker(int stepTargetCount) {
        this.stepTargetCount = stepTargetCount;
        monthsData = new MonthData[12];
        for (int i = 0; i < monthsData.length; i++) {
            monthsData[i] = new MonthData(stepTargetCount);
        }
    }

    public void setStepTargetCount(int value) {
        this.stepTargetCount = value;
        for (var monthData : monthsData) {
            monthData.setStepTargetCount(value);
        }
    }

    public int getStepTargetCount() {
        return stepTargetCount;
    }

    public MonthData monthData(int monthNumber) {
        return monthsData[monthNumber];
    }
}
