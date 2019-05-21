import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RpnNumerator {
    private Stack<Double> numbers = new Stack<Double>();
    private Stack<List<Double>> numlogs = new Stack<>();

    public static void main(String[] args) {
        RpnNumerator cf = new RpnNumerator();
        try {
            while (true) {
                System.out.println("Please input RPN：");
                Scanner scan = new Scanner(System.in);
                String rpn = scan.nextLine();
                cf.rpnExpression(rpn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RPN 计算
     */
    public void rpnExpression(String rpnIn) throws Exception {
        String[] arpn = rpnIn.split(" ");
        int i = 0;
        int apLength = arpn.length;
        while (i < apLength) {
            RpnRules cr = new RpnRules();
            int n = numbers.size();
            if (strToDigit(arpn[i]) != null) {
                numbers.push(strToDigit(arpn[i]));
                numlogs.push(getStack(numbers));
            } else {
                String opt = arpn[i];
                if ("undo".equals(opt) || "clear".equals(opt)) {
                    cr.funcRules(numbers, numlogs, opt);
                } else if ("sqrt".equals(opt)) {
                    if (n > 0) {
                        cr.unaryOptRules(numbers, numlogs, opt);
                    } else {
                        System.out.print("operator" + opt + "(position:" + (2 * i - 1) + "):insufficient parameters ");
                        break;
                    }

                } else if ("+".equals(opt) || "-".equals(opt) || "*".equals(opt) || "/".equals(opt)) {
                    if (n > 1) {
                        cr.binaryOptRules(numbers, numlogs, opt);
                    } else {
                        System.out.print("operator" + opt + "(position:" + (2 * i + 1) + "):insufficient parameters ");
                        break;
                    }

                } else {
                    throw new Exception("ERROR ！");
                }
            }
            i++;
        }
        displayStack(numbers);

    }


    private Double strToDigit(String str) {
        try {
            double num = Double.valueOf(str);
            return num;
        } catch (Exception e) {
            return null;
        }
    }


    public List<Double> getStack(Stack<Double> stk) {
        List<Double> getStk = new ArrayList<>();
        for (Double x : stk) {
            getStk.add(x);
        }
        return getStk;
    }


    public void displayStack(Stack<Double> stk) {
        if (stk.size() != 0) {
            System.out.print("stack:");
            for (Double x : stk) {
                System.out.print(outputFormat(x) + " ");
            }
        } else {
            System.out.println("stack:");
        }
        System.out.println();
    }


    public String outputFormat(double value) {
        DecimalFormat numformat = new DecimalFormat("##########.##########");
        String output = numformat.format(value);
        return output;
    }


}
