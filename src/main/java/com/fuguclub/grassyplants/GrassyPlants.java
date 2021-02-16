package com.fuguclub.grassyplants;

import com.fuguclub.grassyplants.init.BlockInit;
import com.fuguclub.grassyplants.init.ItemInit;
import com.fuguclub.grassyplants.objects.blocks.BlueberryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod("grassyplants")
@Mod.EventBusSubscriber(modid = "grassyplants",bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrassyPlants {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "grassyplants";
    public static GrassyPlants instance;

    public static ConfiguredFeature<?, ?> flowerFeature;

    public GrassyPlants() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().filter(blockRegistryObject -> !(blockRegistryObject.get() instanceof BlueberryBlock))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(GrassyItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        List<Supplier<ConfiguredFeature<?, ?>>> features = BlockInit.BLOCKS.getEntries().stream()
                .map(b -> (Supplier<ConfiguredFeature<?, ?>>) () ->
                        Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(b.get().getDefaultState()),
                                new SimpleBlockPlacer())).tries(64).func_227317_b_().build()))
                .collect(Collectors.toList());
        flowerFeature = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, "forest_flower_vegetation"),
                Feature.SIMPLE_RANDOM_SELECTOR.withConfiguration(new SingleRandomFeature(features)).func_242730_a(FeatureSpread.func_242253_a(-3, 4))
                        .withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5));

        // composting
        for (RegistryObject<Block> block : BlockInit.BLOCKS.getEntries())
            ComposterBlock.CHANCES.put(block.get().asItem(), 0.65F);
    }

    public static class GrassyItemGroup extends ItemGroup{

        public static final GrassyItemGroup instance = new GrassyItemGroup(ItemGroup.GROUPS.length, "grassyplants");

        private GrassyItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.BLUEBERRY.get());
        }
    }
}