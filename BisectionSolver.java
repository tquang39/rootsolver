package hus.oop.rootsolver;

public class BisectionSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public BisectionSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    @Override
    public double solve(MyPolynomial polynomial, double lower, double upper) {
        double a = lower;
        double b = upper;
        int iterations = 0;

        while (iterations < maxIterations) {
            double c = (a + b) / 2;
            double fc = polynomial.evaluate(c);
            
            // Check if we found a root
            if (Math.abs(fc) < tolerance) {
                return c;
            }
            
            // Check convergence
            if (Math.abs(b - a) < tolerance) {
                return c;
            }
            
            // Update interval
            double fa = polynomial.evaluate(a);
            if (fa * fc < 0) {
                b = c;
            } else {
                a = c;
            }
            
            iterations++;
        }
        
        // Return the midpoint of the final interval
        return (a + b) / 2;
    }
}
