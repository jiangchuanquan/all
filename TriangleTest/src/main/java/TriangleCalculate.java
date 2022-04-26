public class TriangleCalculate {

    public String triangle(float a, float b, float c) {
        if ((a >= 1 && a <= 100 && a % 1 == 0) && (b >= 1 && b <= 100 && b % 1 == 0) && (c >= 1 && c <= 100 && c % 1 == 0)) {
            if ((a + b) > c && (a + c) > b && (b + c) > a) {
                if (a == b && a == c)
                    return "等边三角形";
                if ((a == b && a != c) || (a == c & a != b) || (b == c && a != b))
                    return "等腰三角形";
                if (a != b && a != c && b != c)
                    return "一般三角形";
            } else
                return "非三角形";
        } else
            return "输入错误";
        return "";
    }
}
