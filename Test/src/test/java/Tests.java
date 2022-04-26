import org.junit.Test;

public class Tests {

    int[] data = new int[]{-200,1,2723,200,1500,20,-1};
    //用于进行测试的案例数据
    @Test
    public void test(){
        for(int i = 0 ; i < 7 ; i++)
        {
            Grade grade = new Grade();
            System.out.println(grade.judge(data[i]));
        }

    }
}
