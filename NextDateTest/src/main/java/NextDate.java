public class NextDate {
    public String getNextDate(int day, int month, int year){
        int tDay = day, tMonth = month, tYear = year;
        if((day >= 1 && day<= 31) && (month >= 1 && month <= 12 ) && (year >= 1900 && year <=2050))
        {
            boolean isLeapYear = ((year%400)==0) || (((year%4)==0) && ((year%100)!=0));
//用于判断是否为闰年
            switch (month){
                case 2:
                    if(day==29 && !isLeapYear)
                        return "输入数据有问题";
                    if (day<28){
                        tDay = day + 1;
                    }

                    if(day == 28 && isLeapYear) {
                        tDay = 29;
                    }
                    if((day == 29 && isLeapYear) ||(day == 28 && !isLeapYear))
                    {
                        tDay = 1;
                        tMonth = 3;
                    }

                    if(day > 29 || day <1)
                        return "输入数据有问题";
                    break;
                case 12:
                    if (day<31){
                        tDay = day + 1;
                    }else {
                        tDay = 1;
                        tMonth = 1;
                        if (year == 2050){
                            return "输入数据有问题";
                        }else {
                            tYear = year + 1;
                        }
                    }
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                    if (day < 31) //有前面一层的限制，day肯定是小于等于32的，如果不小于31，此时day为31天
                        tDay = day + 1;
                    else {
                        tDay = 1;
                        tMonth = month+1;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day < 30){
                        tDay = day + 1;
                    }else if (day == 30){
                        tDay = 1;
                        tMonth = month + 1;
                    }else {
                        return "输入数据有问题";
                    }
                    break;


                default:
                    return "输入数据有问题";
            }
            return  tYear + "年" + tMonth + "月" + tDay + "日";

        }
        return "输入数据有问题";

    }

}
