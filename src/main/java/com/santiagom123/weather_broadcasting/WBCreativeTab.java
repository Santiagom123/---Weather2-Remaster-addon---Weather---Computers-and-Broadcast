package com.santiagom123.weather_broadcasting;

import com.santiagom123.weather_broadcasting.init.WBBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class WBCreativeTab extends CreativeTabs {

    public static final WBCreativeTab WB_TAB = new WBCreativeTab();

    public WBCreativeTab() {
        super("weather_broadcasting_tab");
    }
    @Override
    public ItemStack getTabIconItem() {

        return new ItemStack(Item.getItemFromBlock(WBBlocks.DIGITAL_RADAR));

    }
}
