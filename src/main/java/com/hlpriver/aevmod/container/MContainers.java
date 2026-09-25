package com.hlpriver.aevmod.container;

import com.hlpriver.aevmod.PlatinumCore;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
            .create(ForgeRegistries.CONTAINERS, PlatinumCore.MOD_ID);
    public static final RegistryObject<ContainerType<CChestContainer>> CHEST_9X9_CONTAINER = CONTAINERS
            .register("chest_9x9", () -> IForgeContainerType.create(CChestContainer::new));
    public static final RegistryObject<ContainerType<CChestContainer1>> CHEST_10X10_CONTAINER = CONTAINERS
            .register("chest_10x10", () -> IForgeContainerType.create(CChestContainer1::new));
    public static final RegistryObject<ContainerType<ChestItemContainer>> PORTABLE_CHEST_CONTAINER = CONTAINERS
            .register("portable_chest", () -> IForgeContainerType.create(ChestItemContainer::new));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}