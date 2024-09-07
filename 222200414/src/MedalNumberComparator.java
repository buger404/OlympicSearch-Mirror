import java.util.Comparator;

public class MedalNumberComparator implements Comparator<MedalInfoModel.MedalNumber>
{
    @Override
    public int compare(MedalInfoModel.MedalNumber o2, MedalInfoModel.MedalNumber o1)
    {
        if (o1.gold != o2.gold)
            return o1.gold - o2.gold;

        if (o1.silver != o2.silver)
            return o1.silver - o2.silver;

        return o1.bronze - o2.bronze;
    }
}
