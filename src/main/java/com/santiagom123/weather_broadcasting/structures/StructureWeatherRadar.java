package com.santiagom123.weather_broadcasting.structures;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class StructureWeatherRadar implements IWorldGenerator {
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
      if (world.provider.getDimension() == 0 && random.nextInt(500) == 0) {
         int x = chunkX * 16 + random.nextInt(16);
         int z = chunkZ * 16 + random.nextInt(16);
         BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
         this.generateStructure(world, pos);
      }

   }

   private void generateStructure(World world, BlockPos pos) {
      if (world instanceof WorldServer) {
         WorldServer worldServer = (WorldServer)world;
         TemplateManager manager = worldServer.getStructureTemplateManager();
         ResourceLocation location = new ResourceLocation("weather_broadcasting", "weather_radar");
         Template template = manager.get(worldServer.getMinecraftServer(), location);
         if (template != null) {
            PlacementSettings settings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos)null).setReplacedBlock((Block)null).setIgnoreStructureBlock(false);
            template.addBlocksToWorld(world, pos, settings);
         }
      }

   }
}
