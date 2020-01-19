import java.util.*;
import java.io.*;
/* 作用：生成随机数
命令行参数： 1. 输出随机数的数量 2. 3. 最大值和最小值，[min,max)
编译参考： javac -cp -encoding utf-8 .\randomNums.java
运行参考： java -cp randomNums 5 10 20 
Exist Function：生成随机 Double 数列，Int 数列，并保存到文件，读取保存的文件。
Warning：readFile(String name,Char spacer) 对读取的文件有要求，只能读取
         全部都是 Int 数据或者 Double 数据的文件，否则会报错。
*/
public class randomNums{
    private static boolean DEBUG=true;
    final static public int INT=10101;
    final static public double DOUBLE=3.1415926;
    /* (int length, int min, int max)返回length长的int随机数数组，最大值小于max，最小值大于等于min。 */
    public static int[] intArray(String[] args){
        int len=Integer.parseInt(args[0]);
        int min=Integer.parseInt(args[1]);
        int max=Integer.parseInt(args[2]);
        int[] rint=new int[len];
        Random random = new Random();
        if(max<min){
            max=max+min;
            min=max-min;
            max=max-min;
        }
        for(int i=0;i<len;i++){
            rint[i]=random.nextInt(max-min)+min;
        }
        return rint;
    }
    /* (int length, double min, double max)返回length长的double随机数数组，最大值小于max，最小值大于等于min。 */
    public static double[] doubleArray(String[] args){
        int len=Integer.parseInt(args[0]);
        double min=Double.parseDouble(args[1]);
        double max=Double.parseDouble(args[2]);
        double[] rint=new double[len];
        Random random = new Random();
        if(max<min){
            max=max+min;
            min=max-min;
            max=max-min;
        }
        for(int i=0;i<len;i++){
            rint[i]=(max-min)*random.nextDouble()+min;
        }
        return rint;
    }
    /* 把一个int数组'a'存到文件'name'中(utf8,lf) */
    public static int saveFile(int [] a,String name){
        try{
            File file=new File("a.txt");
            FileOutputStream fop=new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
            //writer.append("/* 随机整数 (int) */\n");
            for(int i=0;i<a.length;i++){
                writer.append(a[i]+"\n");
                //writer.append("\n");
            }
            writer.close();
            fop.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return 0;
    }
    /* 把一个double数组'a'存到文件'name'中(utf8,lf) */
    public static int saveFile(double [] a,String name){
        try{
            File file=new File("a.txt");
            FileOutputStream fop=new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
            //writer.append("/* 随机整数 (int) */\n");
            for(int i=0;i<a.length;i++){
                writer.append(a[i]+"\n");
                //writer.append("\n");
            }
            writer.close();
            fop.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return 0;
    }
    /* 末尾带Reader和Writer的都是字符流，末尾是Stream的都是字节流 */
    /* 读取文件,单纯的读取文件并打印 */
    public static int readFile(String name){
        try{
            File file=new File("a.txt");
            FileInputStream fip=new FileInputStream(file);
            InputStreamReader reader=new InputStreamReader(fip,"UTF-8");
            StringBuffer strBuf=new StringBuffer();
            while(reader.ready()){
                strBuf.append((char) reader.read());
            }
            System.out.println(strBuf.toString());
            reader.close();
            fip.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return 0;
    }
    /* readFile 能设定分隔符并读取int或double值 返回 ArrayList*/
    public static ArrayList readFile(String name,char spacer){
        ArrayList ret=new ArrayList();
        try{
            File file=new File(name);
            FileInputStream fip=new FileInputStream(file);
            InputStreamReader reader=new InputStreamReader(fip,"UTF-8");
            StringBuffer strBuf=new StringBuffer();
            char buf;
            /* Class.newInstance()只能调用无参构造函数构造对象，但Integer没有
            T type=clazz.getConstructor(int.class).newInstance(0);
            if(DEBUG){
                System.out.println("type's "+type.getClass());
            } */
            while(reader.ready()){
                    buf=(char)reader.read();
                    if(buf!=spacer){
                        strBuf.append(buf);
                    }else{
                        if(strBuf.indexOf(".")==-1){
                            ret.add(Integer.parseInt(strBuf.toString()));
                        }else{
                            ret.add(Double.parseDouble(strBuf.toString()));
                        }
                        strBuf.delete(0,strBuf.length());
                    }
            }
            reader.close();
            fip.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    public static void main(String[] args){
        int [] rInt=intArray(args);
        double[] rDouble=doubleArray(args);
        saveFile(rInt,"a.txt");
        System.out.println(readFile("a.txt",'\n'));
        saveFile(rDouble,"a.txt");
        System.out.println(readFile("a.txt",'\n'));
    }
}
