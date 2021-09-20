import com.sun.istack.internal.Nullable;

import java.util.*;

public class OneStep {
    static private Map<String, Integer> map = new TreeMap<String, Integer>();

    static public List<String> splist(String str){//str传过来之前先变成小写
        //临时变量用于保存单词
        String tmp = "";
        List<String> textArray = new ArrayList<>();
//        word.toLowerCase();
////        System.out.println(word);
        for (int i = 0; i < str.length(); ++i){
            //将是英语字母且连续出现的单词保存到tmp中，直至出现其他字符将tmp=""，且将tmp加入到list中
            if ((str.charAt( i ) > 96 && str.charAt( i ) < 123) || str.charAt( i )=='’' || (str.charAt( i ) > 64 && str.charAt( i ) < 91)){
                tmp += str.charAt( i );
            }else if( tmp.length() != 0){
                textArray.add( tmp );
                tmp="";
            }else tmp="";
        }
        return textArray;
    }

    static public void getMap(List<String> textArray, @Nullable List<String> stopList){

        for (int i = 0; i < textArray.size(); i++) {
            //转为小写
            String key = textArray.get( i ).toLowerCase();
            //去空值
            if((key != null || !"".equals(key)) && judjeStop(key, stopList)){
                Integer num = map.get(key);
                if(num == null || num == 0){
                    map.put(key, 1);
                }else if(num > 0){
                    map.put(key, num+1);
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
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>( map.entrySet() );
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
    //判断该单词是否需要跳过
    static public boolean judjeStop(String key, List<String> stopList){
        if (stopList != null || stopList.size() != 0){
            for (String word: stopList){
                if (key.equals( word )) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String t = "adas asds ds , , dasd ,sdad - da ";
        List<String> list = splist( t );
        for (String s: list){
            System.out.println(s);
        }
    }
}
