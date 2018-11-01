package com.accenture.testing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static com.accenture.testing.Cypher.cyph;

public class Enigma {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        loadCode();

        while (true) {

            System.out.println("Please choose comand ENC (encode) or DEC (decode). Type SAVE to save code , EXIT to exit programm");
            String comand = scn.nextLine();
            switch (comand) {
                case "ENC":
                    coding();
                    break;
                case "DEC":
                    decoding();
                    break;
                case "SAVE":
                    saveCode();
                    System.out.println("Your CODE is saved !");
                    break;
                case "EXIT":
                    System.out.println("Goodbye !");
                    return;

                default:
                    System.out.println("There is no such command ! Please type ENC or DEC");
            }
        }
    }

    private static void coding() {
        System.out.println("Please type a word to encrypt !");
        String scann = scn.nextLine();
        System.out.println(encode(scann));
    }

    private static String encode(String txt) {
        List<String> encoding = new ArrayList<>();
        for (int i = 0; i < txt.length(); i++) {
            String code = Character.toString(txt.charAt(i));
            String value = cyph.get(code);
            encoding.add(value);
        }

        return encoding.stream()
                .collect(Collectors.joining(" "));
    }

    private static void decoding() {
        System.out.println("Type your code:");
        String input = scn.nextLine();
        System.out.println(decode(input));
    }

    private static String decode(String txt) {
        List<String> decoding = new ArrayList<>();
        String[] convert = txt.split(" ");
        for (String r : convert) {
            for (String o : cyph.keySet()) {
                if (cyph.get(o).equals(r)) {
                    decoding.add(o);
                }
            }
        }
        return decoding.stream()
                .collect(Collectors.joining(""));
    }

    private static void loadCode() {
        File filus = new File("cypher_code.txt");
        try (Scanner shmeks = new Scanner(filus)) {

            while (shmeks.hasNext()) {
                String x = shmeks.next();
                String y = shmeks.next();
                if (x.equals("XYZ")) {
                    x = " ";
                    cyph.put(x, y);
                    continue;
                }
                cyph.put(x, y);
            }
        } catch (IOException e) {
            System.out.println("Cannot load from a file");
        }
    }

    private static void saveCode() {
        File filus = new File("cypher_code1.txt");
        try (PrintWriter xeks = new PrintWriter(filus)) {
            for (Map.Entry<String, String> entry : cyph.entrySet()) {
                if (entry.getKey().equals(" ")){
                    String xyz = "XYZ";
                    xeks.printf("%s %s \n",xyz, entry.getValue());
                    continue;
                }
                xeks.printf("%s %s \n ",entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }
    }

}

//        String exit = new String();
//        for (String s : encoding)
//        {
//            exit += s + "\t";
//        }
//         return exit;

//        String exit = Arrays.toString(encoding.toArray());
//         return exit;

//        String tmp = "";
//        return StringUtils.join(encoding, tmp);
