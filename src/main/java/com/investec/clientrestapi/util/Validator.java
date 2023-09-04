package com.investec.clientrestapi.util;

public class Validator {

    public static Boolean isIdNumberValid(String idNumber) {
        boolean isValid = false;
        if (idNumber.length()!= 13 || !isNumeric(idNumber) ) {
            return  isValid;
        }
        int sum = 0;
        char[] idChars = idNumber.toCharArray();
        StringBuilder even= new StringBuilder();
        for (int i = 0; i <= idChars.length-2; i++) {
            int digit = Character.getNumericValue(idChars[i]);
            if(((i+1)%2)!=0){
                sum+=digit;
            }else{
                even.append(idChars[i]);
            }
        }
        int sumEven=getSum(Integer.parseInt(even.toString())*2);
        int checkDigit=10-((sumEven+sum)%10);
        if(checkDigit == Integer.parseInt(String.valueOf(idNumber.charAt(12)))){
            isValid=true;
        }

        return isValid;
    }

    public static int getSum(int n) {
        int sum;
        for (sum = 0; n > 0; sum += n % 10, n /= 10);
        return sum;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null || strNum.isEmpty()) {
            return false;
        }

       return strNum.chars().allMatch( Character::isDigit );

    }

}
