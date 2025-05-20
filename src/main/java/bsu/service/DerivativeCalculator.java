package bsu.service;

import bsu.app.model.*;
import bsu.model.*;
import com.yourorg.app.model.*;

public class DerivativeCalculator {

    public static String differentiate(String input) {
        Expression expr = parse(input);
        Expression deriv = expr.differentiate().simplify();
        return deriv.toString();
    }

    private static Expression parse(String input) {
        Parser parser = new Parser(input);
        Expression result = parser.parseExpression();
        if (parser.currentChar() != '\0') {
            throw new RuntimeException("Ошибка парсинга выражения на позиции " + parser.getPosition());
        }
        return result;
    }

    private static class Parser {
        private final String input;
        private int pos = -1;
        private char ch;

        public Parser(String input) {
            this.input = input.replaceAll("\\s+", "");
            nextChar();
        }

        public int getPosition() {
            return pos;
        }

        public char currentChar() {
            return ch;
        }

        private void nextChar() {
            pos++;
            ch = pos < input.length() ? input.charAt(pos) : '\0';
        }

        private boolean eat(char charToEat) {
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        public Expression parseExpression() {
            Expression result = parseTerm();
            while (true) {
                if (eat('+')) result = new Addition(result, parseTerm());
                else if (eat('-')) result = new Subtraction(result, parseTerm());
                else break;
            }
            return result;
        }

        private Expression parseTerm() {
            Expression result = parsePower();
            while (true) {
                if (eat('*')) result = new Multiplication(result, parsePower());
                else if (eat('/')) result = new Division(result, parsePower());
                else break;
            }
            return result;
        }

        private Expression parsePower() {
            Expression result = parsePrimary();
            while (eat('^')) {
                result = new Power(result, parsePrimary());
            }
            return result;
        }

        private Expression parsePrimary() {
            if (eat('+')) return parsePrimary();
            if (eat('-')) return new Multiplication(new Constant(-1), parsePrimary());

            Expression result;
            int startPos = this.pos;

            if (eat('(')) {
                result = parseExpression();
                if (!eat(')')) throw new RuntimeException("Пропущена закрывающая скобка");
                return result;
            }

            if (Character.isLetter(ch)) {
                StringBuilder name = new StringBuilder();
                while (Character.isLetter(ch)) {
                    name.append(ch);
                    nextChar();
                }
                String func = name.toString();
                return switch (func) {
                    case "x" -> new Variable();
                    case "sin" -> new Sin(parseParenthesizedArg());
                    case "cos" -> new Cos(parseParenthesizedArg());
                    case "ln" -> new Ln(parseParenthesizedArg());
                    case "exp" -> new Exp(parseParenthesizedArg());
                    default -> throw new RuntimeException("Неизвестная функция: " + func);
                };
            }

            if (Character.isDigit(ch) || ch == '.') {
                while (Character.isDigit(ch) || ch == '.') nextChar();
                double value = Double.parseDouble(input.substring(startPos, this.pos));
                return new Constant(value);
            }

            throw new RuntimeException("Неожиданный символ: " + ch);
        }

        private Expression parseParenthesizedArg() {
            if (!eat('(')) throw new RuntimeException("Ожидалась '('");
            Expression arg = parseExpression();
            if (!eat(')')) throw new RuntimeException("Ожидалась ')'");
            return arg;
        }
    }
}