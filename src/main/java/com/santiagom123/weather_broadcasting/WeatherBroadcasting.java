package com.santiagom123.weather_broadcasting;

import com.santiagom123.weather_broadcasting.init.WBTileEntities;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = WeatherBroadcasting.MODID, name = WeatherBroadcasting.NAME, version = WeatherBroadcasting.VERSION, dependencies = "required-after:opencomputers@[1.8.9,);required-after:weather2remaster@[2.8.16,)")
public class WeatherBroadcasting
{
    public static final String MODID = "weather_broadcasting";
    public static final String NAME = "[Weather2Remast and OC addon] Weather Broadcasting";
    public static final String VERSION = "0.1.2";


    private static Logger logger;



    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();

        WBTileEntities.registerTileEntities();

        if (event.getSide().isClient()) {
            ClientRenderHandler.registerModels();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        logger.info("HERE: ", WeatherBroadcasting.NAME, " at ver: ", WeatherBroadcasting.VERSION, " Inicialized");

    }
}
