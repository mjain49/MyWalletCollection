package com.cg.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.bean.*;
import com.cg.exception.InsufficientFundException;
import com.cg.exception.MobileNotFoundException;
import com.cg.service.AccountService;
import com.cg.service.Gst;
import com.cg.service.Validator;

public class MyWallet {

	public static void main(String[] args) throws InsufficientFundException, IOException, MobileNotFoundException {
		// TODO Auto-generated method stub

		System.out.println("=================================================");
		System.out.println("Welcome To My Wallet Using Collection");
		System.out.println("=================================================");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AccountService ser = new AccountService();

		while (true) {
			String choice = "";
			System.out.println("Menu");
			System.out.println("=================================================");
			System.out.println("1.Create New Account");
			System.out.println("2.Find Account");
			System.out.println("3.Delete Existing Account");
			System.out.println("4.WithDraw");
			System.out.println("5.Deposit");
			System.out.println("6.Display");
			System.out.println("7.Exit");
			System.out.println("=================================================");
			System.out.println("Enter Your Choice");
			System.out.println("=================================================");
			choice = br.readLine();

			switch (choice) {
			case "1":// Create New Account
				int id = 0;
				long mb = 0L;
				String ah = "";
				double bal = 0.0;

				// Accepting and validating input for Account Number
				System.out.println("Enter Account Number");
				while (true) {
					String s_id = br.readLine();
					boolean ch1 = Validator.validatedata(s_id, Validator.aidpattern);
					if (ch1 == true) {
						try {
							id = Integer.parseInt(s_id);
							break;
						} catch (NumberFormatException e) {
							System.out.println("Account Number Must Be Numeric. Re Enter");
						}
					} else {
						System.out.println("Re Enter Account Number in 3 digits");
					}
				} // end of account number while

				// Accept Mpobile Number
				System.out.println("Enter Mobile Number");
				while (true) {
					String s_mb = br.readLine();
					boolean ch1 = Validator.validatedata(s_mb, Validator.mobilepattern);
					if (ch1 == true) {
						try {
							mb = Long.parseLong(s_mb);
							break;
						} catch (NumberFormatException e) {
							System.out.println("Mobile Number Must Be Numeric and 0f 10 number. Re Enter");
						}
					} else {
						System.out.println("Re Enter Mobile Number in 10 digits");
					}
				} // end of mobile number while

				// Accepting And Validating Account Holder
				System.out.println("Enter Account Holder Name(Example John Doe)");
				while (true) {
					String s_ah = br.readLine();
					boolean ch1 = Validator.validatedata(s_ah, Validator.namepattern);
					if (ch1 == true) {
						try {
							ah = s_ah;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Wrong Name Re Enter");
						}
					} else {
						System.out.println("Re Enter Holder Name");
					}
				}

				System.out.println("Enter Initial Balance");
				while (true) {
					String s_bal = br.readLine();

					try {
						bal = Double.parseDouble(s_bal);
					} catch (NumberFormatException e) {
						System.out.println("Balance Must Be Numeric");
					}

					if (bal < 1000)
						System.out.println("Invalid Balance, ReEnter");
					else {
						break;
					}
				}
				Account ob = new Account(id, mb, ah, bal);
				ser.addAccount(ob);
				break;

			case "2": // find Existing Account
				System.out.println("Enter Mobile Number");
				mb = Long.parseLong(br.readLine());
				ob = ser.findAccount(mb);
				if (ob != null)
					ser.printStatement(ob);
				else
					throw new MobileNotFoundException("Mobile Number Not Found");// user defined Exception

				break;
			case "3": // delete
				System.out.println("Enter Mobile Number");
				String s_mb = br.readLine();
				mb = Long.parseLong(s_mb);
				boolean ch1 = Validator.validatedata(s_mb, Validator.mobilepattern);
				if (ch1 == true) {
					ser.deleteAccount(ser.findAccount(mb));
					System.out.println("Acccount with Mobile Number= " + mb + " is Deleted Successfully!!");
				} else
					System.out.println("Invalid Mobile Number");

				break;
			case "4":// withdraw
				System.out.println("Enter Mobile Number");
				s_mb = br.readLine();
				mb = Long.parseLong(s_mb);
				ch1 = Validator.validatedata(s_mb, Validator.mobilepattern);
				if (ch1 == true) {

					System.out.println("Enter the Amount");
					bal = Double.parseDouble(br.readLine());
					ser.withdraw(ser.findAccount(mb), bal);
					ser.printStatement(ser.findAccount(mb));
					System.out.println("Withdraw Successfull!!!!");
				} else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}

				break;
			case "5":// Deposit
				System.out.println("Enter Mobile Number of Depositer");
				s_mb = br.readLine();
				long dmb = Long.parseLong(s_mb);
				ch1 = Validator.validatedata(s_mb, Validator.mobilepattern);
				if (ch1 == true) {
					ob = ser.findAccount(dmb);
					if (ob != null) {
						System.out.println("Enter Mobile Number of Repositer");
						String s_rmb = br.readLine();
						long rmb = Long.parseLong(s_rmb);
						ch1 = Validator.validatedata(s_rmb, Validator.mobilepattern);
						if (ch1 == true) {
							ob = ser.findAccount(rmb);
							if (ob != null) {
								System.out.println("Enter the Amount");
								bal = Double.parseDouble(br.readLine());
								ser.transferMoney(ser.findAccount(dmb), ser.findAccount(rmb), bal);
								System.out.println("Money Transferd Successfully");
							} else
								System.out.println("Mobile Number Not exist");
						} else
							System.out.println("Re Enter Mobile Number of Repositer in 10 digits");
					} else
						System.out.println("Mobile Number Not Exist");

				} else
					System.out.println("Re Enter Mobile Number of Deppositer in 10 digits");

				break;

			case "6": // details Printing
				Map<Long, Account> col = ser.getAllAccounts();
				Collection<Account> acclist = col.values();
				for (Account o : acclist) {
					ser.printStatement(o);
				}
				ser.getAllAccounts();
				break;

			case "7":// exit
				System.out.println("Exiting account");
				System.exit(0);
				break;

			}
		}
	}

}