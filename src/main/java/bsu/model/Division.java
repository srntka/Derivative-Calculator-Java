package bsu.model;

/**
 * Представляет деление двух выражений (f / g).
 */
public class Division implements Expression {
    private Expression numerator, denominator;

    public Division(Expression numerator, Expression denominator) {
        if (denominator instanceof Constant constant && constant.getValue() == 0.0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public Expression differentiate() {
        // (f / g)' = (f' * g - f * g') / (g ^ 2)
        Expression f = numerator;
        Expression g = denominator;

        Expression fPrime = f.differentiate();
        Expression gPrime = g.differentiate();

        Expression numeratorPart = new Subtraction(
                new Multiplication(fPrime, g),
                new Multiplication(f, gPrime)
        );

        Expression denominatorPart = new Power(g, new Constant(2));

        return new Division(numeratorPart, denominatorPart);
    }

    @Override
    public Expression simplify() {
        Expression num = numerator.simplify();
        Expression denom = denominator.simplify();

        if (num instanceof Constant c && c.getValue() == 0) return new Constant(0);
        if (denom instanceof Constant c && c.getValue() == 1) return num;

        return new Division(num, denom);
    }

    @Override
    public String toString() {
        return "(" + numerator + " / " + denominator + ")";
    }
}