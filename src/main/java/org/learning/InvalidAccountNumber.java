package org.learning;

public class InvalidAccountNumber  extends  Exception{
    public InvalidAccountNumber(String s) {
        System.out.println(s);
        System.exit(0);
    }
}
