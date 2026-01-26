package com.santiagom123.weather_broadcasting.Blocks;

import com.santiagom123.weather_broadcasting.TileEntitys.TileDigitalRadar;

import com.santiagom123.weather_broadcasting.WBCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockDigitalRadar extends Block {

    public BlockDigitalRadar() {

        super(Material.ROCK);
        setHardness(1.5F);
        setResistance(10.0F);
        setUnlocalizedName("block_digital_radar");
        setRegistryName("block_digital_radar");
        setCreativeTab(WBCreativeTab.WB_TAB);


    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileDigitalRadar();
    }
}
