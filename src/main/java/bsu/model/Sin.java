package bsu.model;

/**
 * Представляет функцию sin(u).
 */
public class Sin implements Expression {
    private Expression argument;

    public Sin(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate() {
        // (sin(u))' = cos(u) * u'
        Expression argDiff = argument.differentiate();
        return new Multiplication(new Cos(argument), argDiff);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedArg = argument.simplify();
        return new Sin(simplifiedArg);
    }

    @Override
    public String toString() {
        return "sin(" + argument + ")";
    }
}
