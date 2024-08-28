package ru.serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Person person = new Person("Анатолий", 35);

        serialize(person);

        person = (Person) deserialize("user.bin");
        System.out.println("Десериализованный объект: " + person);
    }

    private static void serialize(Object object) throws IOException {
        try(FileOutputStream fos = new FileOutputStream("user.bin")){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(object);
                System.out.println("oОбъект сериализован");
            }
        }
    }

    private static Object deserialize(String path) throws IOException {
        try(FileInputStream fis = new FileInputStream(new File(path))){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                return ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
