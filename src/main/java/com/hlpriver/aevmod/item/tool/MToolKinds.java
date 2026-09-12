package com.hlpriver.aevmod.item.tool;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.item.itemgroup.MItemGroup;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MToolKinds {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AevMod.MOD_ID);
    //注册区
    //【在XXXItem类中前两个混淆变量为p_i48530_2_、p_i48530_3_对应attackDamage和attackSpeed，
    // 请注意，PickaxeItem，HoeItem，SwordItem类的p_i48530_2_为<int>数据类型————————蝶兰注】
    //以4.0为原始攻击速度值
    //铂金工具
    //斧头
    public static final RegistryObject<Item> PLATINUM_AXE = ITEMS.register("platinum_axe",
            ()-> new AxeItem(MItemTier.PLATINUM,8.0F,-3.6F,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //镐子
    public static final RegistryObject<Item> PLATINUM_PICKAXE = ITEMS.register("platinum_pickaxe",
            ()-> new PickaxeItem(MItemTier.PLATINUM,4,-2.6F,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //锄头
    public static final RegistryObject<Item> PLATINUM_HOE = ITEMS.register("platinum_hoe",
            ()-> new HoeItem(MItemTier.PLATINUM,2,-0.5F,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //铲锹
    public static final RegistryObject<Item> PLATINUM_SHOVEL = ITEMS.register("platinum_shovel",
            ()-> new ShovelItem(MItemTier.PLATINUM,3.0F,-1.3F,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //剑
    public static final RegistryObject<Item> PLATINUM_SWORD = ITEMS.register("platinum_sword",
            ()-> new SwordItem(MItemTier.PLATINUM,1,6.0F,new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    //注册区
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
