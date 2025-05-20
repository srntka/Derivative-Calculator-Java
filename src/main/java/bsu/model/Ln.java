package bsu.model;

/**
 * Представляет функцию ln(u).
 */
public class Ln implements Expression {
    private Expression argument;

    public Ln(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate() {
        // (ln(u))' = u' * u^(-1)
        Expression argDiff = argument.differentiate();
        return new Multiplication(argDiff, new Power(argument, new Constant(-1)));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedArg = argument.simplify();
        if (simplifiedArg instanceof Constant c && c.getValue() == 1) {
            return new Constant(0);
        }
        return new Ln(simplifiedArg);
    }

    @Override
    public String toString() {
        return "ln(" + argument + ")";
    }
}