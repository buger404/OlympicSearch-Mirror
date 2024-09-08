package io.github.buger404;

import lombok.val;

import java.io.PrintWriter;

public class TotalCommandHandler implements CommandHandler
{
    @Override
    public String getRoute()
    {
        return "total";
    }

    @Override
    public int getRequiredArgCount()
    {
        return 0;
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        for(val info : MedalInfoUtils.getRankedCountryMedalInfo())
        {
            output.println("rank" + info.getRank() + ":" + info.getName());
            output.println("gold:" + info.getMedalNumber().getGold());
            output.println("silver:" + info.getMedalNumber().getSilver());
            output.println("bronze:" + info.getMedalNumber().getBronze());
            output.println("total:" + info.getMedalNumber().getTotal());
            output.println("-----");
        }
    }
}
