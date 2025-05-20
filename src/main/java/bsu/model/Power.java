package bsu.model;

/**
 * Представляет возведение в степень: base^exponent.
 */
public class Power implements Expression {
    private Expression base;
    private Expression exponent;

    public Power(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public Expression differentiate() {
        // Если показатель константа: производная u^n = n * u^(n-1) * u'
        Expression simplifiedExponent = exponent.simplify();

        if (simplifiedExponent instanceof Constant) {
            double n = ((Constant) simplifiedExponent).getValue();
            Expression baseDiff = base.differentiate();
            return new Multiplication(
                    new Multiplication(
                            new Constant(n),
                            new Power(base, new Constant(n - 1))
                    ),
                    baseDiff
            );
        }
        // В общем случае не поддерживаем (упрощаем до 0)
        return new Constant(0);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedBase = base.simplify();
        Expression simplifiedExponent = exponent.simplify();

        // x^0 = 1
        if (simplifiedExponent instanceof Constant c && c.getValue() == 0) {
            return new Constant(1);
        }
        // x^1 = x
        if (simplifiedExponent instanceof Constant c && c.getValue() == 1) {
            return simplifiedBase;
        }
        // 1^x = 1
        if (simplifiedBase instanceof Constant c && c.getValue() == 1) {
            return new Constant(1);
        }
        // 0^x = 0 for x != 0
        if (simplifiedBase instanceof Constant c && c.getValue() == 0) {
            return new Constant(0);
        }
        return new Power(simplifiedBase, simplifiedExponent);
    }

    @Override
    public String toString() {
        // строка вида "(base^exponent)"
        return "(" + base + "^" + exponent + ")";
    }
}
