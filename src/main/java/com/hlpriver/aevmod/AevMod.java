package com.hlpriver.aevmod;

import com.hlpriver.aevmod.block.registry.MBlocks;
import com.hlpriver.aevmod.container.MContainers;
import com.hlpriver.aevmod.entity.MEntityTypes;
import com.hlpriver.aevmod.item.armor.MArmorKinds;
import com.hlpriver.aevmod.item.registry.MItems;
import com.hlpriver.aevmod.item.tool.MToolKinds;
import com.hlpriver.aevmod.painting.MPaintingType;
import com.hlpriver.aevmod.screens.ItemChestScreen;
import com.hlpriver.aevmod.screens.MChestScreen;
import com.hlpriver.aevmod.screens.MChestScreen1;
import com.hlpriver.aevmod.tileentity.MTileEntities;
import com.hlpriver.aevmod.util.MIModelProperties;
import com.hlpriver.aevmod.util.MSoundEvents;
import com.hlpriver.aevmod.world.structure.MStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AevMod.MOD_ID)
public class AevMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "aevmod";



    public AevMod() {
        // Register the setup method for modloading

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MSoundEvents.register(eventBus);
        MItems.register(eventBus);
        MBlocks.register(eventBus);
        MToolKinds.register(eventBus);
        MArmorKinds.register(eventBus);
        MTileEntities.register(eventBus);
        MStructures.register(eventBus);
        MPaintingType.PAINTING_TYPES.register(eventBus);
        MContainers.register(eventBus);
        MEntityTypes.ENTITY_TYPES.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        event.enqueueWork(MStructures::setupStructure);


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        event.enqueueWork(()-> {
            RenderTypeLookup.setRenderLayer(MBlocks.PLATINUM_DOOR.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(MBlocks.PLATINUM_TRAPDOOR.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(MBlocks.LSITTING.get(),RenderType.getCutout());

            ScreenManager.registerFactory(MContainers.CHEST_9X9_CONTAINER.get(), MChestScreen::new);
            ScreenManager.registerFactory(MContainers.CHEST_10X10_CONTAINER.get(), MChestScreen1::new);
            ScreenManager.registerFactory(MContainers.PORTABLE_CHEST_CONTAINER.get(), ItemChestScreen::new);

            //MIModelProperties.makeBow(MItems.PLATINUM_BOW.get());
            MIModelProperties.makeBow(MItems.AS.get());
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(AevMod.MOD_ID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}