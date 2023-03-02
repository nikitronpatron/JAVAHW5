// Написать метод, определяющую правильность расстановки скобок в выражении.
// Могут содержаться следующие скобки: ()[]{}<>

import java.util.*;

public class EX1
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> openBrackets = new HashMap<String, Integer>() {{
            put("{", 0);
            put("[", 1);
            put("(", 2);
            put("<", 3);

        }};
        HashMap<String, Integer> closeBrackets = new HashMap<String, Integer>() {{
            put("}", 0);
            put("]", 1);
            put(")", 2);
            put(">", 3);

        }};
        String brackets = "()[]{}<>";
        boolean validate = validate(openBrackets, closeBrackets, brackets);

        if(validate) {
            System.out.println("Скобки расставлены правильно.");
        } else {
            System.out.println("Скобки расставлены неправильно.");
        }
    }

    public static boolean validate( HashMap<String, Integer> openBrackets,
                             HashMap<String, Integer> closeBrackets,
                             String brackets){
        String[] strings = brackets.split(",");
        Deque<String> stack = new ArrayDeque<>();
        for (String inLine: strings) {
            for (int i = 0; i < inLine.length(); i++) {
                char character = inLine.charAt(i);
                String symbol = Character.toString(character);
                if (openBrackets.containsKey(symbol)) {
                    stack.push(symbol);
                } else if (closeBrackets.containsKey(symbol)) {
                    if(!stack.isEmpty()) {
                        Integer ordinalNumberForCheckedSymbol = closeBrackets.get(symbol);
                        String firstElemFromStack = stack.pop();
                        Integer ordinalLastAddedNumberElemFromStack = openBrackets.get(firstElemFromStack);
                        boolean isPairTheBrackets =
                                ordinalLastAddedNumberElemFromStack.equals(ordinalNumberForCheckedSymbol);
                        if(isPairTheBrackets) break;
                    }
                  return false;
                }
            }
        }
        return stack.isEmpty();
    }
}