package com.hlpriver.aevmod.item.armor;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.item.registry.MItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum MArmor implements IArmorMaterial {
    //注册【所用物品的材料】
    PLATINUM("platinum",
            80,
            new int[]{5, 8, 10, 5},
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            5.0F,
            0.2F,
            () -> Ingredient.fromItems(MItems.PLATINUM_INGOT.get()));

    //(以下为各变量名的含义：)
    //name[材质名称]
    //maxDamageFactor[耐久系数]
    //damageReductionAmountArray[减伤值数组]
    //enchantability[附魔兼容性]
    //soundEvent[装备音效]
    //toughness[盔甲韧性]
    //knockbackResistance[击退抗性]
    //LazyValue<Ingredient> repairMaterial[修复材料（延迟加载）————————蝶兰注]
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    MArmor(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue(repairMaterial);
    }

    public int getDurability(EquipmentSlotType p_200896_1_) {
        return MAX_DAMAGE_ARRAY[p_200896_1_.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType p_200902_1_) {
        return this.damageReductionAmountArray[p_200902_1_.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return AevMod.MOD_ID+":"+this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
