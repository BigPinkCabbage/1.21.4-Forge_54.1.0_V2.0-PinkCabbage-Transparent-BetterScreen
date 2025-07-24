package com.FuFu.CabbageJellyPack.mixin;

import com.FuFu.CabbageJellyPack.Event.StaticData;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(CreativeModeInventoryScreen.class)

public abstract class AddTabIcon extends AbstractContainerScreen<AbstractContainerMenu> {

    public AddTabIcon(Container pMenu, Inventory pPlayerInventory, Component pTitle) {
        super((AbstractContainerMenu) pMenu, pPlayerInventory, pTitle);
    }

    @Inject(
            method = "renderTabButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;II)V"
            )
    )
    public void onRenderTabButton(GuiGraphics pGuiGraphics, CreativeModeTab pCreativeModeTab, CallbackInfo ci) {
       //CreativeModeInventoryScreen self = (CreativeModeInventoryScreen)(Object) this;

       // boolean isTop = self.getCurrentPage().isTop(pCreativeModeTab);
       // int column = self.getCurrentPage().getColumn(pCreativeModeTab);

       // int tabX = ((CreativeModeInventoryScreenAccessor) self).invokeGetTabX(pCreativeModeTab);

       // int Iconx = leftPos + tabX + 5;
       // int Icony = topPos - (isTop ? 28 : -(imageHeight - 4)) + 8 + (isTop ? 1 : -1);

        StaticData.currentleftPos = leftPos;
        StaticData.currenttopPos = topPos;
        StaticData.currentimageHeight = imageHeight;
        StaticData.currentimageWidth = imageWidth;
    }
}
    /*
    @Shadow
    protected abstract void renderTabButton(GuiGraphics pGuiGraphics, CreativeModeTab pCreativeModeTab);

    @Inject(method = "renderTabButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V"))
    private void reRenderIcon(GuiGraphics pGuiGraphics, CreativeModeTab pCreativeModeTab, CallbackInfo ci) {
        ItemStack originalIcon = pCreativeModeTab.getIconItem();

        // 保存当前矩阵状态
        pGuiGraphics.pose().pushPose();

        // 计算图标位置
        int iconX = 5;
        int iconY = 8 + (pCreativeModeTab.row() == CreativeModeTab.Row.TOP ? 1 : -1);

        // 重新渲染图标
        pGuiGraphics.renderItem(originalIcon, iconX, iconY);

        // 恢复矩阵状态
        pGuiGraphics.pose().popPose();
    }
      */

