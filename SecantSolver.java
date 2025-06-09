package hus.oop.rootsolver;

public class SecantSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public SecantSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    @Override
    public double solve(MyPolynomial polynomial, double lower, double upper) {
        double x0 = lower;
        double x1 = upper;
        double x2;
        int iterations = 0;

        while (iterations < maxIterations) {
            double fx0 = polynomial.evaluate(x0);
            double fx1 = polynomial.evaluate(x1);
            
            // Check if we found a root
            if (Math.abs(fx1) < tolerance) {
                return x1;
            }
            
            // Secant method formula
            x2 = x1 - fx1 * (x1 - x0) / (fx1 - fx0);
            
            // Check convergence
            if (Math.abs(x2 - x1) < tolerance) {
                return x2;
            }
            
            x0 = x1;
            x1 = x2;
            iterations++;
        }
        
        // Return the best approximation if max iterations reached
        return x1;
    }
}
