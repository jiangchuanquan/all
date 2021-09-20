import com.sun.istack.internal.Nullable;

import java.util.*;

public class ThreeStep {
    static private Map<String, Integer> phraseMap = new TreeMap<>();

    static public void getMap(List<String> phraseList, String word){
        for (String phrase: phraseList){
            phrase = phrase.toLowerCase();//转为小写统计
            int count = 0;
            int i = 0;
            while ( i < word.length()){
                int at = word.indexOf( phrase, i);
                if(at != -1) {
                    count++;
                    i=at+phrase.length();
                }else{
                    phraseMap.put( phrase, count );
                    break;//没找到直接找下一个
                }
            }
        }
    }

    static public List<Map.Entry<String, Integer>> sort(){
        Comparator<Map.Entry<String, Integer>> valCmp = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //先按字母出现的次数进行比较， 后者在前者降序排序
                if (o2.getValue() != o1.getValue())return o2.getValue() - o1.getValue();
                    //若字母出现的次数相同则按字典序进行比较，前者减后者升序排序
                else return o1.getKey().compareTo( o2.getKey() ) ;
            }
        };
        //将map用作list的一个存储单位
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>( phraseMap.entrySet() );
        Collections.sort( list, valCmp );
        return list;
    }
    //输出单词及其出现次数表
    static public void print(List<Map.Entry<String, Integer>> list, @Nullable Integer n){
        if (n == null) n = list.size();
        for (int i = 0; i < n; ++i){
            System.out.printf("  %s%15d\n", list.get( i ).getKey(), list.get( i ).getValue());
        }
    }

}
