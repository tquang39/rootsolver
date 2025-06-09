package hus.oop.rootsolver;

public class PolynomialRootFinder {
    private MyPolynomial polynomial;
    private RootSolver rootSolver;

    /**
     * Khởi tạo mặc định
     */
    public PolynomialRootFinder() {
        this.polynomial = null;
        this.rootSolver = new BisectionSolver(1e-6, 100); // Default solver
    }

    /**
     * Khởi tạo đa thức.
     * @param polynomial
     */
    public PolynomialRootFinder(MyPolynomial polynomial) {
        this.polynomial = polynomial;
        this.rootSolver = new BisectionSolver(1e-6, 100); // Default solver
    }

    /**
     * Khởi tạo đa thức và phương pháp tìm nghiệm.
     * @param polynomial
     * @param rootSolver
     */
    public PolynomialRootFinder(MyPolynomial polynomial, RootSolver rootSolver) {
        this.polynomial = polynomial;
        this.rootSolver = rootSolver;
    }

    public void setPolynomial(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public void setRootSolver(RootSolver rootSolver) {
        this.rootSolver = rootSolver;
    }

    /**
     * Tìm nghiệm của đa thức trong đoạn từ lower đến upper.
     * @param lower
     * @param upper
     * @return
     */
    public double solve(double lower, double upper) {
        if (polynomial == null) {
            throw new IllegalStateException("Polynomial is not set");
        }
        if (rootSolver == null) {
            throw new IllegalStateException("Root solver is not set");
        }
        if (lower >= upper) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound");
        }
        
        // Check if there's a root in the interval
        double fa = polynomial.evaluate(lower);
        double fb = polynomial.evaluate(upper);
        if (fa * fb > 0) {
            throw new IllegalArgumentException("No root in the given interval [f(a)*f(b) > 0]");
        }
        
        return rootSolver.solve(polynomial, lower, upper);
    }
}
