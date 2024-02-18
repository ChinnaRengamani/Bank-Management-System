package org.learning;


import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Banking b = new Banking();
        if(!b.logins()){
            System.out.println("Please Provide a Valid Username and Password");
        }
        else {
            Bank.Account val = b.accounts();
            while (true){
                val=JDBC_connection.fecting_details(Banking.con, val.getAccountNo());
                System.out.println("1.Show Details\n2.Withdraw Amount\n3.Deposit Amount\n4.logout");
                int values = Banking.sc.nextInt();
                switch (values){
                    case 1:
                        b.showAccountDetails(val);
                        break;
                    case 2:
                        b.withdarwAmount(val);
                        break;
                    case 3:
                        b.DepositAmount(val);
                        break;
                    case 4:
                        System.out.println("Loging out....");
                        System.exit(0);
                }
            }

        }


    }
}