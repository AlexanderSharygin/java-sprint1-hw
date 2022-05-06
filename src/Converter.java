import java.math.BigDecimal;
import java.math.RoundingMode;

public class Converter {
    public static double convertStepsToDistance(int stepsCount) {
        return new BigDecimal(stepsCount * 0.75 / 1000).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static double convertStepsToKiloCalories(int stepsCount) {
        return new BigDecimal(stepsCount * 50 / 1000).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
