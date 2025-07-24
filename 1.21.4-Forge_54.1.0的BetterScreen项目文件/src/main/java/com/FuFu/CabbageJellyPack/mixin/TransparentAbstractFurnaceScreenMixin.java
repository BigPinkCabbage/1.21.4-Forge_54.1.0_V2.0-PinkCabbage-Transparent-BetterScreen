package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;


@Mixin(AbstractFurnaceScreen.class)
public abstract class TransparentAbstractFurnaceScreenMixin extends AbstractRecipeBookScreen {


    public TransparentAbstractFurnaceScreenMixin(RecipeBookMenu pMenu, RecipeBookComponent pRecipeBookComponent, Inventory pPlayerInventory, Component pTitle) {
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
        int j = this.topPos;

        FURNACE_I = i;
        FURNACE_J = j;
        FURNACE_LabelX = this.titleLabelX;
        FURNACEHEIGHT = this.imageHeight;

    }
}

