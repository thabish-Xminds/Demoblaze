package com.xminds.selenium.util;

import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();
    
    // Static nested class for UserData
    public static class UserData {
        private String username;
        private String password;
        
        public UserData() {
            this.username = "testuser" + System.currentTimeMillis() + random.nextInt(1000);
            this.password = "TestPass123!";
        }
        
        // Constructor with parameters
        public UserData(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() { 
            return username; 
        }
        
        public String getPassword() { 
            return password; 
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
    }
    
    // Static nested class for CheckoutData
    public static class CheckoutData {
        private String name;
        private String country;
        private String city;
        private String creditCard;
        private String month;
        private String year;
        
        public CheckoutData() {
            this.name = "John Doe";
            this.country = "United States";
            this.city = "New York";
            this.creditCard = "1234567890123456";
            this.month = "12";
            this.year = "2025";
        }
        
        // Constructor with parameters
        public CheckoutData(String name, String country, String city, String creditCard, String month, String year) {
            this.name = name;
            this.country = country;
            this.city = city;
            this.creditCard = creditCard;
            this.month = month;
            this.year = year;
        }
        
        // Getters
        public String getName() { return name; }
        public String getCountry() { return country; }
        public String getCity() { return city; }
        public String getCreditCard() { return creditCard; }
        public String getMonth() { return month; }
        public String getYear() { return year; }
        
        // Setters
        public void setName(String name) { this.name = name; }
        public void setCountry(String country) { this.country = country; }
        public void setCity(String city) { this.city = city; }
        public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
        public void setMonth(String month) { this.month = month; }
        public void setYear(String year) { this.year = year; }
    }
    
    // Static methods to generate random data
    public static UserData generateRandomUser() {
        return new UserData();
    }
    
    public static CheckoutData generateRandomCheckoutData() {
        return new CheckoutData();
    }
    
    public static String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }
    
    public static String generateRandomPhoneNumber() {
        return "555" + (1000000 + random.nextInt(9000000));
    }
}