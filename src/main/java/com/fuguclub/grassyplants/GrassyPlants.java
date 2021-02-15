package com.fuguclub.grassyplants;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("grassyplants")
@Mod.EventBusSubscriber(modid = "grassyplants",bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrassyPlants {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "grassyplants";

    public GrassyPlants() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}