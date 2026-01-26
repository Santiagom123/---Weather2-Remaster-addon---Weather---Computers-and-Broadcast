package com.santiagom123.weather_broadcasting.init;

import com.santiagom123.weather_broadcasting.Blocks.BlockDigitalRadar;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class WBBlocks {

    public static final Block DIGITAL_RADAR = new BlockDigitalRadar();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(DIGITAL_RADAR);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
                new ItemBlock(DIGITAL_RADAR)
                        .setRegistryName(DIGITAL_RADAR.getRegistryName())
        );
    }

}
