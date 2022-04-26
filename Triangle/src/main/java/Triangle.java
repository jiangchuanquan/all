public class Triangle {
        public String isTriangle(int a,int b, int c) {
            boolean c1 = (1 <= a) && (a <= 100);
            boolean c2 = (1 <= b) && (b <= 100);
            boolean c3 = (1 <= c) && (c <= 100);

            if (!c1 || !c2 || !c3) {
                return "输入错误";
            }

            if ((a < (b + c)) && (b < (a + c)) && (c < (a + b))) {
                if (a == b && b == c) {
                    return "等边三角形";
                } else if ((a != b) && (a != c) && (b != c)) {
                    return "一般三角形";
                } else {
                    return "等腰三角形";
                }
            } else {
                return "非三角形";
            }

        }
    }

