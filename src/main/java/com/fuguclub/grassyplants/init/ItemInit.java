package com.fuguclub.grassyplants.init;

import com.fuguclub.grassyplants.GrassyPlants;
import com.fuguclub.grassyplants.objects.items.BlueBerry;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            GrassyPlants.MOD_ID);

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new BlueBerry(BlockInit.BLUEBERRY_BUSH.get(), new Item.Properties().group(GrassyPlants.GrassyItemGroup.instance)
                    .food(new Food.Builder().fastToEat().hunger(2).saturation(2).build())));
}