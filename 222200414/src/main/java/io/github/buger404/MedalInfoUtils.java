package io.github.buger404;

import java.time.LocalDate;
import java.util.Vector;

public class MedalInfoUtils
{
    private static Vector<MedalInfoModel.RankedCountryMedalInfo> rankedCountryMedalInfos = null;

    public static MedalInfoModel.DateMedalInfo getMedalInfoByDate(LocalDate date)
    {
        return MedalInfoManager.info.getDates().stream()
                                        .filter(x -> x.getDate().equals(date))
                                        .findFirst()
                                        .orElse(null);
    }

    public static Vector<MedalInfoModel.RankedCountryMedalInfo> getRankedCountryMedalInfo()
    {
        if (rankedCountryMedalInfos != null)
        {
            return rankedCountryMedalInfos;
        }

        rankedCountryMedalInfos = new Vector<>();

        MedalInfoModel.CountryMedalInfo lastInfo = null;

        int rank = 1, displayRank = 1;

        for(MedalInfoModel.CountryMedalInfo countryMedalInfo : MedalInfoManager.info.getCountries())
        {
            if (lastInfo == null
                    || countryMedalInfo.getMedalNumber().getGold() != lastInfo.getMedalNumber().getGold()
                    || countryMedalInfo.getMedalNumber().getSilver() != lastInfo.getMedalNumber().getSilver()
                    || countryMedalInfo.getMedalNumber().getBronze() != lastInfo.getMedalNumber().getBronze())
            {
                displayRank = rank;
            }

            MedalInfoModel.RankedCountryMedalInfo rankedInfo = new MedalInfoModel.RankedCountryMedalInfo();
            rankedInfo.setName(countryMedalInfo.getName());
            rankedInfo.setRank(displayRank);
            rankedInfo.setMedalNumber(countryMedalInfo.getMedalNumber());

            rankedCountryMedalInfos.add(rankedInfo);

            rank++;
            lastInfo = countryMedalInfo;
        }

        return rankedCountryMedalInfos;
    }

    public static MedalInfoModel.CountryMedalInfo getCountryInfo(String name) throws Exception
    {
        MedalInfoModel.CountryMedalInfo info =
                MedalInfoManager.info.getCountries().stream()
                        .filter(x -> x.getName().equals(name))
                        .findFirst()
                        .orElse(null);

        if (info == null)
        {
            throw new Exception("国家 " + name + " 不存在！");
        }

        return info;
    }

    public static MedalInfoModel.EventMedalInfo getEventInfo
            (MedalInfoModel.CountryMedalInfo country, String name) throws Exception
    {
        MedalInfoModel.EventMedalInfo info =
                country.getEvents().stream()
                        .filter(x -> x.getName().equals(name))
                        .findFirst()
                        .orElse(null);

        if (info == null)
        {
            throw new RuntimeException("国家 " + country.getName() + " 未参与项目 " + name + "！");
        }

        return info;
    }
}
