package com.lightfight.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lightfight.wechat.util.HmacSHA1;

public class WeChatServlet extends HttpServlet{

	/**  **/
	private static final long serialVersionUID = -6913633613927352172L;
	
	static final String TOKEN = "lightfight";
	
	static final String ENCODING_AES_KEY = "KwBiVFQ8jXQCn78mm1uAvDHBv7TQ3T5d8VZ79txLuEl";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String echostr = req.getParameter("echostr");
		String signature = req.getParameter("signature");
		
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		
		// 排序拼装参数
		List<String> list = new ArrayList<>();
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		
		Collections.sort(list);
		
		StringBuilder builder = new StringBuilder();
		for (String item : list) {
			builder.append(item);
		}
		String combineParas = builder.toString();
		
		try {
			String localsignature = HmacSHA1.encrypt(combineParas, ENCODING_AES_KEY);
			System.out.println("比较签名: isSame = " + localsignature.equals(signature));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = resp.getWriter();
		out.write(echostr);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
