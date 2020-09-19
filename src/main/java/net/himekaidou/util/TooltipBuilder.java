package net.himekaidou.util;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;

public class TooltipBuilder
{
    public static List<Text> BuildTooltip(List<Text> tooltip, List<String> descriptionKeys, List<String> quoteKeys)
    {
        for (String desc:descriptionKeys)
        {
            tooltip.add(new TranslatableText(desc));
        }
        tooltip.add(new TranslatableText(""));
        for (String quote:quoteKeys)
        {
            tooltip.add(new TranslatableText(quote));
        }
        return tooltip;
    }

    public static List<Text> BuildTooltip(List<Text> tooltip, String name, int maxDescriptionInstances, int maxQuoteInstances)
    {
        for (int i = 0; i <= maxDescriptionInstances; i++)
        {
            tooltip.add(new TranslatableText("item.himekaidou."+name+".description"+i));
        }
        tooltip.add(new TranslatableText(""));
        for (int i = 0; i <= maxQuoteInstances; i++)
        {
            tooltip.add(new TranslatableText("item.himekaidou."+name+".quote"+i));
        }
        return tooltip;
    }
}
