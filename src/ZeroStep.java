import java.util.*;

public class ZeroStep {

    static private Map<Character, Integer> map = new TreeMap<Character, Integer>();

    //用于统计英文字母的总数，排除除英文字母之外其他的字符
    static public int wordNum(String word){
        int num = 0;
        for(int i = 0; i < word.length(); ++i){
            if ((word.charAt( i ) > 64 && word.charAt( i ) < 91) || (word.charAt( i ) > 96 && word.charAt( i ) < 122)) num++;
        }
        return num;
    }

    static public void sortWord(int numbers, int[] wordNumber){
        for(int i = 1; i <= 26; ++i){
            map.put((char) (96+i), wordNumber[i]);
        }
        //自定义比较器
        Comparator<Map.Entry<Character, Integer>> valCmp = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                //先按字母出现的次数进行比较， 后者间前者降序排序
                if (o2.getValue() != o1.getValue())return o2.getValue() - o1.getValue();
                    //若字母出现的次数相同则按字母的ASCII码进行比较，前者减后者升序排序，这一步其实可以不用因为wordNumber就是按字母的顺序排好的
                else return o1.getKey() - o2.getKey();
            }
        };
        //将map用作list的一个存储单位
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>( map.entrySet() );
        Collections.sort( list, valCmp );
        //输出
        for (int i = 0; i < list.size(); ++i){
            double rate = (1.0 * list.get(i).getValue() )/ (1.0 * numbers) * 100;
            System.out.printf(" %c \t\t   %d \t\t  %.2f%s\n", list.get(i).getKey(), list.get(i).getValue() , rate, "%");
        }
    }
}
