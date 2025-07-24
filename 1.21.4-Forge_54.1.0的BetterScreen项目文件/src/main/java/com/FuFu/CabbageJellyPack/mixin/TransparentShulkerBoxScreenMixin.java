package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import static com.FuFu.CabbageJellyPack.Event.StaticData.*;


@Mixin(ShulkerBoxScreen.class)
public abstract class TransparentShulkerBoxScreenMixin extends AbstractContainerScreen<ShulkerBoxMenu> {
    public TransparentShulkerBoxScreenMixin(ShulkerBoxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        SHULKERBOX_I = i;
        SHULKERBOX_J = j;
        SHULKERBOXWIDTH = this.imageWidth;
        SHULKERBOXHEIGHT = this.imageHeight;
        }
    }

