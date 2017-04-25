package ie.aib.mysecretpocket;

import java.util.Date;

public class Transaction {
    private String narrative;
    private Date date;
    private double amount;

    public Transaction() {
    }

    public Transaction(String narrative, Date date, double amount) {
        this.narrative = narrative;
        this.date = date;
        this.amount = amount;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
