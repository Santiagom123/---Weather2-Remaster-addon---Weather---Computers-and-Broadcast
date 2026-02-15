package com.santiagom123.weather_broadcasting.init;

import com.santiagom123.weather_broadcasting.TileEntitys.TileDigitalRadar;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WBTileEntities {
   public static void registerTileEntities() {
      GameRegistry.registerTileEntity(TileDigitalRadar.class, "weather_broadcasting:tile_digital_radar");
   }
}
