package com.hlpriver.aevmod.item.inventory;

import com.hlpriver.aevmod.item.custom.ChestItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class PortableInventoryWrapper implements IInventory {
    private final Inventory wrapped;
    private final ItemStack stack;

    public PortableInventoryWrapper(Inventory wrapped, ItemStack stack) {
        this.wrapped = wrapped;
        this.stack = stack;
    }


    @Override
    public int getSizeInventory() {
        return wrapped.getSizeInventory();
    }

    @Override
    public boolean isEmpty() {
        return wrapped.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return wrapped.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack result = wrapped.decrStackSize(index,count);
        save();
        return result;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack result = wrapped.removeStackFromSlot(index);
        save();
        return result;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        wrapped.setInventorySlotContents(index,stack);
        save();
    }

    @Override
    public void markDirty() {
        wrapped.markDirty();
        save();
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return IInventory.super.isItemValidForSlot(index, stack);
    }

    @Override
    public void clear() {

    }
    private void save(){
        ChestItem.saveInventory(stack,wrapped);
    }
}
