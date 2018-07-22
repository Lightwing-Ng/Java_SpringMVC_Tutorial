package com.lightwing.springmvc.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * S: source 要转换的源类型
 * T: 目标，要转换成的数据类型
 *
 * @author Lightwing Ng
 */
public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        Date result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
