package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the message you want to encrypt: ");
        String originalMessage = scan.nextLine();
        String encryptedData = encryptStringData(originalMessage);
        String decryptedData = decryptStringData(encryptedData);

        // Print out the originalMessage, encryptedData and decryptedData
        System.out.println("Original message is: " + originalMessage);
        System.out.println("Encrypted message is: " + encryptedData);
        System.out.println("Decrypted message is: " + decryptedData);

        // Verify that the original message is the same as the decrypted data
        System.out.println("Decrypted data == Original message: " + originalMessage.equals(decryptedData));
        scan.close();
    }

    // Method to encrypt string data using the given algorithm -- Sender's end
    public static String encryptStringData(String data) {
        int dataSize = data.length();
        // 1. The minimum number of characters in the string must be 3
        if (dataSize < 3) {
            return "Invalid input! Data must be a string with three or more characters";
        }
        // 2. Split the string into two roughly equal substrings, leftHalf and rightHalf
        int mid = data.length() / 2;
        String leftHalf = data.substring(0, mid);             // Let leftHalf = A and rightHalf = B
        String rightHalf = data.substring(mid, dataSize);

        // 3. Reverse the substrings
        String reversedLeftHalf = reverseString(leftHalf);
        String reversedRightHalf = reverseString(rightHalf);

        // 4. Further encrypt the string by replacing vowels with the given numbers
        String encodedReversedLeftHalf = encodeVowelsIntoNumbers(reversedLeftHalf);
        String encodedReversedRightHalf = encodeVowelsIntoNumbers(reversedRightHalf);

        // 5. Concatenate the encrypted substrings encodedReversedLeftHalf and encodedReversedRightHalf
        // with encodedReversedRightHalf coming after encodedReversedLeftHalf.
        return encodedReversedRightHalf + encodedReversedLeftHalf;
    }


    // Method to decrypt string data using the given algorithm -- Receiver's end
    public static String decryptStringData(String data) {
        int dataSize = data.length();
        // 2. Split the string into two roughly equal substrings, leftHalf and rightHalf
        int mid = data.length() / 2;
        String leftHalf = data.substring(0, mid);
        String rightHalf = data.substring(mid, dataSize);

        // 3. Reverse the substrings
        String reversedLeftHalf = reverseString(leftHalf);
        String reversedRightHalf = reverseString(rightHalf);

        // 4. Convert numbers in the encoded string to their respective vowel equivalent
        String decodedReversedLeftHalf = decodeNumbersIntoVowels(reversedLeftHalf);
        String decodedReversedRightHalf = decodeNumbersIntoVowels(reversedRightHalf);

        // 5. Concatenate the decrypted substrings decodedReversedLeftHalf and decodedReversedRightHalf
        // with decodedReversedRightHalf coming after decodedReversedLeftHalf.
        return decodedReversedRightHalf + decodedReversedLeftHalf;
    }

    // Public method to reverse a string
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    // Public method to encode vowels into numbers
    public static String encodeVowelsIntoNumbers(String str) {
        HashMap<Character, Integer> map = new HashMap<>(
                Map.of('a', 1, 'e', 5, 'i', 3, 'o', 2, 'u', 4)
        );
        StringBuilder encodedStr = new StringBuilder();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (map.containsKey(c))
                encodedStr.append(map.get(c));
            else
                encodedStr.append(c);
        }
        return encodedStr.toString();
    }

    // Public method to decode numbers into vowels
    public static String decodeNumbersIntoVowels(String str) {
        HashMap<Character, Character> map = new HashMap<>(
                Map.of('1', 'a', '5', 'e', '3', 'i', '2', 'o', '4', 'u')
        );
        StringBuilder encodedStr = new StringBuilder();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (map.containsKey(c))
                encodedStr.append(map.get(c));
            else
                encodedStr.append(c);
        }
        return encodedStr.toString();
    }

}
