import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = in.nextLine();
        calc(input);
    }

    public static String calc(String input) {

        int result = 0;
        int romanNumber = 0;
        String operator = "";

        if (input.contains("+"))
            operator = "\\+";
        else if (input.contains("-"))
            operator = "-";
        else if (input.contains("*"))
            operator = "\\*";
        else if (input.contains("/"))
            operator = "/";
        else
            throw new IllegalArgumentException("Калькулятор работает с операциями (+,-,*,/). Например 2 + 2");

        input = input.replace(" ", "");

        String[] parts = input.split(operator);

        int firstNumber = 0;
        int secondNumber = 0;
        if (parts.length > 2 || parts.length < 2) {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет условию, например (2 + 2)");
        }
        if (Arrays.asList(roman).contains(parts[0]) && Arrays.asList(roman).contains(parts[1])) {
            firstNumber = RomanToNumber(parts[0]);
            secondNumber = RomanToNumber(parts[1]);
            romanNumber = 1;
        } else if (Arrays.asList(roman).contains(parts[0]) && !Arrays.asList(roman).contains(parts[1])
                || Arrays.asList(roman).contains(parts[1]) && !Arrays.asList(roman).contains(parts[0])) {
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        } else {
            try {
                firstNumber = Integer.parseInt(parts[0]);
                secondNumber = Integer.parseInt(parts[1]);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Калькулятор умеет работать только с целыми числами");
            }
        }

        if (firstNumber >= 1 && firstNumber <= 10 && secondNumber >= 1 && secondNumber <= 10) {
            if (firstNumber == (int) firstNumber && secondNumber == (int) secondNumber) {
                switch (operator) {
                    case "\\+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "\\*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        try {
                            result = firstNumber / secondNumber;
                            break;
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    default:
                        throw new IllegalArgumentException("Калькулятор работает с операциями +,-,*,/");
                }
                if (romanNumber == 1) {
                    if (result > 1)
                        System.out.println("Результат: " + NumberToRoman(result));
                    else
                        throw new IllegalArgumentException("Результат работы в римской системе должен быть больше 1");
                } else
                    System.out.println("Результат: " + result);

            } else {
                throw new IllegalArgumentException("Калькулятор принимает только целые числа");
            }
        } else {
            throw new IllegalArgumentException("Калькулятор принимает числа от 1 до 10");
        }
        return Integer.toString(result);
    }

    public static String NumberToRoman(int numb) {
        return roman[numb - 1];
    }

    public static int RomanToNumber(String roman) {
        try {
            switch (roman) {
                case ("I"):
                    return 1;
                case ("II"):
                    return 2;
                case ("III"):
                    return 3;
                case ("IV"):
                    return 4;
                case ("V"):
                    return 5;
                case ("VI"):
                    return 6;
                case ("VII"):
                    return 7;
                case ("VIII"):
                    return 8;
                case ("IX"):
                    return 9;
                case ("X"):
                    return 10;
            }
            return Integer.parseInt(roman);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Калькулятор умеет работать только с целыми числами от 1 до 10");
        }
    }
}