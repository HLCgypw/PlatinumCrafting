package com.hlpriver.aevmod.item.armor;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.item.itemgroup.MItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MArmorKinds {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AevMod.MOD_ID);
    //注册区
    //铂金护甲[从上至下分别为：头——甲——护腿——靴]
    public static final RegistryObject<Item> PLATINUM_HELMET = ITEMS.register("platinum_helmet",
            ()-> new ArmorItem(MArmor.PLATINUM, EquipmentSlotType.HEAD,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate",
            ()-> new ArmorItem(MArmor.PLATINUM, EquipmentSlotType.CHEST,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings",
            ()-> new ArmorItem(MArmor.PLATINUM, EquipmentSlotType.LEGS,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    public static final RegistryObject<Item> PLATINUM_BOOTS = ITEMS.register("platinum_boots",
            ()-> new ArmorItem(MArmor.PLATINUM, EquipmentSlotType.FEET,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //铂金马护甲
    public static final RegistryObject<Item> PLATINUM_HORSE_ARMOR = ITEMS.register("platinum_horse_armor",
            ()-> new HorseArmorItem(10,"platinum",new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //注册区

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
