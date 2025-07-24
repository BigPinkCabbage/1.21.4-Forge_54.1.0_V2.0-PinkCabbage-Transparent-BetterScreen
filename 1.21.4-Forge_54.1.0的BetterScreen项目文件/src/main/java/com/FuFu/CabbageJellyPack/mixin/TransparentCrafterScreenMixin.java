package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CrafterScreen;
import net.minecraft.world.inventory.CrafterMenu;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Overwrite;
import static com.FuFu.CabbageJellyPack.Event.StaticData.*;

@Mixin(CrafterScreen.class)
public class TransparentCrafterScreenMixin extends AbstractContainerScreen<CrafterMenu> {
    public TransparentCrafterScreenMixin(CrafterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        CRAFTERTITLE_X = (this.imageWidth - this.font.width(this.title)) / 2;
        CRAFTER_I = i;
        CRAFTER_J = j;
        CRAFTERWIDTH = this.imageWidth;
        CRAFTERHEIGHT = this.imageHeight;

        int redstone_i = this.width / 2 + 9;
        int redstone_j = this.height / 2 - 48;

        REDSTONE_I = redstone_i;
        REDSTONE_J  = redstone_j;
        POWERED = this.menu.isPowered();
    }
}
