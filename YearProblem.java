package lab09;

public class YearProblem {

	public static void main(String[] args) {
		
		long seconds = Long.MAX_VALUE/1000 ; // millis轉sec
		
		// 扣年
		long year = 1970 ;
		long yearSeconds = secondsInYear(year) ;
		
		// 400 年循環 because 四年一閏，百年少一閏，四百年加一閏
		// 400 年總秒數
		long totalSecIn400 = 0 ;
		for (int y = 1; y <= 400; y++) {
			totalSecIn400 += secondsInYear(y) ;
		}
		// 年份一次加
		if (seconds >= totalSecIn400) {
			long loop = seconds/totalSecIn400 ;
			year += loop * 400 ;
		}
		// 剩餘秒數
		seconds = seconds % totalSecIn400 ;
		
		while (seconds - yearSeconds > 0) {
				year ++ ;
				seconds -= yearSeconds ;
				yearSeconds = secondsInYear(year) ;
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
		int hour = 0 ; 
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
		
		System.out.printf("%d年%02d月%d日%02d時%d分%02d秒", year, month, day, hour, min, seconds);
	}
	
	public static long secondsInYear(long y) {
		long totalSeconds = 0;
		
		if((y % 4 == 0 && y % 100 != 0) || (y % 100 == 0 && y % 400 == 0)) {
			totalSeconds = 366 * 86400 ;
		}
		else {
			totalSeconds = 365 * 86400 ;
		}
		return totalSeconds;
	}
	
	public static long secondsInMonth(long y, int m) {
		long totalSeconds = 0;
		
		int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if((y % 4 == 0 && y % 100 != 0) || (y % 100 == 0 && y % 400 == 0)) {
			days[2] = 29;
		}
		else {
			days[2] = 28;
		}
		
		totalSeconds = days[m] * 86400;
		
		return totalSeconds;
	}
}
