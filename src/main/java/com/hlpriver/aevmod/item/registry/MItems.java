package com.hlpriver.aevmod.item.registry;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.item.custom.PaintingItem;
import com.hlpriver.aevmod.item.custom.ChestItem;
import com.hlpriver.aevmod.item.itemgroup.MItemGroup;
import com.hlpriver.aevmod.painting.MPaintingType;
import com.hlpriver.aevmod.util.MSoundEvents;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AevMod.MOD_ID);
    //注册区
    //铂金物品
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
            ()-> new Item(new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget",
            ()-> new Item(new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //音乐物品
    public static final RegistryObject<Item> PLATINUM_DISC = ITEMS.register("platinum_disc",
            ()-> new MusicDiscItem(1,
                    MSoundEvents.MADEAR,
                    new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE).maxStackSize(1)));
    //画作物品
    public static final RegistryObject<Item> FIRST_PAINTING = ITEMS.register("first_painting",
            ()-> new PaintingItem(MPaintingType.FIRST,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //弓物品
    //public static final RegistryObject<Item> PLATINUM_BOW = ITEMS.register("platinum_bow",
    //        ()-> new BowItem(new Item.Properties()
    //                .group(MItemGroup.PLATINUM_TAB_ONE)
    //                .maxStackSize(1)
    //                .maxDamage(500)));
    public static final RegistryObject<Item> AS = ITEMS.register("aqua_simulacra",()->
            new BowItem(new Item.Properties()
            .group(MItemGroup.PLATINUM_TAB_ONE)
            .maxStackSize(1)
            .maxDamage(500)));
    //convince chest
    public static final RegistryObject<Item> C_C = ITEMS.register("convince_chest",()->
            new ChestItem(new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE).maxStackSize(1)));
    //注册区

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
