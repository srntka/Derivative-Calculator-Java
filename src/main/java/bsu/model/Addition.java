package bsu.model;

/**
 * Представляет сумму двух выражений (f + g).
 */
public class Addition implements Expression {
    private Expression left, right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression differentiate() {
        // (f + g)' = f' + g'
        return new Addition(left.differentiate(), right.differentiate());
    }

    @Override
    public Expression simplify() {
        Expression leftSim = left.simplify();
        Expression rightSim = right.simplify();

        if (leftSim instanceof Constant && rightSim instanceof Constant) {
            double sum = ((Constant) leftSim).getValue() + ((Constant) rightSim).getValue();
            return new Constant(sum);
        }

        return new Addition(leftSim, rightSim);
    }

    @Override
    public String toString() {
        // строка вида "(f + g)"
        return "(" + left + " + " + right + ")";
    }
}
