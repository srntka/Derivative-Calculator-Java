package bsu.model;

/**
 * Представляет функцию cos(u).
 */
public class Cos implements Expression {
    private Expression argument;

    public Cos(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate() {
        // (cos(u))' = -sin(u) * u' (правило цепочки)
        Expression argDiff = argument.differentiate();
        // Используем -1 * sin(u) * u'
        return new Multiplication(
                new Constant(-1),
                new Multiplication(new Sin(argument), argDiff)
        );
    }

    @Override
    public Expression simplify() {
        Expression simplifiedArg = argument.simplify();
        return new Cos(simplifiedArg);
    }

    @Override
    public String toString() {
        return "cos(" + argument + ")";
    }
}
