import org.junit.Assert;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.Collection;
@RunWith(Parameterized.class)
public class TestTri {
    TriangleCalculate triangleCalculate;
    String excepted;
    float a, b, c;


    public TestTri(float a, float b, float c, String excepted)
    {

        this.a = a;
        this.b = b;
        this.c = c;
        this.excepted = excepted;
    }
    @Parameterized.Parameters
    public static Collection TestriCase(){
        return Arrays.asList(new Object[][]{
                {0,10,2,"等腰三角形"},
                {10,10,10,"等腰三角形"},
                {0,0,0,"等边三角形"},
                {0,0,10,"等边三角形"},
                {10,10,5190,"等腰三角形"},
                {30,10,10,"非三角形"},
                {10,12,19,"一般三角形"}

        });
    }
@org.junit.Test
    public void testTriangle()
{
    triangleCalculate = new TriangleCalculate();
    Assert.assertEquals(excepted,triangleCalculate.triangle(a,b,c));
}

}
