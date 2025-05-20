package bsu.model;

/**
 * Представляет произведение двух выражений (f * g).
 */
public class Multiplication implements Expression {
    private Expression left, right;

    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression differentiate() {
        // (f * g)' = f' * g + f * g'
        Expression leftDiff = left.differentiate();
        Expression rightDiff = right.differentiate();
        return new Addition(
                new Multiplication(leftDiff, right),
                new Multiplication(left, rightDiff)
        );
    }

    @Override
    public Expression simplify() {
        Expression l = left.simplify();
        Expression r = right.simplify();

        if (l instanceof Constant cl) {
            if (cl.getValue() == 0) return new Constant(0);
            if (cl.getValue() == 1) return r;
        }
        if (r instanceof Constant cr) {
            if (cr.getValue() == 0) return new Constant(0);
            if (cr.getValue() == 1) return l;
        }
        return new Multiplication(l, r);
    }

    @Override
    public String toString() {
        // строка вида "(f * g)"
        return "(" + left + " * " + right + ")";
    }

}
