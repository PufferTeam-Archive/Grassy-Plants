package com.fuguclub.grassyplants;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GrassyPlants.MOD_ID)
public class Events {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event){
        if(event.getCategory() == Biome.Category.FOREST){
            event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GrassyPlants.flowerFeature);
        }
    }

}
