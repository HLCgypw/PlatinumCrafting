package com.hlpriver.aevmod.tileentity.forwork;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.block.custom.ChestBlock;
import com.hlpriver.aevmod.container.CChestContainer1;
import com.hlpriver.aevmod.tileentity.MTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ChestTileEntity1 extends BasicLootTileEntity {
    //Number of players opening the chest
    private int playerCount;
    // Fixed number of rows for the chest GUI (fixed to 9 for now)
    private static final int NUM_ROWS = 10;  // 固定为10行
    //Constructor
    protected ChestTileEntity1(TileEntityType<?> typeIn) {
        super(typeIn);
    }
    //Construct registration type corresponding to - ChestTileEntity
    public ChestTileEntity1(){
        super(MTileEntities.CHEST_TILE_ENTITY_1.get());
    }
    //Set GUI name
    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container."+ AevMod.MOD_ID+".chest_1");
    }
    //Set GUI size - 9*9 grid (temporarily fixed to 9x9)
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        // 固定使用10x10的GUI
        return new CChestContainer1(id,player,this);
    }
    //Set GUI size - fixed to 100 slots (10*10)
    @Override
    public int getSizeInventory() {
        return 10 * NUM_ROWS; // 10列乘以10行 = 100个槽位
    }
    //Judge and increase/decrease number of players when opening the chest
    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()){
            if (this.playerCount < 0){
                this.playerCount = 0;
            }
            this.playerCount++;
            BlockState blockState = this.getBlockState();
            boolean open = blockState.get(ChestBlock.OPEN);
            if (!open){
                this.playDoorSound(blockState, SoundEvents.BLOCK_CHEST_OPEN);
                this.setDoorState(blockState, true);
            }
        }
        this.scheduleTick();
    }
    //Help with real-time updates
    private void scheduleTick() {
        this.world.getPendingBlockTicks().scheduleTick(this.getPos(),this.getBlockState().getBlock(),5);
    }
    //(Update every 5 ticks) Calculate number of players using this chest
    public void onScheduleTick(){
        int x = this.pos.getX();
        int y = this.pos.getY();
        int z = this.pos.getZ();
        World world = this.getWorld();
        if (world != null){
            this.playerCount = net.minecraft.tileentity.ChestTileEntity.calculatePlayersUsing(world,this,x,y,z);
            if (this.playerCount > 0){
                this.scheduleTick();
            }else {
                BlockState blockState = this.getBlockState();
                if (!(blockState.getBlock() instanceof ChestBlock)){
                    this.remove();
                    return;
                }
                boolean open = blockState.get(ChestBlock.OPEN);
                if (open){
                    this.playDoorSound(blockState,SoundEvents.BLOCK_CHEST_CLOSE);
                    this.setDoorState(blockState, false);
                }
            }

        }
    }
    //Play sound
    private void playDoorSound(BlockState blockState, SoundEvent soundEvent) {
        double x = this.pos.getX() + 0.5D;
        double y = this.pos.getY() + 0.5D;
        double z = this.pos.getZ() + 0.5D;
        World world = this.getWorld();
        if(world != null)
        {
            world.playSound(null, x, y, z, soundEvent, SoundCategory.BLOCKS, 0.5F,
                    world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }
    //关闭箱子
    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()){
            this.playerCount--;
        }
    }
    //更新箱子状态
    private void setDoorState(BlockState blockState, boolean open)
    {
        World world = this.getWorld();
        if(world != null)
        {
            world.setBlockState(this.getPos(), blockState.with(ChestBlock.OPEN, open), 3);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketBuffer packetBuffer) {
        packetBuffer.writeInt(NUM_ROWS);
        packetBuffer.writeBlockPos(this.getPos());
    }

    /**
     * 掉落所有物品
     * @param world 世界
     * @param pos 方块位置
     */
    public void dropItems(World world, BlockPos pos) {
        if (!this.isEmpty()) {
            for (int i = 0; i < this.getSizeInventory(); ++i) {
                ItemStack itemstack = this.getStackInSlot(i);
                if (!itemstack.isEmpty()) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
                    ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.55D, pos.getY() + 0.55D, pos.getZ() + 0.55D, itemstack);
                    itemEntity.setMotion((f - 0.5D) * 0.2D, (f1 - 0.5D) * 0.2D, (f2 - 0.5D) * 0.2D);
                    world.addEntity(itemEntity);
                }
            }
        }
    }

}