package com.hlpriver.aevmod.item.custom;

import com.hlpriver.aevmod.container.ChestItemContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

public class ChestItem extends Item {
    private static final int NUM_ROWS = 9;
    private static final int INVENTORY_SIZE = NUM_ROWS * 9;
    String UUID_TAG = "ContainerUUID";

    public ChestItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            String uuid = UUID.randomUUID().toString();
            stack.getOrCreateTag().putString(UUID_TAG,uuid);

            Inventory inv = getInventory(stack);
            NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return stack.getDisplayName();
                }
                @Nullable
                @Override
                public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                    return new ChestItemContainer(id, playerInventory, inv, NUM_ROWS, uuid);
                }
            }, buf -> {
                buf.writeInt(NUM_ROWS);
                for (int i = 0; i < INVENTORY_SIZE; i++) {
                    buf.writeItemStack(inv.getStackInSlot(i));
                }
            });
        }
        return ActionResult.resultSuccess(stack);
    }

    public static Inventory getInventory(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        Inventory inventory = new Inventory(INVENTORY_SIZE);
        if (nbt.contains("PortableItems")) {
            ListNBT list = nbt.getList("PortableItems", 10);
            for (int i = 0; i < list.size(); i++) {
                CompoundNBT itemTag = list.getCompound(i);
                int slot = itemTag.getByte("Slot") & 255;
                if (slot >= 0 && slot < INVENTORY_SIZE) {
                    inventory.setInventorySlotContents(slot, ItemStack.read(itemTag));
                }
            }
        }
        return inventory;
    }

    public static void saveInventory(ItemStack stack, Inventory inventory) {
        CompoundNBT nbt = stack.getOrCreateTag();
        ListNBT list = new ListNBT();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventory.getStackInSlot(i);
            if (!stackInSlot.isEmpty()) {
                CompoundNBT itemTag = new CompoundNBT();
                itemTag.putByte("Slot", (byte) i);
                stackInSlot.write(itemTag);
                list.add(itemTag);
            }
        }
        nbt.put("PortableItems", list);
    }
}