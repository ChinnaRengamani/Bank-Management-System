package org.learning;

import java.security.SecureRandom;

interface Bank{
    class Account{
        long accountNo;
        String name;
        String email;
        long phoneNo;
        boolean savingacc,currentacc,debitcard,creditcard;
        double balance;

        public long getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(long accountId) {
            this.accountNo = accountId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(long phoneNo) {
            this.phoneNo = phoneNo;
        }

        public boolean isSavingacc() {
            return savingacc;
        }

        public void setSavingacc(boolean savingacc) {
            this.savingacc = savingacc;
        }

        public boolean isCurrentacc() {
            return currentacc;
        }

        public void setCurrentacc(boolean currentacc) {
            this.currentacc = currentacc;
        }

        public boolean isDebitcard() {
            return debitcard;
        }

        public void setDebitcard(boolean debitcard) {
            this.debitcard = debitcard;
        }

        public boolean isCreditcard() {
            return creditcard;
        }

        public void setCreditcard(boolean creditcard) {
            this.creditcard = creditcard;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
        public String getAccountType(){
            return savingacc?"Savings Account": "Current Account";
        }
        public Account(long accountId, String name,double balance, String email, long phoneNo, boolean savingacc, boolean debitcard) {
            this.accountNo = accountId;
            this.balance = balance;
            this.name = name;
            this.email = email;
            this.phoneNo = phoneNo;
            this.savingacc = savingacc;
            this.currentacc = !(savingacc);
            this.debitcard = debitcard;
            this.creditcard = !debitcard;

        }
    }
    boolean logins();
    Account accounts();



    long withdarwAmount(Account acc);

    long DepositAmount(Account acc);





}