package com.opryshok.compat.farmersdelight;

import com.opryshok.BorukvaFood;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FarmersDelightCompat {
    public static final boolean IS_LOADED = FabricLoader.getInstance().isModLoaded("farmersdelight");
    public static final String FD_MOD_ID = "farmersdelight";

    public static void registerAliases() {
        if (!IS_LOADED) {
            return;
        }

        BorukvaFood.LOGGER.info("Farmer's Delight detected, registering compatibility aliases...");

        registerItemAlias("tomato", "tomato");
        registerItemAlias("tomato_seeds", "tomato_seeds");
        registerItemAlias("cabbage", "cabbage");
        registerItemAlias("cabbage_seeds", "cabbage_seeds");
        registerItemAlias("onion", "onion");
        registerItemAlias("rice", "rice");
        registerItemAlias("rice_panicle", "rice_panicle");
        registerItemAlias("sweet_berry_cookie", "sweet_berry_cookie");

        registerItemAlias("rice_bowl", "cooked_rice");
        registerItemAlias("salmon_fillet", "salmon_slice");
        registerItemAlias("salmon_maki", "salmon_roll");
        registerItemAlias("tomato_slices", "tomato_sauce");

        registerItemAlias("tomato_crate", "tomato_crate");
        registerItemAlias("cabbage_crate", "cabbage_crate");
        registerItemAlias("carrot_crate", "carrot_crate");
        registerItemAlias("onion_crate", "onion_crate");
        registerItemAlias("potato_crate", "potato_crate");
        registerItemAlias("beetroot_crate", "beetroot_crate");
        registerItemAlias("rice_crate", "rice_bale");

        registerBlockAlias("tomato_crate", "tomato_crate");
        registerBlockAlias("cabbage_crate", "cabbage_crate");
        registerBlockAlias("carrot_crate", "carrot_crate");
        registerBlockAlias("onion_crate", "onion_crate");
        registerBlockAlias("potato_crate", "potato_crate");
        registerBlockAlias("beetroot_crate", "beetroot_crate");
        registerBlockAlias("rice_crate", "rice_bale");

        BorukvaFood.LOGGER.info("Farmer's Delight compatibility aliases registered successfully");
    }

    private static void registerItemAlias(String borukvaName, String fdName) {
        Identifier oldId = Identifier.of(BorukvaFood.MOD_ID, borukvaName);
        Identifier newId = Identifier.of(FD_MOD_ID, fdName);
        Registries.ITEM.addAlias(oldId, newId);
    }

    private static void registerBlockAlias(String borukvaName, String fdName) {
        Identifier oldId = Identifier.of(BorukvaFood.MOD_ID, borukvaName);
        Identifier newId = Identifier.of(FD_MOD_ID, fdName);
        Registries.BLOCK.addAlias(oldId, newId);
    }

    public static Item getFdItem(String name) {
        return Registries.ITEM.get(Identifier.of(FD_MOD_ID, name));
    }

    public static Block getFdBlock(String name) {
        return Registries.BLOCK.get(Identifier.of(FD_MOD_ID, name));
    }

    public static Item getTomato() {
        return getFdItem("tomato");
    }

    public static Item getTomatoSeeds() {
        return getFdItem("tomato_seeds");
    }

    public static Item getCabbage() {
        return getFdItem("cabbage");
    }

    public static Item getCabbageSeeds() {
        return getFdItem("cabbage_seeds");
    }

    public static Item getOnion() {
        return getFdItem("onion");
    }

    public static Item getRice() {
        return getFdItem("rice");
    }

    public static Item getRicePanicle() {
        return getFdItem("rice_panicle");
    }

    public static Item getCookedRice() {
        return getFdItem("cooked_rice");
    }

    public static Item getSweetBerryCookie() {
        return getFdItem("sweet_berry_cookie");
    }

    public static Item getSalmonSlice() {
        return getFdItem("salmon_slice");
    }

    public static Item getSalmonRoll() {
        return getFdItem("salmon_roll");
    }

    public static Item getTomatoSauce() {
        return getFdItem("tomato_sauce");
    }

    public static Block getTomatoCrate() {
        return getFdBlock("tomato_crate");
    }

    public static Block getCabbageCrate() {
        return getFdBlock("cabbage_crate");
    }

    public static Block getCarrotCrate() {
        return getFdBlock("carrot_crate");
    }

    public static Block getOnionCrate() {
        return getFdBlock("onion_crate");
    }

    public static Block getPotatoCrate() {
        return getFdBlock("potato_crate");
    }

    public static Block getBeetrootCrate() {
        return getFdBlock("beetroot_crate");
    }

    public static Block getRiceBale() {
        return getFdBlock("rice_bale");
    }
}
