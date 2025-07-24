package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;
import static com.FuFu.CabbageJellyPack.Event.StaticData.HOPPERHEIGHT;

@Mixin(ContainerScreen.class)
public class TransparentContainerScreenMixin extends AbstractContainerScreen<ChestMenu> {
    public TransparentContainerScreenMixin(ChestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    /**
     * 重写 renderBg，跳过对 CONTAINER_TEXTURE 的 blit 渲染
     * @author PinkCabbage
     * @reason want to disable original rendering to allow custom transparent rendering externally.
     */
    @Overwrite
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        CHEST_I = i;
        CHEST_J = j;
        CHESTWIDTH = this.imageWidth;
        CHESTHEIGHT = this.imageHeight;

    }
}

