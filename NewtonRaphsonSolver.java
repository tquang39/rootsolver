package hus.oop.rootsolver;

public class NewtonRaphsonSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public NewtonRaphsonSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    @Override
    public double solve(MyPolynomial polynomial, double lower, double upper) {
        // Start with the midpoint of the interval
        double x = (lower + upper) / 2;
        int iterations = 0;
        
        // Get the derivative of the polynomial
        MyPolynomial derivative = polynomial.derivative();
        
        while (iterations < maxIterations) {
            double fx = polynomial.evaluate(x);
            
            // Check if we found a root
            if (Math.abs(fx) < tolerance) {
                return x;
            }
            
            double fpx = derivative.evaluate(x);
            
            // Avoid division by zero
            if (Math.abs(fpx) < tolerance) {
                // If derivative is too small, try a different starting point
                x = (x + upper) / 2;
                continue;
            }
            
            // Newton-Raphson formula
            double xNew = x - fx / fpx;
            
            // Check convergence
            if (Math.abs(xNew - x) < tolerance) {
                return xNew;
            }
            
            x = xNew;
            iterations++;
        }
        
        return x;
    }
}
