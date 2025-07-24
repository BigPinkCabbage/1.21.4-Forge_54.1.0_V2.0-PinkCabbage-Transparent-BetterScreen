package com.FuFu.CabbageJellyPack.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;


@Mixin(Gui.class)
public class TransparentHotbarUIMixin {

/*
    @Redirect(
            method = "renderItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    private void redirectHotbarBackground(
            GuiGraphics guiGraphics,
            Function<RenderType, VertexConsumer> vertexFunc,
            ResourceLocation sprite,
            int x, int y, int width, int height
    ) {
        // 其他 blit 正常处理
        if (HOTBAR_SPRITE != null && HOTBAR_SPRITE.equals(sprite) && !cabbagepinkpack1_0_1$hasRenderedHotbarOnce) {
            // 开启混合模式
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // 设置透明度
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.3F);

            // 绘制 hotbar 背景
            guiGraphics.blitSprite(RenderType::guiTextured, HOTBAR_SPRITE , x, y, width, height);

            // 还原状态
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            cabbagepinkpack1_0_1$hasRenderedHotbarOnce = true; // 标记为已执行
        }
    }


 */

/*
    @Inject(method = "renderItemHotbar", at = @At("HEAD"))
    private void onRenderHotbarPre(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        // 设置颜色为白色，透明度为 0.2
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.3F);
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            pGuiGraphics.pose().pushPose();
            pGuiGraphics.pose().translate(currentleftPos, currenttopPos, 500F);
            int i = pGuiGraphics.guiWidth() / 2;

            // 绘制背景
            //pGuiGraphics.blit(RenderType::guiTextured, HOTBAR_SPRITE, i - 90, pGuiGraphics.guiHeight() - 21,0,0, 182, 22,182,22);
            // 绘制选择框
            pGuiGraphics.blit(RenderType::guiTextured, HOTBAR_SELECTION_SPRITE, i - 91 + player.getInventory().selected * 20, pGuiGraphics.guiHeight() - 22,0,0, 24, 23,24,23);
            // 取消后续绘制，避免重复绘制

            pGuiGraphics.pose().popPose();


        }
    }

    @Inject(method = "renderItemHotbar", at = @At("TAIL"))
    private void onRenderHotbarPost(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        // 还原为不透明
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }





/*
    @Redirect(
            method = "renderItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    private void redirectHotbarBackground(
            GuiGraphics guiGraphics,
            Function<RenderType, VertexConsumer> vertexFunc,
            ResourceLocation sprite,
            int x, int y, int width, int height
    ) {
        // 其他 blit 正常处理
        if (HOTBAR_SPRITE != null && HOTBAR_SPRITE.equals(sprite)) {
            // 开启混合模式
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // 设置透明度
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);

            // 绘制 hotbar 背景
            guiGraphics.blitSprite(RenderType::guiTextured, HOTBAR_SPRITE , x, y, width, height);

            // 还原状态
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }



 */

    @Shadow
    @Final
    @Mutable

    private static final ResourceLocation HOTBAR_SPRITE = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/hud/hotbar.png");
    private static final ResourceLocation HOTBAR_SELECTION_SPRITE = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/hud/hotbar_selection.png");
    @Redirect(
            method = "renderItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    private void redirectHotbarBackground(
            GuiGraphics guiGraphics,
            Function<RenderType, VertexConsumer> vertexFunc,
            ResourceLocation sprite,
            int x, int y, int width, int height
    ) {
        // 只对快捷栏背景生效
        int color = 0;
        if (HOTBAR_SPRITE.equals(sprite)) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            //float alpha = 0.2F;
            color = ARGB.colorFromFloat(InventoryAlpha, 1.0F, 1.0F, 1.0F);

            // 使用 blit 手动绘制热键栏背景纹理
            guiGraphics.blit(
                    RenderType::guiTextured, // 渲染类型
                    HOTBAR_SPRITE,                  // 纹理位置
                    x, y,                    // 屏幕位置
                    0.0F, 0.0F,              // 纹理偏移
                    width, height,          // 宽高
                    width, height,               // 纹理图大小（注意：Atlas 中热键栏为 256x256）
                    color                   // ARGB颜色（含透明度）
            );
            RenderSystem.disableBlend();
        } else {
            guiGraphics.blit( RenderType::guiTextured, sprite, x, y,0,0, width, height, width, height);
        }
    }

    /*
    // 在绘制 Hotbar 背景前注入，设置透明度为 0.2
    @Inject(method = " renderItemHotbar", at = @At("HEAD"))
    private void onRenderHotbarPre(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        // 设置颜色为白色，透明度为 0.2
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
    }

    // 在绘制 Hotbar 后重置颜色为不透明
    @Inject(method = " renderItemHotbar", at = @At("TAIL"))
    private void onRenderHotbarPost(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        // 还原为不透明
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }


     */
}