import java.util.Scanner;

import static java.util.logging.Level.parse;

public class InputDataCalc {
    public static void main (String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");
        String operation = in.nextLine();
        System.out.println("Output:");
        System.out.println(parse(operation));
    }
    public static String parse (String operation) throws Exception{
        int number1;
        int number2;
        String oper;
        String output;
        boolean isRoman;
        oper = detectOperation(operation);
        if (oper == null)throw new Exception("Wrong sign!");
        String[] operands=operation.split("[+\\-*/]");
        if (operands.length !=2) throw new Exception("Accept only 2 operands");
        // проверка условия если оба числа римские
        if(Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            // конвертация римских в арабские
            number1 = Roman.convertToArabic(operands[0]);
            number2 = Roman.convertToArabic(operands[1]);
            isRoman = true;
        }
        // проверка условия если оба числа арабские
        else if (!Roman.isRoman(operands[0])&& !Roman.isRoman(operands[1])){
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // проверка условия: одно число римское, другое арабское
        else {
            throw new Exception("Numbers must be in the same format");
        }
        if (number1 > 10 || number2 > 10){
            throw new Exception("Nunbers must be from 1 to 10");
        }
        int arabic = calc(number1,number2, oper);
        if (isRoman) {
            // если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabic <=0) {
                throw new Exception("Roman number must be > 0");
            }
            // переводим результат операции из арабского в римское
            output = Roman.convertToRoman(arabic);
        } else {
            // переводим арабское число в тип String
            output = String.valueOf(arabic);
        }
        // возврат результата
        return output;
    }

    static String detectOperation(String operation){
        if (operation.contains("+")) return "+";
        else if (operation.contains("-")) return "-";
        else if(operation.contains("*")) return "*";
        else if(operation.contains("/")) return "/";
        else return null;
    }

    static int calc (int a, int b, String oper){

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a + b;
        else if (oper.equals("*")) return a * b;
        else return a/b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"}; // так как максимольное возможное произведение 100 - массив римских чисел до 100



    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length;i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabic(String roman) {
        for (int i = 0; i < romanArray.length; i++){
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman (int arabic) {
        return romanArray[arabic];
    }

}