package com.ecs.android.sample.listview;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String getDateFormatted(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		return formatter.format(date);
	}
}
