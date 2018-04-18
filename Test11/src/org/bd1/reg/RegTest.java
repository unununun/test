package org.bd1.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	public static void main(String[] args) {
		//编译
		Pattern p = Pattern.compile("");//compile（String regex）将给定的正则表达式编译到模式中返回Pattern编译模式对象
		String s="";
		//匹配器
		Matcher m = p.matcher(s);//表示匹配给定输入与此模式的匹配器返回Matcher匹配器对象。
		boolean b = m.matches();//表示全字匹配（也就是所有内容进行匹配）
		if(b){
			System.out.println("匹配");
		}else{
			System.out.println("不匹配");
		}
		//String的matchers()用的就是Pattern和Matcher对象来完成的，被String的封装了，使用起来简单
	}
}
