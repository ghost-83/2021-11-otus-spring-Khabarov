package ru.ghost.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

@Service
public class IOServiceImpl implements IOService {

    private PrintStream printStream;
    private BufferedReader bufferedReader;

    @Override
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printLine(String line) {
        printStream.println(line);
    }

    @Override
    public String inputLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
