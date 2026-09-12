package com.hlpriver.aevmod.block.registry;

import com.hlpriver.aevmod.AevMod;

import com.hlpriver.aevmod.block.custom.ChestBlock;
import com.hlpriver.aevmod.block.custom.ChestBlock1;
import com.hlpriver.aevmod.block.custom.Lsitting;
import com.hlpriver.aevmod.item.itemgroup.MItemGroup;
import com.hlpriver.aevmod.item.registry.MItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;



public class MBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            AevMod.MOD_ID);

    //注册区
    //铂金工艺
    //普通方块
    public static final RegistryObject<Block> PLATINUM_BLOCK = registryBlock("platinum_block",
            ()-> new Block(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)));
    //楼梯方块
    public static final RegistryObject<Block> PLATINUM_STAIRS = registryBlock("platinum_stairs",
            ()-> new StairsBlock(() -> PLATINUM_BLOCK.get().getDefaultState()
                    ,AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)));
    //栅栏&栅栏门方块
    //栅栏
    public static final RegistryObject<Block> PLATINUM_FENCE = registryBlock("platinum_fence",
            ()-> new FenceBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)));
    //栅栏门
    public static final RegistryObject<Block> PLATINUM_FENCE_GATE = registryBlock("platinum_fence_gate",
            ()-> new FenceGateBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)));
    //台阶
    public static final RegistryObject<Block> PLATINUM_SLAB = registryBlock("platinum_slab",
            ()-> new SlabBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)));
    //按钮
    public static final RegistryObject<Block> PLATINUM_BUTTON = registryBlock("platinum_button",
            ()-> new StoneButtonBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .doesNotBlockMovement()
                    ));
    //压力板
    public static final RegistryObject<Block> PLATINUM_PRESSURE_PLATE = registryBlock("platinum_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
            ));
    //门
    public static final RegistryObject<Block> PLATINUM_DOOR = registryBlock("platinum_door",
            ()-> new DoorBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .notSolid()
            ));
    //活板门
    public static final RegistryObject<Block> PLATINUM_TRAPDOOR = registryBlock("platinum_trapdoor",
            ()-> new TrapDoorBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .notSolid()
            ));
    //对应矿石
    public static final RegistryObject<Block> PLATINUM_ORE = registryBlock("platinum_ore",
            ()-> new Block(AbstractBlock
                    .Properties
                    .create(Material.ROCK)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(7.0f,22.5f)
            ));
    //箱子
    public static final RegistryObject<Block> PLATINUM_CHEST = registryBlock("platinum_chest",
            ()-> new ChestBlock(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
    ));
    public static final RegistryObject<Block> PLATINUM_CHEST_1 = registryBlock("platinum_chest_1",
            ()-> new ChestBlock1(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
            ));
    //自模型
    public static final RegistryObject<Block> LSITTING = registryBlock("lsitting",
            ()-> new Lsitting(AbstractBlock
                    .Properties
                    .create(Material.IRON)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()
                    .hardnessAndResistance(10.0f,29.5f)
                    .sound(SoundType.NETHERITE)
                    .notSolid()));
    //注册区

    private static <T extends Block>RegistryObject<T> registryBlock(String name, Supplier<T> block){
        RegistryObject<T> tRegistryObject=BLOCKS.register(name,block);
        registerBlockItem(name,tRegistryObject);
        return tRegistryObject;
    }
    private static <T extends Block> void registerBlockItem(String name,Supplier<T> block){
        MItems.ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties().group(MItemGroup.PLATINUM_TAB_ONE)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

