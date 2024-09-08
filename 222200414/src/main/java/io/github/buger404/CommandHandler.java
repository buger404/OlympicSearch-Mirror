package io.github.buger404;

import java.io.PrintWriter;

public interface CommandHandler
{
    String getRoute();
    int getRequiredArgCount();
    void process(PrintWriter output, String[] args);
}
