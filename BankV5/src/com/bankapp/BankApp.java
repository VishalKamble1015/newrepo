package com.bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.BankLog.Logs;
import com.bankapp.constant.AccountType;
import com.bankapp.constant.Role;

public class BankApp {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("1.Register");
			System.out.println("2.LogIn");
			System.out.println("0. Exit");
			System.out.print("Enter Choice : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				registerCustomer();
				break;
			case 2:
				customerLogin();
				break;
			}
		
		
		}while(choice>0);
	}
	
	public static void registerCustomer() {
		
		 String username = null;
		 String fname = null;
		 String lname = null;
		 String password = null;
		 int accType=0;
		 double balance=0;
		 AccountType account_type=null;	 
		 Scanner sc = new Scanner(System.in);
		 System.out.println("---------CREATE ACCOUNT-------------");
		 
		 try {
			 
			 System.out.print("Enter First Name :");
			 fname =sc.next();
			 
			 System.out.print("Enter Last Name : ");
			 lname = sc.next();
			 
			 System.out.print("Enter User Name : ");
			 username = sc.next();
			 
			 System.out.print("Enter Password : ");
			 password = sc.next();
			 
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","manager");
			 
			 PreparedStatement st = con.prepareStatement("insert into register(username,fname,lname,password,role) values(?,?,?,?,?)");
			 st.setString(1, username);
			 st.setString(2, fname);
			 st.setString(3, lname);
			 st.setString(4, password);
			 st.setString(5, Role.customer.toString());

			 int rs = st.executeUpdate();
			 
			 PreparedStatement st2 = con.prepareStatement("select userid from register where username =? and password=?");
			 st2.setString(1,username);
			 st2.setString(2, password);
			 ResultSet rs2 = st2.executeQuery();
			 rs2.next();
			 int userid = rs2.getInt(1);
			 System.out.println(userid);
			 
			 System.out.println("Choose account type : ");
			 System.out.println("     1. Savings");
			 System.out.println("     2. Salary Account");
			 System.out.print("Enter Account Type :");
			 accType = sc.nextInt();
			 
			 
			 if(accType==1) {
				 account_type = AccountType.SAVINGS;
			 }else if(accType == 2 ) {
				 account_type = AccountType.SALARY_ACCOUNT;
			 }
			 
			 System.out.print("Enter Initial Balance :");
			 balance = sc.nextDouble();
			 
			 PreparedStatement st3 = con.prepareStatement("insert into account_details(account_type,balance,userid) values(?,?,?)");
			 st3.setString(1, account_type.toString());
			 st3.setDouble(2, balance);
			 st3.setInt(3, userid);
			 st3.executeUpdate();
			 
			 con.close();
			 
			 if(rs!=0) {
				 System.out.println("Registered Successfully !!!");
			 }else {
				 System.out.println("Registration Failed xxxx");
			 }
			
		} catch (SQLException | ClassNotFoundException | InputMismatchException e) {
			e.printStackTrace();
		}
	}
	
	public static void customerLogin() {
		String username = null;
		String password = null;
		 Scanner sc = new Scanner(System.in);
		System.out.println("---------LOG IN -------------");
		try {
			
			System.out.print("Enter User_Name : ");
			 username = sc.next();
			
			System.out.print("Enter Password : ");
			password = sc.next();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","manager");
			 
			 PreparedStatement st = con.prepareStatement("select userid from register where username =? and password=?");
			 st.setString(1,username);
			 st.setString(2, password);
			 ResultSet rs = st.executeQuery();
			 rs.next();
			 int userid = rs.getInt(1);
//			 System.out.println(userid);
			 int menuChoice = 0;
			 do {
				 System.out.println("-----MENU-----");
				 System.out.println("1. Deposite Ammount");
				 System.out.println("2. Withdarw Ammount");
				 System.out.println("3. Display Information");
				 System.out.println("0. Exit");
				 System.out.print("Enter choice : ");
				 menuChoice = sc.nextInt();
				 switch(menuChoice) {
				 case 1:
					 System.out.print("Enter Ammount to Deposite : ");
						double ammount = sc.nextDouble();
						if(ammount>50000) {
							System.out.println("Deposite limit is 50000");
						}else if(ammount<0) {
							System.out.println("ammount should not be 0");
						}
						
						deposite(userid, ammount);
						
					 break;
				 case 2:
					 System.out.print("Enter Ammount to Withdraw : ");
						double withdraw = sc.nextDouble();
						if(withdraw<0) {
							System.out.println("Enter Valid amount");
						}else if(withdraw>50000) {
							System.out.println("Withdraw Limit is 50000");
						}
						withdraw(userid, withdraw);
					 break;
				 case 3:
					 accountDetails(userid);
					 break;
				 }
			} while (menuChoice>0);
			 
			con.close();
			
		} catch (SQLException | ClassNotFoundException | InputMismatchException e) {
			// TODO: handle exception
		}
	}
	
	public static void deposite(int userid,double ammount) {
	
		try {
			
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","manager");
			 PreparedStatement st = con.prepareStatement("select balance,account_number from account_details where userid=?");
			 st.setInt(1, userid);
			 ResultSet rs = st.executeQuery();
			 rs.next();
			 double balance = rs.getDouble(1);
			 int accNo = rs.getInt(2);
			 
			 balance = balance +ammount;
			 PreparedStatement st2 = con.prepareStatement("update account_details set balance = ? where userid=?");
			 st2.setDouble(1, balance);
			 st2.setInt(2, userid);
			 
			 java.sql.Date transaction_date = java.sql.Date.valueOf(LocalDate.now());
			 PreparedStatement st3 = con.prepareStatement("insert into transaction(transaction_date,transaction_ammount,account_number,transaction_details) values(?,?,?,?)");
			 st3.setDate(1, transaction_date);
			 st3.setDouble(2, ammount);
			 st3.setInt(3, accNo);
			 st3.setString(4, "Deposite");
			 
			 st2.executeUpdate();
			 st3.executeUpdate();
			 
			 Logs.depositLog(userid, balance, accNo);
			 con.close();
			 

		} catch (SQLException | ClassNotFoundException | InputMismatchException  e) {
			e.printStackTrace();
		}
	}
	
	public static void withdraw(int userid,double ammount) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","manager");
			 PreparedStatement st = con.prepareStatement("select balance,account_number from account_details where userid=?");
			 st.setInt(1, userid);
			 ResultSet rs = st.executeQuery();
			 rs.next();
			 double balance = rs.getDouble(1);
			 int accNo = rs.getInt(2);
			 
			 balance = balance - ammount;
			 PreparedStatement st2 = con.prepareStatement("update account_details set balance = ? where userid=?");
			 st2.setDouble(1, balance);
			 st2.setInt(2, userid);
			 
			 java.sql.Date transaction_date = java.sql.Date.valueOf(LocalDate.now());
			 PreparedStatement st3 = con.prepareStatement("insert into transaction(transaction_date,transaction_ammount,account_number,transaction_details) values(?,?,?,?)");
			 st3.setDate(1, transaction_date);
			 st3.setDouble(2, ammount);
			 st3.setInt(3, accNo);
			 st3.setString(4, "Withdraw");
			 
			 st2.executeUpdate();
			 st3.executeUpdate();

			 Logs.withdrawLog(userid, balance,accNo);
			 con.close();
			 

		} catch (SQLException | ClassNotFoundException | InputMismatchException  e) {
			e.printStackTrace();
		}
	}
	
	public static void accountDetails(int userid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","manager");
			 PreparedStatement st1 = con.prepareStatement("select * from account_details where userid=?");
			 PreparedStatement st2 = con.prepareStatement("select * from register where userid=?");
			 st1.setInt(1, userid);
			 st2.setInt(1, userid);
			 ResultSet rs1 = st1.executeQuery();
			 ResultSet rs2 = st2.executeQuery();
			 rs1.next();
			 rs2.next();
			 System.out.println("------------------------------------");
				System.out.println("---- Account Information----------");
				System.out.println("------------------------------------");
				System.out.println("Name         : "+rs2.getString(3)+" "+rs2.getString(4));
				System.out.println("Account No.  : "+rs1.getInt(1));
				System.out.println("Account Type : "+rs1.getString(2));
				System.out.println("Balance      : "+rs1.getDouble(3));
				System.out.println("------------------------------------");
		} catch (SQLException | ClassNotFoundException | InputMismatchException e) {
			e.printStackTrace();
		}
	}

}
