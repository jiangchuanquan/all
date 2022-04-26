import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class TriangleTest {
    Triangle triangle;
    int a;
    int b;
    int c;
    String expected;

    public TriangleTest(int a, int b, int c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection triangleTestCase(){
        return Arrays.asList(new Object[][]{
                {50,50,1,"等腰三角形"}
        });
    }

    @Test
    public void testTriangle(){
        triangle = new Triangle();
        Assert.assertEquals(expected, triangle.isTriangle(a,b,c));
    }
}