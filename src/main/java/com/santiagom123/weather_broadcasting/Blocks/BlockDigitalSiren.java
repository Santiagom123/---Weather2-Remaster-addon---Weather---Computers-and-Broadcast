package com.santiagom123.weather_broadcasting.Blocks;

import com.santiagom123.weather_broadcasting.TileEntitys.TileDigitalSiren;
import com.santiagom123.weather_broadcasting.WBCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDigitalSiren extends Block {
    public BlockDigitalSiren() {
        super(Material.ROCK);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setUnlocalizedName("block_digital_siren");
        this.setRegistryName("block_digital_siren");
        this.setCreativeTab(WBCreativeTab.WB_TAB);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileDigitalSiren();
    }
}
