import com.sun.istack.internal.Nullable;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public  class Step {
    static private int[] wordNumber = new int[27];

    //第0步，统计26个字母出现的频率，注意不区分大小写
    static public void zero(String word){
        //下标0用于保存文件中
        wordNumber[0] = word.length();
        //获得字木母的个数
        int numbers = ZeroStep.wordNum( word );

        //将字母出现的次数统计到数据wordNumber中
        for(int i = 0; i < wordNumber[0]; ++i){
            if (word.charAt( i ) > 64 && word.charAt( i ) < 91) wordNumber[word.charAt( i )-64]++;
            else if(word.charAt( i ) > 96 && word.charAt( i ) < 123) wordNumber[word.charAt( i )-96]++;
            else continue;
        }

        //将26个字母出现的次数和所占频率打印出来
        System.out.println("字母 \t 出现次数 \t 出现频率");
        ZeroStep.sortWord( numbers, wordNumber );
    }

    static public void one(String word, @Nullable Integer n){

        //先获得所有单词
        List<String> textArray = OneStep.splist(word);

        //遍历textArray把单词统计合并起来
        OneStep.getMap(textArray, null);

        //根据单词出现的次数进行降序排序，次数相同的按字典序排序
        List<Map.Entry<String, Integer>> list =  OneStep.sort();

        //输出
        System.out.println("  单词 \t\t\t 出现的次数");
        OneStep.print(list, n);

    }


    static public void one_d(String path, boolean isDir) throws IOException {
        int i = 0;
        ReadFile.files.clear();
        ReadFile.getFiles( path, isDir);
        for (String file: ReadFile.files){
            String text = FileUtils.readFileToString(new File( file ), "utf8");
            System.out.println("=============文件"+file+"===============");
            one(text, null);
            System.out.println("===========================================");
        }

    }

    static public void two(String word) throws IOException {
        File stopFile = new File( "D:\\JavaWebSSM\\Word Frequence\\word\\stop.txt" );
        String stopWord = FileUtils.readFileToString(stopFile, "utf8"  );
        List<String> stopList = Arrays.asList( stopWord.split( " " ) );
        System.out.println("-----停词表："+stopList+"------------");

        //先获得所有单词
        List<String> textArray = OneStep.splist(word);

        //遍历textArray把单词统计合并起来
        OneStep.getMap(textArray, stopList);

        //根据单词出现的次数进行降序排序，次数相同的按字典序排序
        List<Map.Entry<String, Integer>> list =  OneStep.sort();

        //输出
        System.out.println("  单词 \t\t\t 出现的次数");
        OneStep.print(list, null);
    }

    static public void three(String word) throws IOException {
        File file = new File("D:\\JavaWebSSM\\Word Frequence\\word\\phrase.txt");
        String phrases = FileUtils.readFileToString(file, "utf8"  );
        List<String> phraseList = Arrays.asList( phrases.split( "," ) );
        System.out.println("-------------短语表"+phraseList+"------------");
        //遍历textArray把单词统计合并起来
        ThreeStep.getMap(phraseList, word);

        //根据单词出现的次数进行降序排序，次数相同的按字典序排序
        List<Map.Entry<String, Integer>> list =  ThreeStep.sort();

        //输出
        System.out.println("  单词 \t\t\t 出现的次数");
        ThreeStep.print(list, null);
        System.out.println("=============================================");
    }
    static public void four() throws IOException {
        //动词形态模板
        File file = new File("D:\\JavaWebSSM\\Word Frequence\\word\\verbFormat.txt");
        String verbs = FileUtils.readFileToString(file, "utf8"  );
        List<String> verbList = Arrays.asList( verbs.split( "," ) );
        List<List<String>> verbList2 = new ArrayList<>();
        for (String str: verbList){
            verbList2.add( Arrays.asList( str.split( " " ) ) );
        }
        //用于统计并合并不同动词形态的文件
        File file2 = new File("D:\\JavaWebSSM\\Word Frequence\\word\\verbWord.txt");
        String word = FileUtils.readFileToString(file2, "utf8"  );

        List<String> words =  OneStep.splist( word );
        System.out.println(words);

        FourStep.getMap( words , verbList2);

        List<Map.Entry<String, Integer>> list =   FourStep.sort();

        System.out.println("  单词 \t\t\t 出现的次数");
        FourStep.print( list, null );
    }


    public static void main(String[] args) throws IOException {
        //动词形态模板
        File file = new File("D:\\JavaWebSSM\\Word Frequence\\word\\verbFormat.txt");
        String verbs = FileUtils.readFileToString(file, "utf8"  );
        List<String> verbList = Arrays.asList( verbs.split( "," ) );
        List<List<String>> verbList2 = new ArrayList<>();
        for (String str: verbList){
            verbList2.add( Arrays.asList( str.split( " " ) ) );
        }
        //用于统计并合并不同动词形态的文件
        File file2 = new File("D:\\JavaWebSSM\\Word Frequence\\word\\verbWord.txt");
        String word = FileUtils.readFileToString(file2, "utf8"  );

        List<String> words =  OneStep.splist( word );
        System.out.println(words);
        FourStep.getMap( words , verbList2);
        List<Map.Entry<String, Integer>> list =   FourStep.sort();

        System.out.println("  单词 \t\t\t 出现的次数");
        FourStep.print( list, null );
    }
}
