package hus.oop.rootsolver;

public abstract class MyAbstractPolynomial implements MyPolynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double[] coeffs = coefficients();
        
        for (int i = 0; i < coeffs.length; i++) {
            if (i == 0) {
                sb.append(coeffs[i]);
            } else {
                if (coeffs[i] >= 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
                sb.append(Math.abs(coeffs[i]));
                if (i == 1) {
                    sb.append("x");
                } else {
                    sb.append("x^").append(i);
                }
            }
        }
        
        return sb.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        int degree = degree();
        if (degree == 0) {
            return new double[]{0};
        }
        
        double[] coeffs = coefficients();
        double[] derivative = new double[degree];
        
        for (int i = 1; i <= degree; i++) {
            derivative[i - 1] = coeffs[i] * i;
        }
        
        return derivative;
    }
}
