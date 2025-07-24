package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import static com.FuFu.CabbageJellyPack.Event.StaticData.*;

@Mixin(CraftingScreen.class)
public abstract class TransparentCraftingScreenMixin extends AbstractRecipeBookScreen<CraftingMenu>{
    public TransparentCraftingScreenMixin(CraftingMenu pMenu, RecipeBookComponent<?> pRecipeBookComponent, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pRecipeBookComponent, pPlayerInventory, pTitle);
    }
    /**
     * 重写 renderBg
     * @author PinkCabbage
     * @reason want to disable original rendering to allow custom transparent rendering externally.
     */
    @Overwrite
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        CRAFTING_I = i;
        CRAFTING_J = j;
        CRAFTINGWIDTH = this.imageWidth;
        CRAFTINGHEIGHT = this.imageHeight;

        BUTTON_Y = this.height / 2 - 49;
    }
}
