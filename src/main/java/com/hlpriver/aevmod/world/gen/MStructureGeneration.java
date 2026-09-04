package com.hlpriver.aevmod.world.gen;

import com.hlpriver.aevmod.world.structure.MStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MStructureGeneration {
    public static void GenerateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> BRK = RegistryKey.getOrCreateKey(Registry.BIOME_KEY,event.getName());
        Set<BiomeDictionary.Type> T = BiomeDictionary.getTypes(BRK);
        if (T.contains(BiomeDictionary.Type.OVERWORLD)){
            List<Supplier<StructureFeature<?,?>>> SL = event.getGeneration().getStructures();

            SL.add(() -> MStructures.SB1.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
}
