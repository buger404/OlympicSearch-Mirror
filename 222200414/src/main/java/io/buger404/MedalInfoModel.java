package io.buger404;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MedalInfoModel
{
    @Data
    public static class MedalNumber
    {
        private int gold;
        private int silver;
        private int bronze;
        private int total;
    }

    @Data
    public static class DateMedalInfo
    {
        private LocalDate date;
        private List<CompetitorMedalInfo> competitors = new ArrayList<>();
    }

    @Data
    public static class CompetitorMedalInfo
    {
        private String name;
        private String nationality;
        private MedalNumber medalNumber;
    }

    @Data
    public static class EventMedalInfo
    {
        private String name;
        private MedalNumber medalNumber;
    }

    @Data
    public static class RankedCountryMedalInfo
    {
        private String name;
        private int rank;
        private MedalNumber medalNumber;
    }

    @Data
    public static class CountryMedalInfo
    {
        private String name;
        private MedalNumber medalNumber;
        private List<EventMedalInfo> events = new ArrayList<>();
    }

    private List<CountryMedalInfo> countries = new ArrayList<>();
    private List<DateMedalInfo> dates = new ArrayList<>();
}
