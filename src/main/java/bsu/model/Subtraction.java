package bsu.model;

/**
 * Представляет разность двух выражений (f - g).
 */
public class Subtraction implements Expression {
    private Expression left, right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression differentiate() {
        // (f - g)' = f' - g'
        return new Subtraction(left.differentiate(), right.differentiate());
    }

    @Override
    public Expression simplify() {
        Expression leftSim = left.simplify();
        Expression rightSim = right.simplify();

        if (rightSim instanceof Constant c && c.getValue() == 0) return leftSim;

        return new Subtraction(leftSim, rightSim);
    }

    @Override
    public String toString() {
        // строка вида "(f - g)"
        return "(" + left + " - " + right + ")";
    }
}