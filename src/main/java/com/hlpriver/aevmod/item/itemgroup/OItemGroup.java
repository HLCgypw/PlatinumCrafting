package com.hlpriver.aevmod.item.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class OItemGroup{
    //
    public static final ItemGroup PLATINUM_TAB_TWO = new net.minecraft.item.ItemGroup("platinum_tab_two") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.IRON_INGOT);
        }
        //加入原版物品
        @Override
        public void fill(NonNullList<ItemStack> list) {
            //原版优先
            list.add(new ItemStack(Items.IRON_BLOCK));
            list.add(new ItemStack(Items.GOLD_BLOCK));
            list.add(new ItemStack(Items.DIAMOND_BLOCK));
            list.add(new ItemStack(Items.NETHERITE_BLOCK));
            //注册随后
            super.fill(list);
        }
    };



    //
}
