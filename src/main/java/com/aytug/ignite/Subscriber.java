package com.aytug.ignite;

import java.io.Serializable;

public class Subscriber implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customerId;
    private double dataUsage;
    private int smsUsage;
    private int callUsage;

    public Subscriber(int customerId, double dataUsage, int smsUsage, int callUsage) {
        this.customerId = customerId;
        this.dataUsage = dataUsage;
        this.smsUsage = smsUsage;
        this.callUsage = callUsage;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public double getDataUsage() { return dataUsage; }
    public void setDataUsage(double dataUsage) { this.dataUsage = dataUsage; }

    public int getSmsUsage() { return smsUsage; }
    public void setSmsUsage(int smsUsage) { this.smsUsage = smsUsage; }

    public int getCallUsage() { return callUsage; }
    public void setCallUsage(int callUsage) { this.callUsage = callUsage; }

    @Override
    public String toString() {
        return "Müşteri ID: " + customerId + 
               " | Data: " + String.format("%.2f", dataUsage) + " GB" + 
               " | SMS: " + smsUsage + 
               " | Çağrı: " + callUsage + " dk";
    }
}