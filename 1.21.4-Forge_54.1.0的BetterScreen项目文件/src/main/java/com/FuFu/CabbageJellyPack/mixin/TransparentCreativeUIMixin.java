package com.FuFu.CabbageJellyPack.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;

@Mixin(CreativeModeInventoryScreen.class)
    public abstract class TransparentCreativeUIMixin {

        /*
        // 设置背景为透明(仅背包背景透明)
        @Inject(
                method = "renderTabButton",
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"
                )
        )
        private void makeTabBackgroundTransparent(GuiGraphics graphics, CreativeModeTab tab, CallbackInfo ci) {
            if (tab.getBackgroundTexture().toString().contains("tab_inventory")) {
                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
            }
        }

        // 在图标渲染前还原为不透明
        @Inject(
                method = "renderTabButton",
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;II)V"
                )
        )
        private void makeTabIconOpaque(GuiGraphics graphics, CreativeModeTab tab, CallbackInfo ci) {
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f); // 恢复为不透明
        }
        */


    //在物品渲染前设置透明度，但是标签图标仍然存在
    @Inject(method = "renderBg", at = @At("HEAD"))
    private void injectRenderBg(GuiGraphics guiGraphics, float p_282504_, int p_282089_, int p_282249_, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
    }



    // 在渲染背景纹理后还原
    @Inject(method = "renderBg", at = @At("TAIL"))
    private void restoreColor(GuiGraphics guiGraphics, float p_282504_, int p_282089_, int p_282249_, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

    }



    //------------------------------------------------------------------------------------------------------------------
/*
    // 在 renderTabButton 方法中渲染物品图标之前恢复透明度
    @Inject(
            method = "renderTabButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;II)V"
            )
    )
    private void restoreAlphaBeforeIcon(GuiGraphics guiGraphics, CreativeModeTab tab, CallbackInfo ci) {
        // 恢复为不透明
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    // 如果你希望图标渲染完成后继续恢复状态（例如防止后续渲染错误），可以添加这一段：
    @Inject(
            method = "renderTabButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V",
                    shift = At.Shift.AFTER
            )
    )
    private void ensureAlphaRestoredAfterIcon(GuiGraphics guiGraphics, CreativeModeTab tab, CallbackInfo ci) {
        // 再次确认恢复为不透明状态
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
    }


 */
}
