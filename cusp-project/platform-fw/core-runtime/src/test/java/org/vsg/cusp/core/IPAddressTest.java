package org.vsg.cusp.core;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class IPAddressTest {

    public static void main(String[] args) throws IOException, URISyntaxException{
    	/*
        try{    
                //获得本机的InetAddress信息    
                InetAddress IP = InetAddress.getLocalHost();    
                showInfo(IP);    
                 
                //从名字获得 InetAddress信息    
                //IP = InetAddress.getByName("www.sina.com.cn");    
                //showInfo(IP);    
                 
                //从IP 地址 获得 InetAddress信息    
                byte[] bs = new byte[]{(byte)192,(byte)168,(byte)0,(byte)102};    
                //IP = InetAddress.getByAddress(bs);
                
                byte[] mac = NetworkInterface.getByInetAddress(IP).getHardwareAddress();  
                
                String cuid = CommonUtils.getUid(mac);

                //下面代码是把mac地址拼装成String  
                StringBuffer sb = new StringBuffer();  
                  
                for(int i=0;i<mac.length;i++){  
                    if(i!=0){  
                        sb.append("-");  
                    }  
                    //mac[i] & 0xFF 是为了把byte转化为正整数  
                    String s = Integer.toHexString(mac[i] & 0xFF);  
                    sb.append(s.length()==1?0+s:s);  
                } 
                
                System.out.println(sb.toString());
                showInfo(IP);    
        }    
        catch(java.net.UnknownHostException e){    
                e.printStackTrace();    
        }
        */
    	
    	
    	Enumeration<URL>  urls =  IPAddressTest.class.getClassLoader().getResources("META-INF/modules");
    	
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			File file = new File(url.toURI());
			System.out.println(file.exists());
		}
    	
    }    
//将InetAddress 中的信息显示出来    
public static void showInfo(InetAddress IP){    
        String name = IP.getHostName();    
        String address = IP.getHostAddress();    
        System.out.println(name);    
        System.out.println(address);    
        System.out.println("------------------------------");    
}  

}
