package com.hlpriver.aevmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class CChestContainer extends Container {
    private final IInventory Cinventory;
    private final int ChestNumRows;
    private final int NUM_ROWS = 9;
    //废物端--这个是梗（Server）
    public CChestContainer(int id, PlayerInventory playerInventory, IInventory CinventoryA, int NumRows) {
        super(MContainers.CHEST_9X9_CONTAINER.get(), id);
        this.Cinventory = CinventoryA;
        this.ChestNumRows = NumRows;
        addSlots(CinventoryA,playerInventory);
    }
    //客户端(Client)
    public CChestContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer){
        super(MContainers.CHEST_9X9_CONTAINER.get(),id);
        // 从网络读取行数和方块位置
        int rows = buffer.readInt();
        BlockPos pos = buffer.readBlockPos();
        IInventory chestInv;
        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (te instanceof IInventory){
            chestInv = (IInventory) te;
        }else {
            chestInv = new Inventory(rows * NUM_ROWS);
        }
        this.ChestNumRows = rows;
        this.Cinventory = chestInv;
        addSlots(chestInv,playerInventory);
    }
    //加载（Loading）
    public void addSlots(IInventory cinventoryA, PlayerInventory playerInventory) {
        //箱子槽位（行数=numRows，列数=9）
        //加行
        for (int row = 0;row < ChestNumRows;row++){
            //加列
            for(int col = 0;col < NUM_ROWS;col++){
                int index = col + row * NUM_ROWS;
                int x = 8 + col * 18;
                int y = 18 + row * 18;
                addSlot(new Slot(cinventoryA,index,x,y));
            }
        }
        //玩家背包（3行9列）
        int StartY = 194;
        //加行
        for(int row1 = 0;row1 < 3;row1++){
            //加列
            for (int col1 = 0;col1 < 9;col1++){
                int index = col1 + row1 * 9 + 9;
                int x = 8 + col1 * 18;
                int y = StartY + row1 * 18;
                //重复玩家快捷栏
                addSlot(new Slot(playerInventory,index,x,y));
            }
        }
        //快捷栏（9列1行）
        for(int col2 = 0;col2 < 9;col2++){
            int x = 8 + col2 * 18;
            int y = StartY + 57;
            addSlot(new Slot(playerInventory,col2,x,y));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return Cinventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack stack = slot.getStack();
            result = stack.copy();
            int chestSlots = ChestNumRows * NUM_ROWS;
            int hotbarStart = chestSlots + 27;
            //箱子->玩家
            if (index < chestSlots){
                if(!mergeItemStack(stack, chestSlots,inventorySlots.size(),true)){
                    return ItemStack.EMPTY;
                }
            }
            //玩家->箱子
            else if(!mergeItemStack(stack,0,chestSlots,false)){
                    return ItemStack.EMPTY;
            }
            if (stack.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }
        return result;
    }
}
