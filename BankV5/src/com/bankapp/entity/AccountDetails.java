package com.bankapp.entity;

import com.bankapp.constant.AccountType;

public class AccountDetails {
	private long account_number;
	private AccountType account_type;
	private double balance;
	
	public AccountDetails(AccountType account_type, double balance) {
		super();
		this.account_type = account_type;
		this.balance = balance;
	}

	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public AccountType getAccount_type() {
		return account_type;
	}

	public void setAccount_type(AccountType account_type) {
		this.account_type = account_type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountDetails [account_number=" + account_number + ", account_type=" + account_type + ", balance="
				+ balance + "]";
	}

}
