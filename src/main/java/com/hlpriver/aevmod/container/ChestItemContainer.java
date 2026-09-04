package com.hlpriver.aevmod.container;

import com.hlpriver.aevmod.item.custom.ChestItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class ChestItemContainer extends Container {
    private final IInventory PCI;
    private final int Rows;
    String containerUUID;
    private final int NUM_ROWS = 9;

    //废物端(Server)
    public ChestItemContainer(int id, PlayerInventory playerInventory, IInventory CinventoryA, int NumRows,String uuid) {
        super(MContainers.PORTABLE_CHEST_CONTAINER.get(), id);
        this.PCI = CinventoryA;
        this.Rows = NumRows;
        this.containerUUID = uuid;
        addSlots(PCI,playerInventory);
    }
    //客户端(Client)
    public ChestItemContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer){
        super(MContainers.PORTABLE_CHEST_CONTAINER.get(),id);
        // 从网络读取行数和方块位置
        this.Rows = buffer.readInt();
        int SlotCount = this.Rows * NUM_ROWS;
        IInventory chestInv;
        chestInv = new Inventory(SlotCount);
        for (int i = 0; i < SlotCount; i++) {
            chestInv.setInventorySlotContents(i, buffer.readItemStack());
        }
        this.PCI = chestInv;
        addSlots(chestInv,playerInventory);
    }


    //加载（Loading）
    public void addSlots(IInventory cinventoryA, PlayerInventory playerInventory) {
        //箱子槽位（行数=numRows，列数=9）
        //加行
        for (int row = 0;row < Rows;row++){
            //加列
            for(int col = 0;col < NUM_ROWS;col++){
                int index = col + row * NUM_ROWS;
                int x = 8 + col * 18;
                int y = 18 + row * 18;
                addSlot(new Slot(cinventoryA,index,x,y){
                    @Override
                    public boolean isItemValid(ItemStack stack) {
                        return !(stack.getItem() instanceof ChestItem);
                    }
                });


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
        return true;
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack stack = slot.getStack();
            result = stack.copy();

            int chestSlots = Rows * NUM_ROWS;
            int hotbarStart = chestSlots + 9;
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



    @Override
    public void onContainerClosed(PlayerEntity player) {
        super.onContainerClosed(player);
        if (!player.world.isRemote && containerUUID != null) {
            // 将 chestInventory 的内容写回玩家手持的物品（或其他方式，取决于你的逻辑）
            // 例如：假设玩家手持该物品，找到它并保存
            ItemStack stack = findItemByUUID(player,containerUUID);
            if (!stack.isEmpty() && stack.getItem() instanceof ChestItem) {
                ChestItem.saveInventory(stack, (Inventory) this.PCI);
                stack.getOrCreateTag().remove("ContainerUUID");
            }
        }
    }

    // 遍历玩家所有物品栏定位物品
    ItemStack findItemByUUID(PlayerEntity player, String uuid) {
        // 主手
        ItemStack main = player.getHeldItemMainhand();
        if (hasUUID(main, uuid)) return main;
        // 副手
        ItemStack off = player.getHeldItemOffhand();
        if (hasUUID(off, uuid)) return off;
        // 背包（不包括主副手，但包含所有格子）
        PlayerInventory inv = player.inventory;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (hasUUID(stack, uuid)) return stack;
        }
        return ItemStack.EMPTY;
    }

    boolean hasUUID(ItemStack stack, String uuid) {
        if (stack.isEmpty()) return false;
        CompoundNBT tag = stack.getTag();
        return tag != null && uuid.equals(tag.getString("ContainerUUID"));
    }
}
