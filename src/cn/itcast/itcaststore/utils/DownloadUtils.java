package cn.itcast.itcaststore.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.sun.mail.util.BASE64EncoderStream;

//处理下载文件的乱码问题
public class DownloadUtils {
	public static String filenameEncoding(String filename,HttpServletRequest request) throws UnsupportedEncodingException {
		/**
		String agent=request.getHeader("User-Agent");//获取浏览器
		if (agent.contains("Firefox")) {
			BASE64EncoderStream bStream=new BASE64EncoderStream("utf-8");
			filename="=?utf-8?B"+bStream.encode(filename.getBytes("utf-8")+"?=");
		} else {
		*/
		filename=URLEncoder.encode(filename,"utf-8");
        return filename;
	}
}
