import java.io.PrintWriter;

public interface CommandHandler
{
    String getRoute();
    int getRequiredArgCount();
    void process(PrintWriter output, String[] args);
}
