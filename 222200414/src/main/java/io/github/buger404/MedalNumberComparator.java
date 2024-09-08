package io.github.buger404;

import java.util.Comparator;

public enum MedalNumberComparator implements Comparator<MedalInfoModel.MedalNumber>
{
    INSTANCE;

    @Override
    public int compare(MedalInfoModel.MedalNumber o2, MedalInfoModel.MedalNumber o1)
    {
        if (o1.getGold() != o2.getGold())
            return o1.getGold() - o2.getGold();

        if (o1.getSilver() != o2.getSilver())
            return o1.getSilver() - o2.getSilver();

        return o1.getBronze() - o2.getBronze();
    }
}
