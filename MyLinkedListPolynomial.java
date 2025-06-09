package hus.oop.rootsolver;

public class MyLinkedListPolynomial extends MyAbstractPolynomial {
    private MyLinkedList coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedListPolynomial() {
        this.coefficients = new MyLinkedList();
    }

    @Override
    public double coefficientAt(int index) {
        if (index < 0 || index >= coefficients.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[coefficients.size()];
        for (int i = 0; i < coefficients.size(); i++) {
            result[i] = coefficients.get(i);
        }
        return result;
    }

    @Override
    public void addAtStart(double coefficient) {
        coefficients.insert(coefficient, 0);
    }

    @Override
    public void addAtEnd(double coefficient) {
        coefficients.add(coefficient);
    }

    @Override
    public void addAtPosition(int index, double coefficient) {
        if (index < 0 || index > coefficients.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        coefficients.insert(coefficient, index);
    }

    @Override
    public void set(int index, double coefficient) {
        if (index < 0 || index >= coefficients.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        coefficients.set(coefficient, index);
    }

    @Override
    public int degree() {
        return coefficients.size() - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial derivative() {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        for (int i = 1; i < coefficients.size(); i++) {
            result.addAtEnd(coefficients.get(i) * i);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial plus(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        
        for (int i = 0; i <= maxDegree; i++) {
            double coeff1 = (i < coefficients.size()) ? coefficients.get(i) : 0;
            double coeff2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coeff1 + coeff2);
        }
        
        return result;
    }

    @Override
    public MyLinkedListPolynomial minus(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        
        for (int i = 0; i <= maxDegree; i++) {
            double coeff1 = (i < coefficients.size()) ? coefficients.get(i) : 0;
            double coeff2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coeff1 - coeff2);
        }
        
        return result;
    }

    @Override
    public MyLinkedListPolynomial multiply(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        int resultDegree = this.degree() + another.degree();
        
        // Initialize result list with zeros
        for (int i = 0; i <= resultDegree; i++) {
            result.addAtEnd(0);
        }
        
        // Multiply polynomials
        for (int i = 0; i < coefficients.size(); i++) {
            for (int j = 0; j <= another.degree(); j++) {
                double coeff1 = coefficients.get(i);
                double coeff2 = another.coefficientAt(j);
                result.set(i + j, result.coefficientAt(i + j) + coeff1 * coeff2);
            }
        }
        
        return result;
    }
}
