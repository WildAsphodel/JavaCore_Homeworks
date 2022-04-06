package Lesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> newPhones = phoneBook.getOrDefault(lastName, new ArrayList<>());
        newPhones.add(phoneNumber);
        phoneBook.put(lastName, newPhones);
    }

    public HashMap<String, ArrayList<String>> getPhoneBook() {
        return phoneBook;
    }


    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "89991113355");
        phoneBook.add("Иванов", "89991345667");
        phoneBook.add("Петров", "89345554367");
        phoneBook.add("Павлова", "8924555789");
        phoneBook.add("Иванова", "89991113355");
        phoneBook.add("Сидоров", "89456671123");
        System.out.println("Номер телефона: " + phoneBook.getPhoneBook().get("Павлова"));

    }
}
