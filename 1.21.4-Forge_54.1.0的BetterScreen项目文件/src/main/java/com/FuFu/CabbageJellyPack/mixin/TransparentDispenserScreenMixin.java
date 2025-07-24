package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.DispenserScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.DispenserMenu;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;


@Mixin(DispenserScreen.class)
public class TransparentDispenserScreenMixin extends AbstractContainerScreen<DispenserMenu> {
    public TransparentDispenserScreenMixin(DispenserMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    /**
     * 重写 renderBg
     * @author PinkCabbage
     * @reason want to disable original rendering to allow custom transparent rendering externally.
     */
    @Overwrite
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        DISPENSER_I = i;
        DISPENSER_J = j;
        DISPENSERWIDTH = this.imageWidth;
        DISPENSERHEIGHT = this.imageHeight;
        DISPENSERTITLE_X = (this.imageWidth - this.font.width(this.title)) / 2;
    }
}
