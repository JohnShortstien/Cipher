package com.caleb.cipher;

import java.util.Scanner;

public class Cipher {
    public static int complex(int a, int b) {
        int c = 0;
        int d = 26;
        if (a - b <0) {
            while (a != 0) {
                a--;
                c++;
            }
        }else{
            d = a;
        }
        b = b - c;
        a = d - b;
        return a;
    }

    static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static void main(String[] args) {
        StringBuilder output;
        Scanner userInput = new Scanner(System.in);
        boolean cont = true;


        while (cont) {
            String chose;
            String message;
            String encode;
            output = new StringBuilder();
            boolean cont2 = true;
            char w = 'z';
            boolean needLess = false;


            System.out.println("Do you wish to encode or decode a message?");
            chose = userInput.nextLine().toLowerCase();
            while (cont2) {
                if (chose.contains("encode")) {
                    System.out.println("Input the message you wish to encode.");
                    message = userInput.nextLine().toLowerCase();

                    System.out.println("Input the key you wish to use.");
                    encode = userInput.nextLine().toLowerCase().replaceAll("\\s","");
                    int[] encodeIndex = new int[encode.length()];

                    for(int i=0; i < encode.length(); i++){
                        char y = encode.charAt(i);
                        encodeIndex[i] = ((int) y) - 97;
                    }
                    for (int j = 0; j< message.length(); j++) {
                        char y = message.charAt(j);
                        try {
                            if (Character.isLetter(y)) {
                                int z = ((int) y) -97;
                                int x = (z + encodeIndex[j % encodeIndex.length]) % 26;
                                w = alphabet[x];
                            }else {
                                w = y;
                            }
                        }catch (Exception e) {
                            System.out.println("There was an unexpected character in your key or message.");
                            needLess = true;
                            break;
                        }finally {
                            output.append(w);
                        }


                    }
                    cont2 = false;
                } else if (chose.contains("decode")) {
                    System.out.println("Input the message you wish to decode.");
                    message = userInput.nextLine().toLowerCase();

                    System.out.println("Input the key you wish to use.");
                    encode = userInput.nextLine().toLowerCase().replaceAll("\\s","");
                    int[] encodeIndex = new int[encode.length()];

                    for(int i=0; i < encode.length(); i++){
                        char y = encode.charAt(i);
                        encodeIndex[i] = ((int) y) - 97;
                    }
                    for (int j = 0; j< message.length(); j++) {
                        char y = message.charAt(j);
                        try {
                            if (Character.isLetter(y)) {
                                int z = ((int) y) -97;
                                int x = complex(z, encodeIndex[j % encodeIndex.length]);
                                w = alphabet[x];
                            }else {
                                w = y;
                            }
                        }catch (Exception e) {
                            System.out.println("There was an unexpected character in your key or message.");
                            needLess = true;
                            break;
                        }finally {
                            output.append(w);
                        }
                    }
                    cont2 = false;
                }else {
                    System.out.println("I don't understand. Please try again.");
                    chose = userInput.nextLine().toLowerCase();
                }
            }if (!needLess) {
                System.out.println(output +"\r\n");
            }
            System.out.println("Do you want to go again?");
            String chose2 = userInput.nextLine().toLowerCase();
            if (chose2.contains("no")) {
                cont = false;
            }
        }

        userInput.close();
    }

}
