public class Converter {
    public static double convertStepsToKilometers(int stepsCount, double stepLength) {
        double distance = stepsCount * stepLength / 1000;
        distance = Math.round(distance * 100);
        return distance / 100;
    }

    public static double convertStepsToKiloCalories(int stepsCount, double caloriesPerStep) {
        double KiloCalories = stepsCount * caloriesPerStep / 1000;
        KiloCalories = Math.round(KiloCalories * 100);
        return KiloCalories / 100;
    }
}
