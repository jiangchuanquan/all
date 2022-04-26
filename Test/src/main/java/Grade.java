public class Grade {
    private int datas;//此为疫情的数据
    public int judge(int data){
        //此为疫情风险级别定级函数
        int degree;  //根据疫情数据的规则，等级水平

        
        if(data >= 0){
            if(data > 2000){
                degree = 1;
            }else if(data > 1300){
                degree = 2;
            }else if(data > 800){
                degree = 3;
            }else if(data > 500){
                degree = 4;
            }else if(data > 200){
                degree = 5;
            }else if(data > 50){
                degree = 6;
            }else{
                degree = 7;
            }
            return degree;//返回的等级
                     }
        else
            {
                System.out.println("此数据有问题，输入的不对");
                return 404;//用于表示此错误有问题
           }
    }
}
