package bsu.model;


/**
 * Представляет функцию exp(u) (e^u).
 */
public class Exp implements Expression {
    private Expression argument;

    public Exp(Expression argument) {
        this.argument = argument;
    }

    @Override
    public Expression differentiate() {
        // (exp(u))' = exp(u) * u' (правило цепочки)
        Expression argDiff = argument.differentiate();
        return new Multiplication(new Exp(argument), argDiff);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedArg = argument.simplify();

        if (simplifiedArg instanceof Constant) {
            double value = ((Constant) simplifiedArg).getValue();
            return new Constant(Math.exp(value));
        }

        return new Exp(simplifiedArg);
    }

    @Override
    public String toString() {
        return "exp(" + argument + ")";
    }
}
