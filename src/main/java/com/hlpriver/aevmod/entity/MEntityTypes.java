package com.hlpriver.aevmod.entity;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.entity.custom.CPaintingEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, AevMod.MOD_ID);

    public static final RegistryObject<EntityType<CPaintingEntity>> PAINTING_ENTITY_TYPE =
            ENTITY_TYPES.register("custom_painting",
                    ()-> EntityType.Builder.<CPaintingEntity>create(
                            CPaintingEntity::new,
                            EntityClassification.MISC
                    ).size(0.5f, 0.5f).build("custom_painting")
            );


}
