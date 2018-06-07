package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
    
/**
 * 日期工具
 * @author eric
 * @version 1.0
 */
public class DateUtil {
	SimpleDateFormat format=null;
	SimpleDateFormat firstFormat=null;
	SimpleDateFormat lastFormat=null;
	Calendar calendar=null;
	public DateUtil() {
		format=new SimpleDateFormat("yyyy-MM-dd"); //默认格式
		firstFormat=new SimpleDateFormat("yyyy-MM-dd"); //默认格式
		lastFormat=new SimpleDateFormat("yyyy-MM-dd"); //默认格式
		calendar=Calendar.getInstance(); //默认今天
	}
	
	
	/**
	 * @param date 时间
	 * @param pattern 日期格式
	 * @return 判断日期格式
	 * @throws ParseException
	 */
	public static boolean isDate(String date ,String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
		try {
			simpleDateFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param date 时间
	 * @param pattern 日期格式
	 * @return 判断日期格式
	 * @throws ParseException
	 */
	public static boolean isDate(String date ,SimpleDateFormat pattern) throws ParseException {
		try {
			pattern.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param date 入参时间
	 * @return 格式化时间
	 * @throws ParseException
	 */
	public String dateFormat(String date) throws ParseException {
		calendar.setTime(format.parse(date));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 入参时间
	 * @param pattern 时间格式
	 * @return 格式化时间
	 * @throws ParseException
	 */
	public String dateFormat(String date ,String pattern) throws ParseException {
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 入参时间
	 * @param pattern 时间格式
	 * @return 格式化时间
	 * @throws ParseException
	 */
	public String dateFormat(String date ,SimpleDateFormat pattern) throws ParseException {
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 入参时间
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 格式化时间
	 * @throws ParseException
	 */
	public String dateFormat(String date ,String firstPattern,String lastPattern) throws ParseException {
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 入参时间
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 格式化时间
	 * @throws ParseException
	 */
	public String dateFormat(String date ,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException {
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 今天日期
	 */
	public String getTaday(){
		calendar.setTime(new Date());
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param pattern 时间格式
	 * @return 今天日期
	 */
	public String getTaday(String pattern){
		calendar.setTime(new Date());
		firstFormat=new SimpleDateFormat(pattern);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param pattern 时间格式
	 * @return 今天日期
	 */
	public String getTaday(SimpleDateFormat pattern){
		calendar.setTime(new Date());
		firstFormat=pattern;
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 月初时间
	 */
	public String getFirstDayOfMonth(){
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期 
	 * @return 月初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 月初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 月初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 月初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 月初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 月末时间
	 */
	public String getLastDayOfMonth(){
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @return 月末时间
	 * @throws ParseException
	 */
	public String getLastDayOfMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 月末时间
	 * @throws ParseException
	 */
	public String getLastDayOfMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 月末时间
	 * @throws ParseException
	 */
	public String getLastDayOfMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern  出参时间格式
	 * @return 月末时间
	 * @throws ParseException
	 */
	public String getLastDayOfMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern  出参时间格式
	 * @return 月末时间
	 * @throws ParseException
	 */
	public String getLastDayOfMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @return 上月第一天
	 */
	public String getFirstDayOfLastMonth(){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 上月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 */
	public String getDayOfOffsetMonth(int offset){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, offset);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 */
	public String getDayOfOffsetMonth(String date , int offset) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, offset);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getDayOfOffsetMonth(String date,String pattern, int offset) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getDayOfOffsetMonth(String date,SimpleDateFormat pattern, int offset) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getDayOfOffsetMonth(String date,String firstPattern,String lastPattern, int offset) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getDayOfOffsetMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern, int offset) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 */
	public String getFirstDayOfOffsetMonth(int offset){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getFirstDayOfOffsetMonth(String date , int offset) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getFirstDayOfOffsetMonth(String date,String pattern, int offset) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getFirstDayOfOffsetMonth(String date,SimpleDateFormat pattern, int offset) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @param offset 偏移值
	 * @return 获取offset个月前后
	 * @throws ParseException
	 */
	public String getFirstDayOfOffsetMonth(String date,String firstPattern,String lastPattern, int offset) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @param offset 偏移值
	 * @return 上offset个月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfOffsetMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern, int offset) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	
	
	/**
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth() throws ParseException{
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfLastMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 下月第一天
	 */
	public String getFirstDayOfNextMonth(){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	

	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @return 下月最后一天
	 */
	public String getLastDayOfNextMonth(){
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @return 下月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfNextMonth(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfNextMonth(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfNextMonth(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfNextMonth(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下月最后一天
	 * @throws ParseException
	 */
	public String getLastDayOfNextMonth(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @return 年初时间
	 */
	public String getFirstDayOfYear(){
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 年初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfYear(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 年初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfYear(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 年初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfYear(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 年初时间
	 * @throws ParseException
	 */
	public String getFirstDayOfYear(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	
	/**
	 * @return 上年本月第一天
	 */
	public String getFirstDayOfMonthLastYear(){
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	
	
	/**
	 * @param date 日期
	 * @return 上年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthLastYear(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthLastYear(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthLastYear(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}

	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthLastYear(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthLastYear(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}

	/**
	 * @return 下年本月第一天
	 */
	public String getFirstDayOfMonthNextYear(){
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 下年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthNextYear(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthNextYear(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthNextYear(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}

	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthNextYear(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下年本月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfMonthNextYear(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}

	
	/**
	 * @return 上年下月第一天
	 */
	public String getFirstDayOfNextMonthLastYear(){
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 上年下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonthLastYear(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上年下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonthLastYear(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上年下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonthLastYear(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}

	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上年下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonthLastYear(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上年下月第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextMonthLastYear(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.MONTH, +1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 本季度第一天
	 */
	public String getFirstDayOfQuarter(){
		calendar.setTime(new Date());
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 本季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfQuarter(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern
	 * @return 本季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfQuarter(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 本季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfQuarter(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 本季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfQuarter(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 本季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfQuarter(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month/3*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @return 上季度第一天
	 */
	public String getFirstDayOfLastQuarter(){
		calendar.setTime(new Date());
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 上季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastQuarter(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern
	 * @return 上季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastQuarter(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 上季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastQuarter(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastQuarter(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 上季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfLastQuarter(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3-1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}

	
	/**
	 * @return 下季度第一天
	 */
	public String getFirstDayOfNextQuarter(){
		calendar.setTime(new Date());
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @return 下季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextQuarter(String date) throws ParseException{
		calendar.setTime(format.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextQuarter(String date,String pattern) throws ParseException{
		firstFormat=new SimpleDateFormat(pattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param pattern 时间格式
	 * @return 下季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextQuarter(String date,SimpleDateFormat pattern) throws ParseException{
		firstFormat=pattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return firstFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextQuarter(String date,String firstPattern,String lastPattern) throws ParseException{
		firstFormat=new SimpleDateFormat(firstPattern);
		lastFormat=new SimpleDateFormat(lastPattern);
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	/**
	 * @param date 日期
	 * @param firstPattern 入参时间格式
	 * @param lastPattern 出参时间格式
	 * @return 下季度第一天
	 * @throws ParseException
	 */
	public String getFirstDayOfNextQuarter(String date,SimpleDateFormat firstPattern,SimpleDateFormat lastPattern) throws ParseException{
		firstFormat=firstPattern;
		lastFormat=lastPattern;
		calendar.setTime(firstFormat.parse(date));
		int month=calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month/3+1)*3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return lastFormat.format(calendar.getTime());
	}
	
	
	public static void main(String[] args) throws ParseException {
		DateUtil dateUtil=new DateUtil();
		
		System.out.println(dateUtil.getFirstDayOfLastMonth());
	}
}
