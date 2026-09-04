package com.hlpriver.aevmod.world.structure;

import com.hlpriver.aevmod.AevMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class SStart extends StructureStart<NoFeatureConfig> {
    public SStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
        super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }

    @Override
    public void func_230364_a_(DynamicRegistries DRManager, ChunkGenerator CG, TemplateManager TM, int chunkX, int chunkZ, Biome B, NoFeatureConfig NFConfig) {
        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        int x = (chunkX << 4) + 7;
        int z = (chunkZ << 4) + 7;
        // Get surface height and calculate underground depth
        int surfaceHeight = CG.getHeight(x, z, Heightmap.Type.WORLD_SURFACE);
        int undergroundY = Math.max(20, surfaceHeight - 15);

        BlockPos blockpos = new BlockPos(x, undergroundY, z);

        //addpieces()
        JigsawManager.func_242837_a(DRManager,
                new VillageConfig(() -> DRManager.getRegistry(Registry.JIGSAW_POOL_KEY)
                        .getOrDefault(new ResourceLocation(AevMod.MOD_ID, "secret_bank_1/start_pool")),
                        10), AbstractVillagePiece::new, CG, TM,
                blockpos, this.components, this.rand,false,false);

        this.components.forEach(piece -> piece.offset(0, 0, 0));
        this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

        this.recalculateStructureSize();

        LogManager.getLogger().log(Level.DEBUG, "Bank at " +
                this.components.get(0).getBoundingBox().minX + " " +
                this.components.get(0).getBoundingBox().minY + " " +
                this.components.get(0).getBoundingBox().minZ);
        }
    }
