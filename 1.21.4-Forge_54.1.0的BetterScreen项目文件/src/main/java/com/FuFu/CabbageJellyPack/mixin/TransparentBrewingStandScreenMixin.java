package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BrewingStandMenu;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;

@Mixin(BrewingStandScreen.class)
public class TransparentBrewingStandScreenMixin extends AbstractContainerScreen<BrewingStandMenu> {

    public TransparentBrewingStandScreenMixin(BrewingStandMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    @Unique
    private static final int[] cabbagepinkpack1_0_1$BUBBLELENGTHS = new int[]{29, 24, 20, 16, 11, 6, 0};
    /**
     * 重写 renderBg
     *
     * @author PinkCabbage
     * @reason want to disable original rendering to allow custom transparent rendering externally.
     */
    @Overwrite
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        BREWINGSTAND_X = this.titleLabelX;

        BREWINGSTANDWIDTH = this.imageWidth;
        BREWINGSTANDHEIGHT = this.imageHeight;

        BREWINGSTAND_I = i;
        BREWINGSTAND_J = j;

        int k = this.menu.getFuel();

        BREWINGSTAND_L = Mth.clamp((18 * k + 20 - 1) / 20, 0, 18);

        int i1 = this.menu.getBrewingTicks();
        BREWINGSTAND_I1 = i1;

        if (i1 > 0) {
            BREWINGSTAND_J1 = cabbagepinkpack1_0_1$BUBBLELENGTHS[i1 / 2 % 7];
        }

    }
}
