import com.sun.istack.internal.Nullable;

import java.util.*;

public class FourStep {
    static private  Map<String, Integer> verbMap = new TreeMap<>();

    static public void getMap(List<String> words, List<List<String>> verbList){
        boolean flag = true;
        for(String str: words){
            str = str.toLowerCase();
            Integer value = verbMap.get(str);
            flag = true;
            for(List<String> verb: verbList){
                String tmp = verb.get( 0 );
                Integer verbValue = verbMap.get(tmp);
                boolean findStrInVerb = false;
                for (String t: verb){
                    if (t.equals( str )){
                        findStrInVerb = true;
                        break;
                    }
                }
                if (findStrInVerb){
                    flag = false;
                    if ( verbValue == null || verbValue == 0){
                        verbMap.put( tmp,  1);
                        break;
                    }else{
                        verbMap.put(tmp,verbValue+1);
                        break;
                    }
                }
               else continue;
            }
            if (flag){
                if (value == null  || value == 0){
                    verbMap.put( str,  1);
                }else {
                    verbMap.put(str, value+1);
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
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>( verbMap.entrySet() );
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
        if (stopList != null){
            for (String word: stopList){
                if (key.equals( word )) return false;
            }
        }
        return true;
    }

}
