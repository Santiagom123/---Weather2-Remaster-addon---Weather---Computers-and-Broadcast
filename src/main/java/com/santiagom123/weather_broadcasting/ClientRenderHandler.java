package com.santiagom123.weather_broadcasting;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import com.santiagom123.weather_broadcasting.init.WBBlocks;

public class ClientRenderHandler {

    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(WBBlocks.DIGITAL_RADAR),
                0,
                new ModelResourceLocation("weather_broadcasting:block_digital_radar", "inventory")
        );
    }
}
