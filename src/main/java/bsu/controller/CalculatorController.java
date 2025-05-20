package bsu.controller;
import bsu.service.DerivativeCalculator;
public class CalculatorController {
    public String calculate(String expr){
        return DerivativeCalculator.differentiate(expr);
    }
}