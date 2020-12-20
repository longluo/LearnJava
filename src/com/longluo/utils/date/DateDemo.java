package com.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

    public static void main(String[] args) {
        // long time = 1426723200000L;
        //
        // Timestamp unixTime = new Timestamp(time);
        //
        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //
        // System.out.println("d=" + format.format(unixTime));
        //
        // timeStamp2Date(1426723200, "yyyy-MM-dd");

        System.out.println("" + new Date() + "," + DateUtils.getNowTime());

        System.out.println(" " + DateUtils.getNowTimeWithoutDate());

        System.out.println("1. time=" + Long.parseLong("1444186498491"));

        System.out.println("2. time=" + System.currentTimeMillis());


        String temp = "2015-10-09 10:31:36:111";
        System.out.println("final: " + DateUtils.dateToUnixTimestamp(temp));
		
		
/*		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_LONG_STR);
		String str = sdf.format(temp);
		System.out.println("time: " + str);

		try {
			System.out.println("time:" + sdf.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

        // addDemo();
    }

    public static void addDemo() {
        int temp = 1429670800;
        for (int i = 0; i < 8; i++) {
            temp += 86400;
            System.out.println(" " + temp);
        }
    }

    public static String timeStamp2Date(int timeStamp, String formats) {
        long timeStampLong = (long) timeStamp * 1000;

        String date = new SimpleDateFormat(formats).format(new Date(
                timeStampLong));

        System.out.printf("date=" + date + ",year=" + date.substring(0, 4)
                + ",month=" + date.substring(5, 7));

        return date;
    }

}
