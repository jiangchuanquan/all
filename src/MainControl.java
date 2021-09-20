import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainControl {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\JavaWebSSM\\Word Frequence\\word\\word.txt");
        String word = FileUtils.readFileToString(file, "utf8"  );
        boolean flag = true;
        Scanner writer = new Scanner(System.in);
        String fun = new String(  );

        while (flag){
            fun = writer.nextLine();
            switch (fun){
                case "-c": Step.zero(word); break;
                case "-d":{
                    String path = writer.nextLine();
                    Step.one_d(path, false);
                    break;
                } case "-d -s":{
                    String path = writer.nextLine();
                    Step.one_d(path, true);
                    break;
                }
                case "-f": Step.one(word, null); break;
                case "-n":{
                    int n = writer.nextInt();
                    Step.one(word, n);
                    break;
                }
                case "-x -f": Step.two(word); break;
                case "-p": Step.three(word); break;
                case "-v": Step.four();break;
                case "exit":{
                    flag = false;
                    System.out.println("成功退出程序！");break;
                }

            }
        }

    }
}
