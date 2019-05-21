import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RpnRules {

    public List<Double> getStack(Stack<Double> stk) {
        List<Double> getStk = new ArrayList<>();
        for (Double x : stk) {
            getStk.add(x);
        }
        return getStk;
    }

    public void unaryOptRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {
        double num = stk1.pop();
        switch (opt) {
            case "sqrt":
                stk1.push(sqrt(num));
                stk2.push(getStack(stk1));
                break;
            default:
                throw new Exception("ERROR");
        }
    }


    public void binaryOptRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {
        double num2 = stk1.pop();
        double num1 = stk1.pop();
        switch (opt) {
            case "+":
                stk1.push(num1 + num2);
                stk2.push(getStack(stk1));
                break;
            case "-":
                stk1.push(num1 - num2);
                stk2.push(getStack(stk1));
                break;
            case "*":
                stk1.push(num1 * num2);
                stk2.push(getStack(stk1));
                break;
            case "/":
                stk1.push(div(num1, num2));
                stk2.push(getStack(stk1));
                break;
            default:
                throw new Exception("ERROR");
        }
    }


    public void funcRules(Stack<Double> stk1, Stack<List<Double>> stk2, String opt) throws Exception {
        switch (opt) {
            case "undo":
                while (!stk1.empty()) {
                    stk1.pop();
                }
                if (!stk2.empty()) {
                    stk2.pop();
                    if (!stk2.empty()) {
                        List<Double> list1 = stk2.peek();
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i) != null) {
                                stk1.push(list1.get(i));
                            }
                        }
                    }
                }
                break;
            case "clear":
                while (!stk1.empty()) {
                    stk1.pop();
                }
                List<Double> list2 = new ArrayList<>();
                list2.add(null);
                stk2.push(list2);
                break;
            default:
                throw new Exception("ERROR");
        }

    }


    private double div(double a, double b) throws Exception {
        if (b == 0) {
            throw new Exception("divisor can not be 0 !");
        }
        return a / b;
    }


    private double sqrt(double f) throws Exception {
        if (f < 0) {
            throw new Exception("can not sqrt for negative number ！");
        }
        double a = (double) Math.sqrt(f);
        return a;
    }


}
