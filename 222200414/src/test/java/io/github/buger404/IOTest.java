package io.github.buger404;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class IOTest
{
    @TempDir
    Path tempDir;

    @Test
    public void WrongIOArgumentTest()
    {
        OlympicSearch.main(new String[]{});
        Assertions.assertFalse(tempDir.resolve("output1.txt").toFile().exists());
    }

    @Test
    public void InputFileNotFoundTest()
    {
        OlympicSearch.main
        (
            new String[]
            {
                tempDir.resolve("input2.txt").toAbsolutePath().toString(),
                tempDir.resolve("output2.txt").toAbsolutePath().toString()
            }
        );
        Assertions.assertEquals(0, tempDir.resolve("output2.txt").toFile().length());
    }

    @Test
    public void InvalidOutputPathTest() throws IOException
    {
        Files.write(tempDir.resolve("input3.txt"), "result 0910".getBytes(StandardCharsets.UTF_8));
        Files.createFile(tempDir.resolve("output3.txt"));
        Assertions.assertTrue(tempDir.resolve("output3.txt").toFile().setReadOnly());

        OlympicSearch.main
        (
            new String[]
            {
                tempDir.resolve("input3.txt").toAbsolutePath().toString(),
                tempDir.resolve("output3.txt").toAbsolutePath().toString()
            }
        );

        Assertions.assertEquals(0, tempDir.resolve("output3.txt").toFile().length());
    }

    @Test
    public void NormalIOTest() throws IOException
    {
        Files.write(tempDir.resolve("input4.txt"), "result 0910".getBytes(StandardCharsets.UTF_8));

        OlympicSearch.main
        (
            new String[]
            {
                tempDir.resolve("input4.txt").toAbsolutePath().toString(),
                tempDir.resolve("output4.txt").toAbsolutePath().toString()
            }
        );

        Scanner scanner = new Scanner(tempDir.resolve("output4.txt").toFile(), "UTF-8");
        Assertions.assertEquals(scanner.nextLine(), "N/A");
        Assertions.assertEquals(scanner.nextLine(), "-----");
        Assertions.assertFalse(scanner.hasNextLine());
        scanner.close();
    }
}
