package com.hlpriver.aevmod.tileentity;

import com.hlpriver.aevmod.AevMod;
import com.hlpriver.aevmod.block.registry.MBlocks;
import com.hlpriver.aevmod.tileentity.forwork.ChestTileEntity;
import com.hlpriver.aevmod.tileentity.forwork.ChestTileEntity1;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(
            ForgeRegistries.TILE_ENTITIES,AevMod.MOD_ID);

    public static final RegistryObject<TileEntityType<ChestTileEntity>> CHEST_TILE_ENTITY = TILE_ENTITY
            .register("chest_tile_entity",() ->TileEntityType
                    .Builder
                    .create(ChestTileEntity::new, MBlocks.PLATINUM_CHEST.get()).build(null));

    public static final RegistryObject<TileEntityType<ChestTileEntity1>> CHEST_TILE_ENTITY_1 = TILE_ENTITY
            .register("chest_tile_entity_1",() ->TileEntityType
                    .Builder
                    .create(ChestTileEntity1::new, MBlocks.PLATINUM_CHEST_1.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITY.register(eventBus);
    }
}