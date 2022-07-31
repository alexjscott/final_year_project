package com.webapp.VehicleRental.bean;
import java.security.MessageDigest;

/**
 * Demonstrates how to generate hash using Java
 * Hong, Yi (2022) HashGenerator.java [Source code]. https://blackboard.le.ac.uk/
 */
public class HashGenerator {

    /**
     * Returns a hexadecimal encoded SHA256 hash for the input String.
     * @param data
     * @return 
     */
    
	 public static String getSHA256(String data) {
		 String result = null;
		 try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
			 byte[] hash = digest.digest(data.getBytes("UTF-8"));
			 return bytesToHex(hash); // make it printable
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
		 return result;
		 }
		 private static String bytesToHex(byte[] hash) {
			 // return DatatypeConverter.printHexBinary(hash);
			 final StringBuilder builder=new StringBuilder();
			 for(byte b:hash) {
				 builder.append(String.format("%02x", b));
		 }
		 return builder.toString();

		 }
    
}