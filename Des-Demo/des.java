package cryptology;

import java.util.Scanner;

/**
 * Created by leif on 2016/3/26 0026.
 */
public class des {
	public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("                      DES加解密分步演示                      ");
        System.out.println("**********************************************************");
        System.out.println();
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        System.out.print("请输入明文(支持任意长度字母数字组合)：");
        String mmmmmm = in.nextLine();
        System.out.print("请输入密钥(支持任意格式输入)：");
        String kkkkkk = in.nextLine();
        String[] M = util.all2string(mmmmmm);
        char[] K = util.keypre(kkkkkk);
        descore descore = new descore();
        String mimi = descore.en(M, K);
        String demimi = descore.de(util.all222string(mimi), K);
        String demimistring = util.byte2string(demimi);
        System.out.println("*************************************************");
        System.out.println("**********************结果***********************");
        System.out.println("明文："+mmmmmm);
        System.out.println("------------------------------------------------------------");
        System.out.println("加密结果：");
        System.out.println("二进制："+ mimi);
        System.out.println("十六进制："+util.two2hex(mimi));
        System.out.println("------------------------------------------------------------");
        System.out.println("解密结果：");
        System.out.println("二进制："+demimi);
        System.out.println("十六进制："+util.two2hex(demimi));
        System.out.println("编码后："+demimistring);
            
        
     
		
    }


}
