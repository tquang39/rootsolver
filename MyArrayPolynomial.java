package hus.oop.rootsolver;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficents;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        this.coefficents = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public double coefficientAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return coefficents[index];
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[size];
        System.arraycopy(coefficents, 0, result, 0, size);
        return result;
    }

    @Override
    public void addAtStart(double coefficient) {
        if (size == coefficents.length) {
            allocateMore();
        }
        System.arraycopy(coefficents, 0, coefficents, 1, size);
        coefficents[0] = coefficient;
        size++;
    }

    @Override
    public void addAtEnd(double coefficient) {
        if (size == coefficents.length) {
            allocateMore();
        }
        coefficents[size] = coefficient;
        size++;
    }

    @Override
    public void addAtPosition(int index, double coefficient) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == coefficents.length) {
            allocateMore();
        }
        System.arraycopy(coefficents, index, coefficents, index + 1, size - index);
        coefficents[index] = coefficient;
        size++;
    }

    @Override
    public void set(int index, double coefficient) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        coefficents[index] = coefficient;
    }

    @Override
    public int degree() {
        return size - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += coefficents[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial derivative() {
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 1; i < size; i++) {
            result.addAtEnd(coefficents[i] * i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial another) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        
        for (int i = 0; i <= maxDegree; i++) {
            double coeff1 = (i < size) ? coefficents[i] : 0;
            double coeff2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coeff1 + coeff2);
        }
        
        return result;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial another) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        
        for (int i = 0; i <= maxDegree; i++) {
            double coeff1 = (i < size) ? coefficents[i] : 0;
            double coeff2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coeff1 - coeff2);
        }
        
        return result;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial another) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int resultDegree = this.degree() + another.degree();
        
        // Initialize result array with zeros
        for (int i = 0; i <= resultDegree; i++) {
            result.addAtEnd(0);
        }
        
        // Multiply polynomials
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= another.degree(); j++) {
                double coeff1 = coefficents[i];
                double coeff2 = another.coefficientAt(j);
                result.set(i + j, result.coefficientAt(i + j) + coeff1 * coeff2);
            }
        }
        
        return result;
    }

    /**
     * Thêm kích thước mảng gấp đôi để lưu đa thức khi cần thiết.
     */
    private void allocateMore() {
        double[] newArray = new double[coefficents.length * 2];
        System.arraycopy(coefficents, 0, newArray, 0, size);
        coefficents = newArray;
    }
}
