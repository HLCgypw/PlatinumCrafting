package com.hlpriver.aevmod.entity;

import com.hlpriver.aevmod.PlatinumCore;
import com.hlpriver.aevmod.entity.custom.CPaintingEntity;
import com.hlpriver.aevmod.entity.custom.ExplosiveArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, PlatinumCore.MOD_ID);

    public static final RegistryObject<EntityType<CPaintingEntity>> PAINTING_ENTITY_TYPE =
            ENTITY_TYPES.register("custom_painting",
                    ()-> EntityType.Builder.<CPaintingEntity>create(
                            CPaintingEntity::new,
                            EntityClassification.MISC
                    ).size(0.5f, 0.5f).build("custom_painting")
            );

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW_ENTITY = ENTITY_TYPES.register("",()-> EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new,
            EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4).updateInterval(20).build("explosive_arrow"));


}
