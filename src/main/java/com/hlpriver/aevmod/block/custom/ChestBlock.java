package com.hlpriver.aevmod.block.custom;

import com.hlpriver.aevmod.tileentity.forwork.ChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class ChestBlock extends Block {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public ChestBlock(Properties properties) {
        super(properties);
        // 设置默认状态为关闭状态
        this.setDefaultState(this.getStateContainer().getBaseState().with(OPEN,true));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ChestTileEntity();
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // 只在服务端处理，避免客户端重复处理
        if (!worldIn.isRemote){
            // 获取方块对应的TileEntity
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            // 如果是ChestTileEntity实例，则打开容器界面
            if (tileEntity instanceof ChestTileEntity){
                NetworkHooks.openGui((net.minecraft.entity.player.ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, buf -> {
                    buf.writeInt(9); // 写入行数
                    buf.writeBlockPos(pos); // 写入位置
                });
            }
        }
        // 返回成功结果
        return ActionResultType.SUCCESS;
    }
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        // 获取方块对应的TileEntity
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        // 如果是ChestTileEntity实例，则调用其onScheduleTick方法
        if (tileEntity instanceof ChestTileEntity){
            ((ChestTileEntity) tileEntity).onScheduleTick();
        }
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        // 添加OPEN属性到状态容器
        builder.add(OPEN);
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        // 如果方块被替换为不同类型的方块
        if (state.getBlock() != newState.getBlock())
        {
            // 获取方块对应的TileEntity
            TileEntity tileentity = worldIn.getTileEntity(pos);
            // 如果是ChestTileEntity实例，则掉落物品
            if (tileentity instanceof ChestTileEntity)
            {
                // 掉落物品
                ((ChestTileEntity) tileentity).dropItems(worldIn, pos);
                // 移除TileEntity
                worldIn.removeTileEntity(pos);
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}