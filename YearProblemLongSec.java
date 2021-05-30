package lab09;

import java.util.GregorianCalendar;

public class YearProblemLongSec {

	public static void main(String[] args) {

		long seconds = Long.MAX_VALUE/1000 ; // millis轉sec
		
		// 扣年
		int year = 1970 ;
		long yearSeconds = secondsInYear(year) ;
		// 加速計算
		int yearPass = 10000 ;
		long normalYearSeconds = 365 * 86400 ;
		
		while (seconds - yearSeconds > 0) {
			
			if (seconds - normalYearSeconds * yearPass > 0 ) {
				seconds -= normalYearSeconds * yearPass ;
				
				// 校正回歸 : 減閏年秒數差
				long correctionSeconds = leapYearInRange(year,yearPass) * 86400; 
				seconds -= correctionSeconds ;
				
				year += yearPass ;
				
			}else {
				year ++ ;
				seconds -= yearSeconds ;
				yearSeconds = secondsInYear(year) ;
			}
		}
		
		// 扣月
		int month = 1 ;
		long monthSeconds = secondsInMonth(year, month) ;
		
		while (seconds - monthSeconds > 0) {
			month ++ ;
			seconds -= monthSeconds ;
			monthSeconds = secondsInMonth(year, month) ;
		}
		
		// 扣日
		int day = 1 ;
		long daySeconds = 86400 ;
		
		while (seconds - daySeconds > 0) {
			day ++ ;
			seconds -= daySeconds ;
		}
		
		// 扣時
		int hour = 0 ; // 12點為0時
		long hourSeconds = 3600  ;
		
		while (seconds - hourSeconds > 0) {
			hour ++ ;
			seconds -= hourSeconds ;
		}
		
		// 扣分
		int min = 0 ;
		long minSeconds = 60  ;
		
		while (seconds - minSeconds > 0) {
			min ++ ;
			seconds -= minSeconds ;
		}	
		
		System.out.println(year + " 年 " + month + " 月 " + day + 
				" 日 " + hour + " 時 " + min + " 分 " + seconds + " 秒");
		
	}
	
	public static long secondsInYear(int y) {
		long totalSeconds = 0 ;
		GregorianCalendar gc = new GregorianCalendar() ;
		
		if (gc.isLeapYear(y)) {
			totalSeconds = 366 * 86400  ;
		}else {
			totalSeconds = 365 * 86400  ;
		}
		
		return totalSeconds ;
	}
	
	public static long secondsInMonth(int y, int m) {
		int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31} ;
		GregorianCalendar gc = new GregorianCalendar() ;
		
		if (gc.isLeapYear(y)) {
			monthDays[2] ++ ;
		}
		
		return monthDays[m] * 86400  ;
	}
		
	public static int leapYearInRange(int starYear, int range) {
		GregorianCalendar gc = new GregorianCalendar() ;
		int leapYearCount = 0;
			
		for(int i =0 ; i < range ; i++) {
			if (gc.isLeapYear(starYear+i)) {
				leapYearCount ++ ;
			}
		}
		return leapYearCount ;
	}

}
