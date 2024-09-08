package io.buger404;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataParseTest
{
    @Test
    public void BadDataTest()
    {
        try
        {
            MedalInfoManager.initialize
            (
                DataParseTest.class.getClassLoader().getResourceAsStream("BadData.json")
            );
        }
        catch (Exception ex)
        {
            return;
        }
        Assertions.fail();
    }
}
