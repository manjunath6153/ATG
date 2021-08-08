package com.atg.daoImpl;

import java.time.LocalDate;

import com.itextpdf.text.log.SysoCounter;

public class Samle {
	public static void main(String[] args) {
		LocalDate date = LocalDate.parse("2020-05-03");
		// Displaying date
		System.out.println("Date : "+date);
		// Add 2 months to the date
		LocalDate newDate = date.plusMonths(2); 
		System.out.println("New Date : "+newDate);
		
		System.out.println(date.compareTo(date));
		
	}
}
