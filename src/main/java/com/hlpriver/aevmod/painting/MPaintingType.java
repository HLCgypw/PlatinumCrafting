package com.hlpriver.aevmod.painting;

import com.hlpriver.aevmod.AevMod;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MPaintingType {
    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, AevMod.MOD_ID);

    public static final RegistryObject<PaintingType> FIRST = PAINTING_TYPES
            .register("first",()-> new PaintingType(80,64));
}
