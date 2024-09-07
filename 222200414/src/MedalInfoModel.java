import java.util.Date;
import java.util.List;
import java.util.Vector;

public class MedalInfoModel
{
    public static class MedalNumber
    {
        public int gold;
        public int silver;
        public int bronze;
        public int total;
    }

    public static class DateMedalInfo
    {
        public Date date;
        public MedalNumber medalNumber = new MedalNumber();
    }

    public static class CompetitorMedalInfo
    {
        public String name;
        public String nationality;
        public Vector<DateMedalInfo> detail = new Vector<>();
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
    public Vector<CompetitorMedalInfo> competitors = new Vector<>();
}
