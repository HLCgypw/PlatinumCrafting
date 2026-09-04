package com.hlpriver.aevmod.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class MIModelProperties {
    public static void makeBow(Item item){
        ItemModelsProperties.registerProperty(item, new ResourceLocation("pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0F;
            } else {
                return p_239429_2_.getActiveItemStack() != p_239429_0_ ? 0.0F : (float)(p_239429_0_.getUseDuration() - p_239429_2_.getItemInUseCount()) / 20.0F;
            }
        });

        ItemModelsProperties.registerProperty(item, new ResourceLocation("pulling"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            return p_239429_2_ != null && p_239429_2_.getActiveItemStack() == p_239429_0_ ? 1.0F : 0.0F;
        });
    }
}
