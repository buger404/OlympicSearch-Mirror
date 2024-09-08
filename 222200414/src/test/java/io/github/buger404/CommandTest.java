package io.github.buger404;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Scanner;

public class CommandTest
{
    @BeforeAll
    public static void setUpAll()
    {
        MedalInfoManager.initialize
        (
            OlympicSearch.class.getClassLoader().getResourceAsStream("paris2024.json")
        );
        CommandManager.initialize();
    }

    private void isResultCorrect(String testCase)
    {
        Scanner scanner = new Scanner
        (
            Objects.requireNonNull
            (
                this.getClass().getClassLoader().getResourceAsStream(testCase + ".in")
            ),
            "UTF-8"
        );

        Scanner answerScanner = new Scanner
        (
            Objects.requireNonNull
            (
                this.getClass().getClassLoader().getResourceAsStream(testCase + ".out")
            ),
            "UTF-8"
        );

        StringWriter stringWriter = new StringWriter();
        PrintWriter output = new PrintWriter(stringWriter);

        while (scanner.hasNextLine())
        {
            CommandManager.processCommandLine(scanner.nextLine(), output);
        }

        Scanner outputScanner = new Scanner(stringWriter.toString());

        while (answerScanner.hasNextLine())
        {
            Assertions.assertTrue(outputScanner.hasNextLine());
            Assertions.assertEquals(answerScanner.nextLine(), outputScanner.nextLine());
        }

        Assertions.assertFalse(outputScanner.hasNextLine());

        scanner.close();
        answerScanner.close();
        outputScanner.close();
    }

    @Test
    public void unknownCommandTest()
    {
        isResultCorrect("UnknownCommand");
    }

    @Test
    public void badDateTest()
    {
        isResultCorrect("BadDateArgument");
    }

    @Test
    public void incorrectArgumentCountTest()
    {
        isResultCorrect("IncorrectArgumentCount");
    }

    @Test
    public void incorrectArgumentTest()
    {
        isResultCorrect("IncorrectArgument");
    }

    @Test
    public void totalTest()
    {
        isResultCorrect("Total");
    }

    @Test
    public void resultTest()
    {
        isResultCorrect("Result");
    }

    @Test
    public void pkTest()
    {
        isResultCorrect("PK");
    }

    @Test
    public void mixedTest()
    {
        isResultCorrect("MixedCommands");
    }
}
