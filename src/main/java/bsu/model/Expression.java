package bsu.model;

/**
 * Базовый интерфейс для представления математического выражения.
 */
public interface Expression {

    Expression differentiate();
    Expression simplify();
    @Override
    String toString();
}