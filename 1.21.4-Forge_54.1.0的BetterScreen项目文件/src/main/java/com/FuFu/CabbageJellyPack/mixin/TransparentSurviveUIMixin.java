package com.FuFu.CabbageJellyPack.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;

@Mixin(InventoryScreen.class)
public class TransparentSurviveUIMixin {

    @Inject(method = "renderBg", at = @At("HEAD"))
    private void onRenderBgStart(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {
        // 设置颜色为白色并将 alpha 设为 0.5f
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f,  InventoryAlpha);
    }

    @Inject(method = "renderBg", at = @At("RETURN"))
    private void onRenderBgEnd(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {
        // 恢复 alpha 为不透明
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
