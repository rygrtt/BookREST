package com.bookrest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

public class KeyFinder {

    public static Key getKey() {
        // TODO: don't hard code this :(
        String path = "C:\\Users\\grott\\IdeaProjects\\BookREST\\key.txt";
        Key key = null;
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ios = new ObjectInputStream(fin);
            key = (Key) ios.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return key;
    }
}
