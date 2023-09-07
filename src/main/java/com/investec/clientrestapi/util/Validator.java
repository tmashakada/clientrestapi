package com.investec.clientrestapi.util;

public class Validator {

    public static Boolean isIdNumberValid(String idNumber) {

        if (idNumber.length() != 13 || !isNumeric(idNumber)) {
            return false;
        }
        int sum = 0;
        char[] idChars = idNumber.toCharArray();
        // loop over each digit right-to-left, including the check-digit
        for (int i = 1; i <= idChars.length; i++) {
            int digit = Character.getNumericValue(idChars[idChars.length - i]);
            if ((i % 2) != 0) {
                sum += digit;
            } else {
                sum += digit < 5 ? digit * 2 : digit * 2 - 9;
            }
        }
        return (sum % 10) == 0;
    }

    public static int getSum(int n) {
        int sum;
        for (sum = 0; n > 0; sum += n % 10, n /= 10) ;
        return sum;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null || strNum.isEmpty()) {
            return false;
        }

        return strNum.chars().allMatch(Character::isDigit);

    }

}
