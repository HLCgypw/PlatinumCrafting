package com.hlpriver.aevmod.world.structure.structures;

import com.hlpriver.aevmod.world.structure.SStart;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class SecretBank1 extends Structure<NoFeatureConfig> {
    public SecretBank1() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator CG, BiomeProvider BSource, long Seed, SharedSeedRandom ChunkR, int ChunkX, int ChunkZ, Biome B, ChunkPos CP, NoFeatureConfig NFConfig) {
        BlockPos ChunkCenter = new BlockPos((ChunkX << 4) + 7,0,(ChunkZ << 4) + 7);
        int GenHeight = CG.getHeight(ChunkCenter.getX(),ChunkCenter.getZ(), Heightmap.Type.WORLD_SURFACE);
        IBlockReader BlockColumn = CG.func_230348_a_(ChunkCenter.getX(),ChunkCenter.getZ());
        BlockState TopBlock = BlockColumn.getBlockState(ChunkCenter.up(GenHeight));
        return TopBlock.getFluidState().isEmpty();
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return SStart::new;
    }
}
