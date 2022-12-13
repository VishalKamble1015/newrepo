package com.BankLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class Logs {

	static Logger log=Logger.getLogger(Logs.class.getName());
	
	public static void withdrawLog(int userid,double balance,int accNo) {
		PropertyConfigurator.configure("log4j.properties");
//		LocalDateTime dateObj=LocalDateTime.now();
//		DateTimeFormatter 
        log.info("withdraw ammout: "+balance+"  userid: "+userid+"Account Number: "+accNo); 
        
	}
	public static void depositLog(int userid,double balance,int accNo) {
		PropertyConfigurator.configure("log4j.properties");
		
        log.info("deposit ammout: "+balance+"  userid: "+userid+"Account Number: "+accNo); 
        
	}
}
