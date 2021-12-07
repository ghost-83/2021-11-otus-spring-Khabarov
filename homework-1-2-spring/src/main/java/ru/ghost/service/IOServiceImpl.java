package ru.ghost.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;

    public IOServiceImpl() {
        this.printStream = new PrintStream(System.out);
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printLine(String line) {
        printStream.println(line);
    }

    @Override
    public void printEmptyLine() {
        printStream.println();
    }

    @SneakyThrows
    @Override
    public String inputLine() {
        return bufferedReader.readLine();
    }

    @SneakyThrows
    @Override
    public boolean inputNext() {
        return bufferedReader.ready();
    }
}
