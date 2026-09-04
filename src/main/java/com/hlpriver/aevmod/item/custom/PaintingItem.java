package com.hlpriver.aevmod.item.custom;

import com.hlpriver.aevmod.entity.custom.CPaintingEntity;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class PaintingItem extends Item {
    private final Supplier<PaintingType> paintingTypeSupplier;

    public PaintingItem(Supplier<PaintingType> paintingTypeSupplier, Properties properties) {
        super(properties);
        this.paintingTypeSupplier = paintingTypeSupplier;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos blockPos = context.getPos();
        Direction direction = context.getFace();
        BlockPos placePos = blockPos.offset(direction);
        ItemStack stack = context.getItem();
        PlayerEntity playerEntity = context.getPlayer();

        if (playerEntity != null && !this.canPlace(placePos, direction, playerEntity, stack)) {
            return ActionResultType.FAIL;
        }

        World world = context.getWorld();

        // 使用自定义画实体，传入对应的物品
        CPaintingEntity painting = new CPaintingEntity(
                world,
                placePos,
                direction,
                paintingTypeSupplier.get(),
                this.asItem()  // 保存当前物品，用于掉落
        );

        if (painting.onValidSurface()) {
            if (!world.isRemote) {
                painting.playPlaceSound();
                world.addEntity(painting);
            }
            stack.shrink(1);
            return ActionResultType.func_233537_a_(world.isRemote);
        } else {
            return ActionResultType.CONSUME;
        }
    }

    protected boolean canPlace(BlockPos posIn, Direction directionIn, PlayerEntity playerEntityIn, ItemStack stackIn) {
        return !directionIn.getAxis().isVertical() && playerEntityIn.canPlayerEdit(posIn, directionIn, stackIn);
    }
}
