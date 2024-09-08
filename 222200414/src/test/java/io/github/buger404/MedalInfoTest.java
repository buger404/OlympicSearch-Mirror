package io.github.buger404;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MedalInfoTest
{
    @BeforeAll
    public static void setUpAll()
    {
        MedalInfoManager.initialize
        (
            OlympicSearch.class.getClassLoader().getResourceAsStream("paris2024.json")
        );
    }

    @Test
    public void medalComparatorTest1()
    {
        val number1 = new MedalInfoModel.MedalNumber();
        number1.setGold(2);
        number1.setSilver(0);
        number1.setBronze(0);
        val number2 = new MedalInfoModel.MedalNumber();
        number2.setGold(1);
        number2.setSilver(0);
        number2.setBronze(0);
        val comparator = MedalNumberComparator.INSTANCE;
        Assertions.assertTrue(comparator.compare(number1, number2) < 0);
    }

    @Test
    public void medalComparatorTest2()
    {
        val number1 = new MedalInfoModel.MedalNumber();
        number1.setGold(2);
        number1.setSilver(1);
        number1.setBronze(0);
        val number2 = new MedalInfoModel.MedalNumber();
        number2.setGold(2);
        number2.setSilver(0);
        number2.setBronze(0);
        val comparator = MedalNumberComparator.INSTANCE;
        Assertions.assertTrue(comparator.compare(number1, number2) < 0);
    }

    @Test
    public void medalComparatorTest3()
    {
        val number1 = new MedalInfoModel.MedalNumber();
        number1.setGold(2);
        number1.setSilver(1);
        number1.setBronze(1);
        val number2 = new MedalInfoModel.MedalNumber();
        number2.setGold(2);
        number2.setSilver(1);
        number2.setBronze(0);
        val comparator = MedalNumberComparator.INSTANCE;
        Assertions.assertTrue(comparator.compare(number1, number2) < 0);
    }

    @Test
    public void medalComparatorTest4()
    {
        val number1 = new MedalInfoModel.MedalNumber();
        number1.setGold(0);
        number1.setSilver(1);
        number1.setBronze(1);
        val number2 = new MedalInfoModel.MedalNumber();
        number2.setGold(2);
        number2.setSilver(1);
        number2.setBronze(0);
        val comparator = MedalNumberComparator.INSTANCE;
        Assertions.assertTrue(comparator.compare(number1, number2) > 0);
    }

    @Test
    public void medalComparatorTest5()
    {
        val number1 = new MedalInfoModel.MedalNumber();
        number1.setGold(0);
        number1.setSilver(1);
        number1.setBronze(1);
        val number2 = new MedalInfoModel.MedalNumber();
        number2.setGold(0);
        number2.setSilver(1);
        number2.setBronze(1);
        val comparator = MedalNumberComparator.INSTANCE;
        Assertions.assertTrue(comparator.compare(number1, number2) == 0);
    }

    @Test
    public void rankedInfoTest()
    {
        val rankedInfo = MedalInfoUtils.getRankedCountryMedalInfo();
        MedalInfoModel.RankedCountryMedalInfo lastInfo = null;
        for(val info : rankedInfo)
        {
            if (lastInfo != null)
            {
                if (info.getMedalNumber().getGold() != lastInfo.getMedalNumber().getGold())
                {
                    Assertions.assertTrue
                    (
                    lastInfo.getMedalNumber().getGold() > info.getMedalNumber().getGold()
                    );
                    continue;
                }

                if (info.getMedalNumber().getSilver() != lastInfo.getMedalNumber().getSilver())
                {
                    Assertions.assertTrue
                    (
                    lastInfo.getMedalNumber().getSilver() > info.getMedalNumber().getSilver()
                    );
                    continue;
                }

                if (info.getMedalNumber().getBronze() != lastInfo.getMedalNumber().getBronze())
                {
                    Assertions.assertTrue
                    (
                        lastInfo.getMedalNumber().getBronze() > info.getMedalNumber().getBronze()
                    );
                    continue;
                }

                Assertions.assertEquals(info.getRank(), lastInfo.getRank());
            }
            lastInfo = info;
        }
    }
}
