
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public long million() {
		Date d = new Date();
		long times = d.getTime();
		long year = times / (1000 * 60 * 60 * 24 * 365);
		return year;
	}

	public long olypic() {
		try {
			Date d = new Date();
			long times = d.getTime();
			String olp = "2015-5-25";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long endTime = sdf.parse(olp).getTime();
			long st = endTime - times;
			return (st / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 在指定开始日期和结束日期之间随机生成30个日期
	 * @param args
	 */
	public void createRandomDates(){
		String start = "1978-12-22 00:00:00";
		String end = "1998-12-30 23:59:59";
		
		
	}

	public static void main(String[] args) {
		DateTest dt = new DateTest();
		System.out.println(dt.olypic());
	}
}
