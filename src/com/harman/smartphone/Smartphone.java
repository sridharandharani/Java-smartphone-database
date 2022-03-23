package com.harman.smartphone;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Smartphone {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option;
        while (true){
            System.out.println("Select an option :");
            System.out.println("1. Add the smartphones ");
            System.out.println("2. View all the smartphone ");
            System.out.println("3. Search phones based on brand");
            System.out.println("4. Edit the smart phone data using imei number ");
            System.out.println("5. Delete the smart phone data from db using imei number");
            System.out.println("6. Exit");
            option = in.nextInt();
            switch (option){
                case 1 :
                    try {
                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false", "root", "");
                        String imei,brand,model,price;
                        System.out.println("Enter the imei :");
                        imei = in.next();
                        System.out.println("Enter the brand :");
                        brand = in.next();
                        System.out.println("Enter the model :");
                        model = in.next();
                        System.out.println("Enter the price :");
                        price = in.next();
                        Statement stmt = c.createStatement();
                        stmt.executeUpdate("INSERT INTO `smartphone`( `imei`, `brand`, `model`, `price`) " +
                                "VALUES("+imei+",'"+brand+"','"+model+"',"+price+")");
                        System.out.println("Inserted sucessfully");

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2 :
                    System.out.println("View all smartphones :");
                    try {
                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false", "root", "");
                        Statement stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT `imei`, `brand`, `model`, `price` FROM `smartphone` WHERE 1");
                        while (rs.next()){
                            System.out.println("Imei = "+rs.getInt("imei"));
                            System.out.println("brand = "+ rs.getString("brand"));
                            System.out.println("model = "+ rs.getString("model"));
                            System.out.println("price = "+ rs.getInt("price"));
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    try {
                        String brand;
                        System.out.println("Enter the brand to be searched :");
                        brand = in.next();
                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false", "root", "");
                        Statement stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM `smartphone` WHERE `brand` = '"+brand+"'");
                        while (rs.next()){
                            System.out.println("Imei = "+rs.getInt("imei"));
                            System.out.println("model = "+ rs.getString("model"));
                            System.out.println("price = "+ rs.getInt("price"));

                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    try {
                        int imei;
                        System.out.println("Enter the imei to edit :");
                        imei = in.nextInt();
                        String brand,model,price;
                        System.out.println("Enter the brand :");
                        brand = in.next();
                        System.out.println("Enter the model :");
                        model = in.next();
                        System.out.println("Enter the price :");
                        price = in.next();

                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false", "root", "");
                        Statement stmt = c.createStatement();
                        stmt.executeUpdate("UPDATE `smartphone` SET `brand`='"+brand+"',`model`='"+model+"',`price`= "+price+" WHERE `imei` ="+imei);
                        System.out.println("Updated sucessfully");

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 5:
                    try {
                        int imei;
                        System.out.println("Enter the imei to be deleted :");
                        imei = in.nextInt();
                        Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphonedb?autoReconnect=true&useSSL=false", "root", "");
                        Statement stmt = c.createStatement();
                        stmt.executeUpdate("DELETE FROM `smartphone` WHERE `imei`="+imei);
                        System.out.println("Deleted sucessfully");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid syntax");

            }
        }
    }
}
