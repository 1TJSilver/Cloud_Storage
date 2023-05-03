package com.example.resourceserver.exceptions;

import java.io.FileNotFoundException;

public class ContentNotFoundException extends Exception {
    public ContentNotFoundException() {
        super("Error input data");
    }
}
