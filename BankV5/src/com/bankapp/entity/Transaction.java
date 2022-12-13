package com.bankapp.entity;

import java.util.Date;

public class Transaction {
	private int transaction_id;
	private Date date;
	private double transaction_ammount;
	private long account_number;
	private String transaction_details;
	
	public Transaction(Date date, double transaction_ammount, long account_number, String transaction_details) {
		super();
		this.date = date;
		this.transaction_ammount = transaction_ammount;
		this.account_number = account_number;
		this.transaction_details = transaction_details;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTransaction_ammount() {
		return transaction_ammount;
	}

	public void setTransaction_ammount(double transaction_ammount) {
		this.transaction_ammount = transaction_ammount;
	}

	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public String getTransaction_details() {
		return transaction_details;
	}

	public void setTransaction_details(String transaction_details) {
		this.transaction_details = transaction_details;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", date=" + date + ", transaction_ammount="
				+ transaction_ammount + ", account_number=" + account_number + ", transaction_details="
				+ transaction_details + "]";
	}
	
	

}
