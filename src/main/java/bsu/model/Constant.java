package bsu.model;

/**
 * Представляет числовую константу.
 */
public class Constant implements Expression {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Expression differentiate() {
        // Производная от константы — 0
        return new Constant(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}