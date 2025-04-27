import java.util.*;

public class cheatingTest {

    public static double[] distribution(int n, double p) {
        double[] res = new double[n + 1];
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be between 0 and 1.");
        }
        // Use normal appx if possible otherwise binomial
        if (n*p > 5 && n*(1-p) > 5) {
            for (int i = 0; i <= n; i++) {
                double mean = n * p;
                double sd = Math.sqrt(n * p * (1 - p));
                res[i] = normal_PDF(i, mean, sd);
            }
        } else {
            for (int i = 0; i <= n; i++) {
                res[i] = binomial_PMF(n, p, i);
            }
        }
        // Reverse array so higher head count is first
        return res;
    }

    public static double normal_PDF(double x, double mean, double sd) {
        // normal PDF function
        return (1 / (sd * Math.sqrt(2 * Math.PI))) * Math.exp(-0.5 * Math.pow((x - mean) / sd, 2));
    }

    public static double binomial_PMF(int n, double p, int k) {
        // binomial PMf function
        return (factorial(n) / (factorial(n - k) * factorial(k))) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    public static double factorial(int n) {
        // recursive factorial
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static double cumulative_sum(double[] arr, int n) {
        // sum sum sum sum sum
        double sum = 0;
        for (int i = n + 1; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void simulate(double false_positive, double false_negative, double cheat_chance, double fair_chance) {
        int trials = 5;
        System.out.println("Calculating...");
        while (true) {
            //Create the two distributions for cheaters and fair players 
            double[] fair_dist = distribution(trials, fair_chance);
            double[] unfair_dist = distribution(trials, cheat_chance);

            for (int i = 0; i <= trials; i++) {
                // find the cumulative sum of the fair and unfair distributions to see who would be accused
                double falsePos_rate = cumulative_sum(fair_dist, i);
                double catch_rate = cumulative_sum(unfair_dist, i);

                // want to accuse LESS than the false positive rate and accuse MORE than the desired cheater rate
                if (falsePos_rate <= false_positive && catch_rate >= false_negative) {
                    System.out.println("\nAccusation criteria: more than " + i + " successes in " + trials + " trials.");
                    System.out.println("False accusation rate: " + String.format("%.3f", falsePos_rate));
                    System.out.println("Caught cheater rate: " + String.format("%.3f", catch_rate));
                    return;
                }
            }
            trials++;
        }
    }

    public static void accept_input() {
        // Accept input from the user for the simulation parameters
        Scanner scanner = new Scanner(System.in);
        System.out.println("Maximum proportion of false accusations? (0-1): ");
        double false_positive = scanner.nextDouble();
        System.out.println("Minimum proportion of cheaters caught? (0-1): ");
        double false_negative = scanner.nextDouble();
        System.out.println("Enter the fair player's probability of success (0-1): ");
        double fair_odds = scanner.nextDouble();
        System.out.println("Enter the cheater's probability of success (0-1): ");
        double cheat_odds = scanner.nextDouble();
        simulate(false_positive, false_negative, cheat_odds, fair_odds);
    }

    public static void main(String[] args) {
        accept_input();
        Scanner scanner = new Scanner(System.in);
        System.out.println("type 'restart' to restart or enter to quit");
        String input = scanner.nextLine();
        while (input.equalsIgnoreCase("restart")) {
            accept_input();
            System.out.println("type 'restart' to restart or enter to quit");
            input = scanner.nextLine();
        }
        scanner.close();
    }
}