import java.time.LocalDate;
import java.util.Vector;

public class MedalInfoModel
{
    public static class MedalNumber
    {
        public int gold;
        public int silver;
        public int bronze;
        public int total;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MedalNumber))
            {
                return false;
            }
            MedalNumber that = (MedalNumber)obj;
            return gold == that.gold && silver == that.silver && bronze == that.bronze;
        }
    }

    public static class DateMedalInfo
    {
        public LocalDate date;
        public Vector<CompetitorMedalInfo> competitors = new Vector<>();
    }

    public static class CompetitorMedalInfo
    {
        public String name;
        public String nationality;
        public MedalNumber medalNumber;
    }

    public static class EventMedalInfo
    {
        public String name;
        public MedalNumber medalNumber;
    }

    public static class CountryMedalInfo
    {
        public String name;
        public MedalNumber medalNumber;
        public Vector<EventMedalInfo> events = new Vector<>();
    }

    public Vector<CountryMedalInfo> countries = new Vector<>();
    public Vector<DateMedalInfo> dates = new Vector<>();
}
