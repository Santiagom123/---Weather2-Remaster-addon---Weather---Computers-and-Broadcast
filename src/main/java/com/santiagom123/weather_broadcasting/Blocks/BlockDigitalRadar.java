package com.santiagom123.weather_broadcasting.Blocks;

import com.santiagom123.weather_broadcasting.WBCreativeTab;
import com.santiagom123.weather_broadcasting.TileEntitys.TileDigitalRadar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDigitalRadar extends Block {
   public BlockDigitalRadar() {
      super(Material.ROCK);
      this.setHardness(1.5F);
      this.setResistance(10.0F);
      this.setUnlocalizedName("block_digital_radar");
      this.setRegistryName("block_digital_radar");
      this.setCreativeTab(WBCreativeTab.WB_TAB);
   }

   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileDigitalRadar();
   }
}
