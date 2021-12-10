package ru.ghost.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public interface IOService {

    void setBufferedReader(BufferedReader bufferedReader);

    void setPrintStream(PrintStream printStream);

    void printLine(String line);

    String inputLine() throws IOException;
}
