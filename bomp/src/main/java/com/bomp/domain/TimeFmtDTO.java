package com.bomp.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TimeFmtDTO {
	
	private String timeStr;
	private final long NOW = System.currentTimeMillis();
	private SimpleDateFormat sdf = new SimpleDateFormat("`yy. MM. dd");
	
	public TimeFmtDTO() {}
	
	public TimeFmtDTO(Date time) {
		this.timeStr = countTime(time.getTime());
	}
	
	public String getTimeFmtSetDate(Date time) {
		this.timeStr = countTime(time.getTime());
		return timeStr;
	}
	
	protected String countTime(long timeTemp) {
		if(NOW-timeTemp<30*24*60*60*1000 && NOW-timeTemp>=7*24*60*60*1000) {
			return (NOW - timeTemp) / (7*24*60*60*1000) + "주 전";
		} else if(NOW-timeTemp<7*24*60*60*1000 && NOW-timeTemp>=24*60*60*1000) {
			return (NOW - timeTemp) / (24*60*60*1000) + "일 전";
		} else if(NOW-timeTemp<24*60*60*1000 && NOW-timeTemp>=60*60*1000) {
			return (NOW - timeTemp) / (60*60*1000) + "시간 전";
		} else if(NOW-timeTemp<60*60*1000 && NOW-timeTemp>=60*1000) {
			return (NOW - timeTemp) / (60*1000) + "분 전";
		} else if(NOW-timeTemp<60*1000 && NOW-timeTemp>=1000) {
			return (NOW - timeTemp) / (1000) + "초 전";
		}  else if(NOW-timeTemp<1000 && NOW-timeTemp>=0) {
			return "지금";
		} else {	
			return sdf.format(timeTemp);
		}
	}
}
