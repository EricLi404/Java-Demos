package rip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;



public class Util {
	private final static String propertiesUrl = "./config/network.properties";
	

/**
 * 读取网络配置信息
 * ###网络配置信息相关文档见readme.md
 * @return hashmap
 */
	public static HashMap<String, String[]> readNetworkConfig(){
		HashMap<String, String[]> netconf = new HashMap<>();
		Properties Networkconfig = new Properties();
		try {
			Networkconfig.load(new FileInputStream(propertiesUrl));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 @SuppressWarnings("rawtypes")
		Enumeration enumm = Networkconfig.propertyNames();
		          while(enumm.hasMoreElements()) {
		              String strKey = (String) enumm.nextElement();
		              String strValue = Networkconfig.getProperty(strKey);
		              String[] strValueArray = strValue.split(",");
		              netconf.put(strKey, strValueArray);
		       }
		          return netconf;
	}
	
	/**
	 * 将int[]数组转为string
	 * @param intarr int[]
	 * @return string
	 */
	public static String intArray2string(int intarr[]){
		String string = "";
		for(int i = 0; i < intarr.length; i++){
			string += String.valueOf(intarr[i]);
		}
		return string;
	}
	
	public static String callCmd(String cmd) throws IOException, InterruptedException {
                // 使用Runtime来执行command，生成Process对象
                Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", cmd });
                // int exitCode = process.waitFor();
                // 取得命令结果的输出流
                InputStream is = process.getInputStream();
                // 用一个读输出流类去读
                InputStreamReader isr = new InputStreamReader(is);
                // 用缓冲器读行
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                }
                is.close();
                isr.close();
                br.close();
                return sb.toString();
	}
	
         
}
