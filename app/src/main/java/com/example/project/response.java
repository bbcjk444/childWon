package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class response {
   ArrayList<String> arrayList = new ArrayList<>();

    public String getLogcatLog() {
        StringBuilder log = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = ""; while ((line = bufferedReader.readLine()) != null) {
                log.append(line); log.append("\n");
            }
        }
        catch (IOException e) {

        } return log.toString();
    }


}
