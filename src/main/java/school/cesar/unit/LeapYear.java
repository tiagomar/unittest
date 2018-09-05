package school.cesar.unit;

import java.util.Calendar;

public class LeapYear {

    public boolean isLeap(){
        Integer year = getSystemCurrentYear();

        if(year%4==0){
            if(year%100==0) {
                if(year%400==0){
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Integer getSystemCurrentYear(){
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        return year;
    }

}
