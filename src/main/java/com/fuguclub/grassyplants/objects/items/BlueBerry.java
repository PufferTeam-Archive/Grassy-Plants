package com.fuguclub.grassyplants.objects.items;

import com.fuguclub.grassyplants.GrassyPlants;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlueBerry extends BlockItem {

    public BlueBerry(Block blockIn) {
        super(blockIn, new Item.Properties().group(GrassyPlants.GrassyItemGroup.instance)
                .food(new Food.Builder().fastToEat().hunger(2).saturation(2).build()));
    }

    public BlueBerry(Block blockIn, Properties properties) {
        super(blockIn, properties);
    }
}
