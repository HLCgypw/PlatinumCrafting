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

public class CChestContainer1 extends Container {
    private static final int ROWS = 10;          // 行数（固定10）
    private static final int COLUMNS = 10;       // 列数（固定10）
    private static final int SLOT_COUNT = ROWS * COLUMNS; // 总槽位数 = 100
    private final IInventory chestInventory;
    // 服务端构造函数：
    public CChestContainer1(int id, PlayerInventory playerInventory, IInventory chestInventory) {
        super(MContainers.CHEST_10X10_CONTAINER.get(), id);
        //直接传入箱子IInventory
        this.chestInventory = chestInventory;
        addSlots(chestInventory, playerInventory);
    }
    // 客户端构造函数：
    public CChestContainer1(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        //从网络包读取方块位置
        super(MContainers.CHEST_10X10_CONTAINER.get(), id);
        BlockPos pos = buffer.readBlockPos();
        //获取或创建箱子Inventory
        IInventory inv;
        TileEntity te = playerInventory.player.world.getTileEntity(pos);
        if (te instanceof IInventory) {
            inv = (IInventory) te;
        } else {
            inv = new Inventory(SLOT_COUNT);
        }
        this.chestInventory = inv;
        addSlots(inv, playerInventory);
    }

    // 添加所有槽位：100个箱子槽位 + 27个背包槽位 + 9个快捷栏槽位
    private void addSlots(IInventory chestInv, PlayerInventory playerInv) {
        // 箱子槽位：10行 × 10列
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                //总格数
                int index = col + row * COLUMNS;
                //写入位置
                int x = 8 + col * 18;
                int y = 18 + row * 18;
                //套用方法
                addSlot(new Slot(chestInv, index, x, y));
            }
        }
        // 玩家背包槽位（3行 × 9列）
        // 起始Y坐标在箱子下方
        int startY = 212;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9 + 9;
                int x = 17 + col * 18;
                int y = startY + row * 18;
                addSlot(new Slot(playerInv, index, x, y));
            }
        }
        // 快捷栏槽位（1行 × 9列）
        for (int col = 0; col < 9; col++) {
            int x = 17 + col * 18;
            int y = startY + 57;
            addSlot(new Slot(playerInv, col, x, y));
        }
    }
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return chestInventory.isUsableByPlayer(player);
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();
            // 箱子槽位范围：0 ~ SLOT_COUNT-1;玩家背包起始索引：SLOT_COUNT;快捷栏起始索引：SLOT_COUNT + 27
            if (index < SLOT_COUNT) {
                // 箱子 → 玩家背包/快捷栏
                if (!mergeItemStack(stack, SLOT_COUNT, inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // 玩家 → 箱子
                if (!mergeItemStack(stack, 0, SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return result;
    }
    // 获取箱子Inventory（供外部调用）
    public IInventory getChestInventory() {
        return chestInventory;
    }
    // 获取行数（固定10）
    public int getRows() {
        return ROWS;
    }
    // 获取列数（固定10）
    public int getColumns() {
        return COLUMNS;
    }
}
