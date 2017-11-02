package com.situ.crm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换的Converter
 * S : 页面传递过来的类型
 * T ： 转换后的类型
 */


public class DateConveter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
	       try {
	           if(null != source){//2017-07-14
	               DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	               return df.parse(source);
	           }
	       } catch (Exception e) {
	           e.toString();
	       }
	       return null;
	    }

}
