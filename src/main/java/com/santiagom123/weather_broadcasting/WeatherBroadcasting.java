package com.santiagom123.weather_broadcasting;

import com.santiagom123.weather_broadcasting.init.WBBlocks;
import com.santiagom123.weather_broadcasting.init.WBTileEntities;
import com.santiagom123.weather_broadcasting.structures.StructureWeatherRadar;
import li.cil.oc.api.FileSystem;
import li.cil.oc.api.Items;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(
   modid = WeatherBroadcasting.MODID,
   name = WeatherBroadcasting.NAME,
   version = WeatherBroadcasting.VERSION,
   dependencies = "required-after:opencomputers@[1.8.9,);required-after:weather2remaster@[2.8.16,)"
)
public class WeatherBroadcasting {
   public static final String MODID = "weather_broadcasting";
   public static final String NAME = "[Weather2Remast and OC addon] Weather Broadcasting";
   public static final String VERSION = "1.2";
   private static Logger logger;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      LootTableList.register(new ResourceLocation("weather_broadcasting", "chests/weather_radar"));
      GameRegistry.registerWorldGenerator(new StructureWeatherRadar(), 0);
      logger = event.getModLog();
      WBTileEntities.registerTileEntities();
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      Items.registerFloppy("WeatherOS", EnumDyeColor.CYAN, () -> FileSystem.fromClass(WeatherBroadcasting.class, "weather_broadcasting", "opencomputers/loot/WeatherOS"), true);
      logger.info("DIGITAL RADAR >> {}", WBBlocks.DIGITAL_RADAR.getRegistryName());
      logger.info("DIGITAL SIREN >> {}", WBBlocks.DIGITAL_SIREN.getRegistryName());
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      logger.info("HERE: [Weather2Remast and OC addon] Weather Broadcasting at ver: 1.1.3 Inicialized");
   }
}
