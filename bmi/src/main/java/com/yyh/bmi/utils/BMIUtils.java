package com.yyh.bmi.utils;

public class BMIUtils {

    public static String getBMI(String weight,String height){
        return String.valueOf(Double.valueOf(weight)/
                Double.valueOf(height)/Double.valueOf(height)).substring(0,4);
    }

    public static String getBMIAndMale(String bmi){
        Double result = Double.valueOf(bmi);
        if(result < 20)
            return "体重过轻";
        else if(result < 25)
            return "体重正常";
        else if(result < 27)
            return "体重超重";
        else if(result < 30)
            return "轻度肥胖";
        else if(result < 35)
            return "中度肥胖";
        return "重度肥胖";
    }

    public static String getBMIAndFemale(String bmi){
        Double result = Double.valueOf(bmi);
        if(result < 19)
            return "体重过轻";
        else if(result < 24)
            return "体重正常";
        else if(result < 26)
            return "体重超重";
        else if(result < 29)
            return "轻度肥胖";
        else if(result < 34)
            return "中度肥胖";
        return "重度肥胖";
    }
}
