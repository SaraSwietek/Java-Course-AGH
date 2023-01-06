package org.example;
import com.google.gson.Gson;

import javafx.application.Application;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import com.google.gson.Gson;
import org.json.simple.parser.ParseException;

public class World {
    public static void main(String[] args) throws FileNotFoundException {

        Application.launch(App.class, args);

    }
}