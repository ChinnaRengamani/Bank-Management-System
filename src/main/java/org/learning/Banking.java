package org.learning;

import java.sql.*;
import java.util.*;

public class Banking  implements Bank{
    static Scanner sc = new Scanner(System.in);
    static Connection con = JDBC_connection.getConnection();
    @Override
    public boolean logins() {
        System.out.println("Enter the Username: ");
        String username = sc.nextLine();
        System.out.println("Enter the Password: ");
        String password = sc.nextLine();
        String query = "select count(*) as val from userdetails where username = '"+username+"' and password = '"+password+"' ;";
        return JDBC_connection.logins_checking(con,query);

    }

    @Override
    public Account accounts() {
        System.out.println("Do You have an Account: (Y/n)");
        String yes = sc.nextLine();
        if(yes.equalsIgnoreCase("Y")){
            System.out.println("Enter the Account Number: ");
            long accountno = sc.nextLong();
            return JDBC_connection.fecting_details(con, accountno);
        }
        else{
            System.out.println("Enter the username for your Account: ");
            String username = sc.nextLine();
            System.out.println("Enter the password for your Account: ");
            String password = sc.nextLine();
            System.out.println("Creating Account ....");
            if(JDBC_connection.signup(con,username,password)){
                System.out.println("Account Created ...");
                System.out.println("Generating Account Number ....");
                long accno = JDBC_connection.retrivelastaccountno(con);
                System.out.println("Your Account Number: "+accno+1);
                System.out.println("Enter your Email Id: ");
                String email = sc.next();
                System.out.println("Enter your Phone Number: ");
                long phoneNo = sc.nextLong();
                System.out.println("Your Account Type is Savings Account Now. \nSo Minimum balance should be Rs.1000 \nDeposit your Money in your new Account with minimum of 1100");
                double balance = sc.nextDouble();
                System.out.println("Your Debit card will be arrive by 1 week");
                if(JDBC_connection.addaccount_details(con,accno+1,balance,username,email, phoneNo,"savingsacc","debitcard")>0){
                    System.out.println("Account Details Updated ...");
                }
                else System.out.println("Account Details not updated...");
            }
        }
        return null;
    }


    @Override
    public long withdarwAmount(Account acc) {
        System.out.println("\nEnter the Amount to be withdrawn");
        double amount = sc.nextDouble();
        try {
            if(amount<acc.getBalance()){
                String sql = "update bankaccount set balance = ? where accountNo = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setDouble(1,acc.getBalance()-amount);
                st.setLong(2,acc.getAccountNo());
                int v = st.executeUpdate();
                return (long) v;
            }
            else {
                System.out.println("Insufficient Balance");
            }

        }
        catch (SQLException ignored){

        }
        return -5;
    }

    @Override
    public long DepositAmount(Account acc) {
        System.out.println("\nEnter the Amount to be Deposit");
        double amount = sc.nextDouble();
        try {

                String sql = "update bankaccount set balance = ? where accountNo = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setDouble(1,acc.getBalance()+amount);
                st.setLong(2,acc.getAccountNo());
                int v = st.executeUpdate();
                return (long) v;


        }
        catch (SQLException ignored){

        }
        return 0;
    }

    public void showAccountDetails(Account val){
        System.out.println("\nAccount Number: "+val.getAccountNo());
        System.out.println("Balance: "+val.getBalance());
        System.out.println("Username: "+val.getName());
        System.out.println("Email ID: "+val.getEmail());
        System.out.println("Phone No: "+val.getPhoneNo());
        System.out.println("Account Type: "+val.getAccountType());
        System.out.println();
    }

}


