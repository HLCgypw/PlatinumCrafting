package com.hlpriver.aevmod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.PaintingEntity;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CPaintingEntity extends PaintingEntity {
    private Item dropItem;

    public CPaintingEntity(EntityType<? extends PaintingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public CPaintingEntity(World worldIn, BlockPos pos, Direction facing, PaintingType paintingType, Item item) {
        super(EntityType.PAINTING, worldIn);
        this.art = paintingType;
        this.dropItem = item;
        this.hangingPosition = pos;
        this.updateFacingWithBoundingBox(facing);
    }

    @Override
    public void onBroken(@Nullable Entity brokenEntity){
    if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)){
        this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
        if(brokenEntity instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) brokenEntity;
            if (playerEntity.abilities.isCreativeMode){
                return;
                }
            }
        if(dropItem != null){
            this.entityDropItem(new ItemStack(dropItem));

            }else {
                 super.onBroken(brokenEntity);
            }
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound){
        super.writeAdditional(compound);
        if (dropItem != null){
            compound.putString("dropItem", Registry.ITEM.getKey(dropItem).toString());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound){
        super.readAdditional(compound);
        if (compound.contains("dropItem")){
            this.dropItem = Registry.ITEM.getOrDefault(ResourceLocation.tryCreate(compound.getString("dropItem")));
        }
    }

    @Override
    public int getWidthPixels(){
        return  this.art == null ? 1 : this.art.getWidth();
    }

    @Override
    public int getHeightPixels(){
        return  this.art == null ? 1 : this.art.getHeight();
    }
}
