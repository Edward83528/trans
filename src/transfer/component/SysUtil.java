
package transfer.component;

import sysview.zhiren.function.SvString;

import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SysUtil {

	public static void main(String[] args) {
		// SysUtil a = new SysUtil();
		// System.out.print(a.decrypt("dXNlcg=="));

	}

	// 加密
	public static String encrypt(String encrypt_string) {
		String encrypt_value = null;
		if (encrypt_string != null && !encrypt_string.equals("null") && !encrypt_string.equals("")) {
			try {
				encrypt_value = Base64.getEncoder().encodeToString(encrypt_string.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return encrypt_value;
	}

	// 解密
	public static String decrypt(String decrypt_string) {
		String decrypt_value = null;
		if (decrypt_string != null && !decrypt_string.equals("null") && !decrypt_string.equals("")) {
			try {
				byte[] asBytes = Base64.getDecoder().decode(decrypt_string);
				decrypt_value = new String(asBytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return decrypt_value;
	}

	/** 取系統日期，傳入欲呈現的格式，如yyyyMMdd **/
	public static String nowDate(String formatstyle) {
		Format formatter = new SimpleDateFormat(formatstyle);
		Date todayDate = new Date();
		return formatter.format(todayDate);
	}

	/** 計算系統日期前幾天的日期 **/
	public static String beforeDate(String beforday) {
		if (beforday == null || beforday.equals("null") || beforday.equals(""))
			beforday = "365";
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		long beforeTime = (todayDate.getTime() / 1000) - 60 * 60 * 24 * (Integer.parseInt(beforday));
		todayDate.setTime(beforeTime * 1000);
		String beforeDate = formatter.format(todayDate);
		return beforeDate;
	}

	/** 計算系統日期後幾天的日期 **/
	public static String afterDate(String afterday) {
		if (afterday == null || afterday.equals("null") || afterday.equals(""))
			afterday = "180";
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		long afterTime = (todayDate.getTime() / 1000) + 60 * 60 * 24 * (Integer.parseInt(afterday));
		todayDate.setTime(afterTime * 1000);
		String afterDate = formatter.format(todayDate);
		return afterDate;
	}

	// 西元年轉民國年(格式：YYYY-MM-DD)
	public String getChina(String transdate) {
		String datey = String.valueOf(Integer.parseInt(transdate.substring(0, 4)) - 1911);
		String chinadate = datey + "-" + SvString.right(transdate, "-");
		return chinadate;
	}

	/** 計算2個日期天數 **/
	public static int daysBetween(String startdate, String enddate) throws ParseException {
		startdate = startdate + " 00:00:00";
		enddate = enddate + " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(enddate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days + 1));
	}

}
