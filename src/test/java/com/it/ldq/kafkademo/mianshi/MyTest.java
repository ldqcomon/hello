package com.it.ldq.kafkademo.mianshi;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: ldq
 * @Date: 2020/3/18
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class MyTest {

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("请你输入一行句子:");
        String s = scanner.nextLine();
        String[] s2 = s.split(" ");
        int length = s2.length;
        float f =  (s.length()-s2.length+1)/length;
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(f);
        System.out.println(format);*/

        //正则表达式
        /*String patternStr = "^[[A-Za-z]|\\s]+$";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请你输入一行句子:");
        String s = scanner.nextLine();
        Matcher meq = Pattern.compile(patternStr).matcher(s);
        if (!meq.matches()) {
            System.out.println("只包含大小写的英文字母和空格");
        }
        if (s.length()>=100) {
            System.out.println("长度不超过100");
        }
        if(!s.matches(patternStr)) {
            System.out.println("输入只包含大小写的英文字母和空格");
        }
        if (s.length()>=100) {
            System.out.println("输入长度不超过100");
        }

        if (s.contains("a")){
           s = s.replaceAll("a","A");
        }
        if (s.contains("e")){
            s = s.replaceAll("e","E");
        }
        if (s.contains("i")){
            s = s.replaceAll("i","I");
        }
        if (s.contains("o")){
            s = s.replaceAll("o","O");
        }

        System.out.println(s);
        }

        //递归
    public static int cal(int n, int m) {
        //当n=1或者m=1时，只有一种情况
        if(n == 1 || m ==1) {
            return 1;
        }
        //当n < m 时，无意义，返回原式
        if(n < m) {
            return cal(n,n);
        }
        //当n == m 时， 拆分成fun(n, m-1)和1
        if(n == m) {
            return cal(n, m-1)+1;
        }
        //当 n > m 时，可以用m进行拆分，或者用比m小的数进行拆分
        if(n > m) {
            return cal(n, m -1) + cal(n - m, m);
        }*/

        String s = "1";
        String s2 = new String("1").intern();
        System.out.println(s==s2);

    }


}
