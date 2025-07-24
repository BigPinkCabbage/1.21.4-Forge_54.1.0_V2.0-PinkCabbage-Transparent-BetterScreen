package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(RecipeBookTabButton.class)
public interface RecipeBookTabButtonAccessor {
    @Accessor("tabInfo")
    RecipeBookComponent.TabInfo getTabInfo();
}