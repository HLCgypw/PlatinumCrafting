package com.hlpriver.aevmod.util;

import com.hlpriver.aevmod.AevMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AevMod.MOD_ID);
    //声音注册
    public static final RegistryObject<SoundEvent> MADEAR = regSoundEvent("madear");
    //声音注册
    public static RegistryObject<SoundEvent> regSoundEvent(String name){
        return SOUND.register(name,()-> new SoundEvent(new ResourceLocation(AevMod.MOD_ID,name)));
    }

    public static void register(IEventBus eventBus){
        SOUND.register(eventBus);
    }
}
