package com.hlpriver.aevmod.world.structure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.hlpriver.aevmod.PlatinumCore;
import com.hlpriver.aevmod.world.structure.structures.SecretBank1;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class MStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, PlatinumCore.MOD_ID);
    //registries
    public static final RegistryObject<Structure<NoFeatureConfig>> SB1 = STRUCTURE.register("secret_bank_1", SecretBank1::new);
    //
    public static void setupStructure(){
        SetupMapSpacingAndLand(SB1.get(),new StructureSeparationSettings(35,25,1234567890),true);
    }
    public static <X extends Structure<?>> void SetupMapSpacingAndLand(X structure, StructureSeparationSettings SSS, boolean TransformSurroundingLand){
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(),structure);
        if (TransformSurroundingLand) Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();
        DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.field_236191_b_).put(structure, SSS).build();
        WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap =
                    settings.getValue().getStructures().func_236195_a_();
            /*
             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods do...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
             */
            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, SSS);
                settings.getValue().getStructures().func_236195_a_();

            } else {
                structureMap.put(structure, SSS);
            }
        });
    }
    public static void register(IEventBus eventBus){
        STRUCTURE.register(eventBus);
    }
}
