package hus.oop.rootsolver;


import java.util.Random;

public class TestRootSolver {
    private static final double TOLERANCE = 1e-6;
    private static final int MAX_ITERATIONS = 100;
    private static final Random random = new Random();
    private final PolynomialRootFinder rootFinder;

    public TestRootSolver(PolynomialRootFinder rootFinder) {
        this.rootFinder = rootFinder;
    }

    public static void main(String[] args) {
        TestRootSolver tester = new TestRootSolver(new PolynomialRootFinder());
        tester.testMyArrayPolynomial();
        tester.testMyListPolynomial();
        tester.testMyLinkedListPolynomial();
    }

    private void testPolynomialOperations(MyPolynomial polynomial) {
        System.out.println("Original polynomial: " + polynomial);
        
        // Test adding coefficients
        double newCoeff = random.nextDouble() * 10 - 5;
        polynomial.addAtStart(newCoeff);
        System.out.println("After adding at start: " + polynomial);
        
        newCoeff = random.nextDouble() * 10 - 5;
        polynomial.addAtEnd(newCoeff);
        System.out.println("After adding at end: " + polynomial);
        
        // Test setting coefficient
        newCoeff = random.nextDouble() * 10 - 5;
        polynomial.set(0, newCoeff);
        System.out.println("After setting coefficient at index 0: " + polynomial);
        
        // Test polynomial arithmetic
        MyPolynomial another = createRandomPolynomial(polynomial.getClass(), polynomial.degree());
        System.out.println("Another polynomial: " + another);
        
        MyPolynomial sum = polynomial.plus(another);
        System.out.println("Sum: " + sum);
        
        MyPolynomial diff = polynomial.minus(another);
        System.out.println("Difference: " + diff);
        
        MyPolynomial product = polynomial.multiply(another);
        System.out.println("Product: " + product);
        
        // Test evaluation
        double x = random.nextDouble() * 2;
        System.out.println("Value at x = " + x + ": " + polynomial.evaluate(x));
    }

    private MyPolynomial createRandomPolynomial(Class<? extends MyPolynomial> type, int degree) {
        MyPolynomial polynomial;
        try {
            polynomial = type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create polynomial instance", e);
        }
        
        for (int i = 0; i <= degree; i++) {
            polynomial.addAtEnd(random.nextDouble() * 10 - 5);
        }
        return polynomial;
    }

    private void findAndPrintRoots(MyPolynomial polynomial, double lower, double upper) {
        System.out.println("\nFinding roots in interval [" + lower + ", " + upper + "]:");
        
        // Test Bisection method
        rootFinder.setRootSolver(new BisectionSolver(TOLERANCE, MAX_ITERATIONS));
        try {
            double root = rootFinder.solve(lower, upper);
            System.out.println("Bisection root: " + root);
            System.out.println("f(" + root + ") = " + polynomial.evaluate(root));
        } catch (Exception e) {
            System.out.println("Bisection failed: " + e.getMessage());
        }
        
        // Test Secant method
        rootFinder.setRootSolver(new SecantSolver(TOLERANCE, MAX_ITERATIONS));
        try {
            double root = rootFinder.solve(lower, upper);
            System.out.println("Secant root: " + root);
            System.out.println("f(" + root + ") = " + polynomial.evaluate(root));
        } catch (Exception e) {
            System.out.println("Secant failed: " + e.getMessage());
        }
        
        // Test Newton-Raphson method
        rootFinder.setRootSolver(new NewtonRaphsonSolver(TOLERANCE, MAX_ITERATIONS));
        try {
            double root = rootFinder.solve(lower, upper);
            System.out.println("Newton-Raphson root: " + root);
            System.out.println("f(" + root + ") = " + polynomial.evaluate(root));
        } catch (Exception e) {
            System.out.println("Newton-Raphson failed: " + e.getMessage());
        }
    }

    public void testMyArrayPolynomial() {
        System.out.println("\nTesting MyArrayPolynomial:");
        System.out.println("Testing MyArrayPolynomial operations:");
        
        // Create a random polynomial of degree 2-6
        int degree = random.nextInt(5) + 2;
        MyPolynomial polynomial = createRandomPolynomial(MyArrayPolynomial.class, degree);
        rootFinder.setPolynomial(polynomial);
        
        // Test polynomial operations
        testPolynomialOperations(polynomial);
        
        // Find an interval [a,b] where f(a)*f(b) < 0
        double a = -10;
        double b = 10;
        double fa = polynomial.evaluate(a);
        double fb = polynomial.evaluate(b);
        
        // If no root in [-10,10], try [-100,100]
        if (fa * fb > 0) {
            a = -100;
            b = 100;
        }
        
        // Find and print roots
        findAndPrintRoots(polynomial, a, b);
    }

    public void testMyListPolynomial() {
        System.out.println("\nTesting MyListPolynomial:");
        System.out.println("Testing MyListPolynomial operations:");
        
        // Create a random polynomial of degree 2-6
        int degree = random.nextInt(5) + 2;
        MyPolynomial polynomial = createRandomPolynomial(MyListPolynomial.class, degree);
        rootFinder.setPolynomial(polynomial);
        
        // Test polynomial operations
        testPolynomialOperations(polynomial);
        
        // Find an interval [a,b] where f(a)*f(b) < 0
        double a = -10;
        double b = 10;
        double fa = polynomial.evaluate(a);
        double fb = polynomial.evaluate(b);
        
        // If no root in [-10,10], try [-100,100]
        if (fa * fb > 0) {
            a = -100;
            b = 100;
        }
        
        // Find and print roots
        findAndPrintRoots(polynomial, a, b);
    }

    public void testMyLinkedListPolynomial() {
        System.out.println("\nTesting MyLinkedListPolynomial:");
        System.out.println("Testing MyLinkedListPolynomial operations:");
        
        // Create a random polynomial of degree 2-6
        int degree = random.nextInt(5) + 2;
        MyPolynomial polynomial = createRandomPolynomial(MyLinkedListPolynomial.class, degree);
        rootFinder.setPolynomial(polynomial);
        
        // Test polynomial operations
        testPolynomialOperations(polynomial);
        
        // Find an interval [a,b] where f(a)*f(b) < 0
        double a = -10;
        double b = 10;
        double fa = polynomial.evaluate(a);
        double fb = polynomial.evaluate(b);
        
        // If no root in [-10,10], try [-100,100]
        if (fa * fb > 0) {
            a = -100;
            b = 100;
        }
        
        // Find and print roots
        findAndPrintRoots(polynomial, a, b);
    }
}
