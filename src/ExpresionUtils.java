import java.util.*;

public class ExpresionUtils {

    // Verifica si los paréntesis están balanceados
    public static boolean parentesisBalanceados(String expresion) {
        Stack<Character> pila = new Stack<>();
        for (char c : expresion.toCharArray()) {
            if (c == '(') pila.push(c);
            else if (c == ')') {
                if (pila.isEmpty()) return false;
                pila.pop();
            }
        }
        return pila.isEmpty();
    }

    // Convierte notación infija a postfija
    public static String infijaAPostfija(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        Map<Character, Integer> precedencia = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

        for (char c : expresion.toCharArray()) {
            if (Character.isDigit(c)) resultado.append(c);
            else if (c == '(') pila.push(c);
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') resultado.append(pila.pop());
                pila.pop(); // Eliminar '('
            } else if (precedencia.containsKey(c)) {
                while (!pila.isEmpty() && precedencia.getOrDefault(pila.peek(), 0) >= precedencia.get(c)) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }
        while (!pila.isEmpty()) resultado.append(pila.pop());
        return resultado.toString();
    }

    // Evalúa la expresión en notación postfija
    public static int evaluarPostfija(String expresionPostfija) {
        Stack<Integer> pila = new Stack<>();
        for (char c : expresionPostfija.toCharArray()) {
            if (Character.isDigit(c)) pila.push(c - '0');
            else {
                int b = pila.pop();
                int a = pila.pop();
                switch (c) {
                    case '+': pila.push(a + b); break;
                    case '-': pila.push(a - b); break;
                    case '*': pila.push(a * b); break;
                    case '/': pila.push(a / b); break;
                }
            }
        }
        return pila.pop();
    }
}
