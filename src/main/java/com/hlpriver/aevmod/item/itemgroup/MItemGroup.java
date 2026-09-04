package com.hlpriver.aevmod.item.itemgroup;

import com.hlpriver.aevmod.item.registry.MItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MItemGroup {
    //
    public static final ItemGroup PLATINUM_TAB_ONE = new net.minecraft.item.ItemGroup("platinum_tab_one") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MItems.PLATINUM_INGOT.get());
        }
    };
    //
}
