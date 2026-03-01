package com.santiagom123.weather_broadcasting;

import com.santiagom123.weather_broadcasting.init.WBBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


@EventBusSubscriber({Side.CLIENT})
public class ClientRenderHandler {
   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent event) {
      Item item = Item.getItemFromBlock(WBBlocks.DIGITAL_RADAR);
      if (item != null) {
         ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(WBBlocks.DIGITAL_RADAR.getRegistryName().toString(), "inventory"));
      }
      Item item2 = Item.getItemFromBlock(WBBlocks.DIGITAL_SIREN);
      if (item2 != null) {
         ModelLoader.setCustomModelResourceLocation(item2, 0, new ModelResourceLocation(WBBlocks.DIGITAL_SIREN.getRegistryName().toString(), "inventory"));
      }
   }
}
