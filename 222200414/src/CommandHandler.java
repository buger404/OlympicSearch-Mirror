import java.io.PrintWriter;

public interface CommandHandler
{
    String getRoute();
    void process(PrintWriter output, String[] args);
}
