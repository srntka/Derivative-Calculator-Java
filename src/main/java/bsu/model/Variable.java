package bsu.model;

/**
 * Представляет переменную x в выражении.
 */
public class Variable implements Expression {

    @Override
    public Expression differentiate() {
        // Производная x равна 1
        return new Constant(1);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return "x";
    }
}