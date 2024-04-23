package apps;

import java.util.regex.Pattern;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class ExpressionEvaluator {
    private static final Pattern UNSIGNED_DOUBLE = Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    private static final Pattern CHARACTER = Pattern.compile("\\S.*?");
    private static String str1 = "ERROR";

    public static String toPostfix(String expression){
        Scanner sc = new Scanner(expression);
        Stack<Character> chars = new Stack<Character>(); 
        int nums = 0;
        int operators = 0;
        char symbol;
        String postfix = "";
        String next = "";
        while (sc.hasNext()){
            if (sc.hasNext(UNSIGNED_DOUBLE)){
                next = sc.findInLine(UNSIGNED_DOUBLE);
                nums++;
                postfix += next + " ";
            }
            else{
                next = sc.findInLine(CHARACTER);
                symbol = next.charAt(0);
                if (symbol == '('){
                    chars.push(symbol);
                }
                else if (symbol == '/' || symbol == '*' || symbol == '-' || symbol == '+'){
                    while (!chars.isEmpty() && chars.peek() != '(' && !higherPrecedence(chars.peek(), symbol)){
                        operators++;
                        postfix += (char) chars.pop() + " ";
                    }
                    chars.push(symbol);
                }
                else if (symbol == ')'){
                    while (!chars.isEmpty() && chars.peek() != '('){
                        operators++;
                        postfix += (char) chars.pop() + " ";
                    }
                    if (chars.peek() == '('){
                        chars.pop();
                    }
                    else{
                        return str1;
                    }
                }
                else{
                    postfix = str1;
                }
            }
        }
        while (!chars.empty()){
            symbol = chars.pop();
            if (symbol != '('){
                operators++;
                postfix += symbol + " ";
            }
        }
        if (nums - 1 != operators){
            return str1;
        }
        return postfix;
    }

    
    
    
    public static double evaluate(String postfixExpression){
        Scanner sc = new Scanner(postfixExpression);
        Stack<Double> doubles = new Stack<Double>();
        double answer = Double.NaN;
        char operator;
        String next;
        while (sc.hasNext()){
            if (sc.hasNext(UNSIGNED_DOUBLE)){
                next = sc.findInLine(UNSIGNED_DOUBLE);
                doubles.push(Double.parseDouble(next));
            }
            else{
                next = sc.findInLine(CHARACTER);
                operator = next.charAt(0);
                double num1 = 0.0;
                double num2 = 0.0;
                if (operator == '/'){
                    num2 = doubles.pop();
                    num1 = doubles.pop();
                    doubles.push((num1 / num2));
                }
                else if (operator == '*'){
                    num1 = doubles.pop();
                    num2 = doubles.pop();
                    doubles.push((num1 * num2));
                }
                else if (operator == '-'){
                    num2 = doubles.pop();
                    num1 = doubles.pop();
                    doubles.push((num1 - num2));
                }
                else if (operator == '+'){
                    num2 = doubles.pop();
                    num1 = doubles.pop();
                    doubles.push((num1 + num2));
                }
            }
            
        }
        answer = doubles.pop();
        return answer;
    }

    private static boolean higherPrecedence(char current, char top){
        return (current == '+' || current == '-') && (top == '*' || top == '/');
    }
}
