package com.santiagom123.weather_broadcasting.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDopplerDebug extends Block {
   public static final PropertyDirection FACING;

   public BlockDopplerDebug() {
      super(Material.IRON);
      this.setHardness(2.0F);
      this.setResistance(10.0F);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }

   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      EnumFacing dir = (EnumFacing)state.getValue(FACING);
      EnumFacing dir2 = dir.rotateY();
      EnumFacing dir3 = dir.rotateYCCW();
      BlockPos destC1 = blockPos.offset(dir, 32);
      blockPos.offset(dir, 48);
      blockPos.offset(dir, 64);
      blockPos.offset(dir, 80);
      blockPos.offset(dir, 112);
      blockPos.offset(dir, 144);
      blockPos.offset(dir, 192);
      blockPos.offset(dir, 240);
      blockPos.offset(dir, 304);
      blockPos.offset(dir, 368);
      blockPos.offset(dir, 448);
      blockPos.offset(dir, 496);
      BlockPos destI1C2 = blockPos.offset(dir, 48).offset(dir2, 16);
      blockPos.offset(dir2, 16);
      if (!world.isRemote) {
         world.setBlockState(destC1, Blocks.LAPIS_BLOCK.getDefaultState());
      }

      return true;
   }

   public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   static {
      FACING = BlockHorizontal.FACING;
   }
}
