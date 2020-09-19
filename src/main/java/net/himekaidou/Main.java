package net.himekaidou;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer, ClientModInitializer
{
    public static final String MODID = "himekaidou";

    @Override
    public void onInitialize()
    {
        ItemRegistry.LoadItems();
        ItemRegistry.LoadItemsClient();
    }

    @Override
    public void onInitializeClient()
    {

    }
}

