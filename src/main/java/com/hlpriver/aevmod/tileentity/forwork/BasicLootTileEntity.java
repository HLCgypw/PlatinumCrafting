package com.hlpriver.aevmod.tileentity.forwork;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.stream.IntStream;

public abstract class BasicLootTileEntity extends LockableLootTileEntity implements ISidedInventory {

    private final int[] slots;

    protected NonNullList<ItemStack> inventory;
    protected BasicLootTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        this.slots = IntStream.range(0,this.getSizeInventory()).toArray();
        this.inventory = NonNullList.withSize(this.getSizeInventory(),ItemStack.EMPTY);
    }

    @Override
    public int[] getSlotsForFace(Direction side){
        return slots;
    }

    @Override
    public boolean canInsertItem(int index,ItemStack itemStackIn,@Nullable Direction direction){
        return true;
    }

    @Override
    public boolean canExtractItem(int index,ItemStack itemStackEx,Direction direction){
        return true;
    }

    @Override
    public NonNullList<ItemStack> getItems(){
        return inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn){
        this.inventory = itemsIn;
    }

    @Override
    protected abstract ITextComponent getDefaultName();

    @Override
    protected abstract Container createMenu(int id, PlayerInventory player);

    @Override
    public abstract int getSizeInventory();

    @Override
    public boolean isEmpty() {
        Iterator iterator = this.inventory.iterator();
        ItemStack stack;
        do {
            if ((!iterator.hasNext())){
                return true;
            }
            stack = (ItemStack) iterator.next();
        }
        while (stack.isEmpty());
        return false;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)){
            ItemStackHelper.saveAllItems(compound,this.inventory);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.inventory = NonNullList.withSize(this.getSizeInventory(),ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt,this.inventory);
        }
    }

    public abstract void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketBuffer packetBuffer);
}
