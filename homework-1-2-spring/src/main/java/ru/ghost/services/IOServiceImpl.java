package ru.ghost.services;

import ru.ghost.exceptions.IOServiceException;

import java.io.*;

public class IOServiceImpl implements IOService {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;

    public IOServiceImpl(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
            throw new IOServiceException("stream read error!", e);
        }
    }
}
