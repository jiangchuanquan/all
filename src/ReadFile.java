import java.io.File;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    static public List<String> files = new ArrayList<String>();
    public static void getFiles(String path, boolean isDir) {
        //如果是文件的话直接结束
        if (path.contains( ".txt" )) {
            files.add( path );
            return;
        }

        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (isDir && tempList[i].isDirectory()) {
                //这里就不递归了，
                getFiles( tempList[i].toString() , true);
            }
        }
        return;
    }

    public static void main(String[] args) {
        getFiles( "D:\\JavaWebSSM\\Word Frequence\\word" , true);
        for (String s: files){
            System.out.println(s);
        }
        Scanner s = new Scanner(System.in);
        System.out.println(s.nextLine());
    }
}
