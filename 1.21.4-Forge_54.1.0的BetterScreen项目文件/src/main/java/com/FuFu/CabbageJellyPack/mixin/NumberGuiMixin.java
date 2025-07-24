package com.FuFu.CabbageJellyPack.mixin;

import com.FuFu.CabbageJellyPack.GuiText.*;
import com.FuFu.CabbageJellyPack.Screen.SettingsIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.FuFu.CabbageJellyPack.Event.StaticData.HasPressed;


@Mixin(Gui.class)
public class NumberGuiMixin {
    @Shadow
    @Final
    private LayeredDraw layers;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectOverlay(Minecraft mc, CallbackInfo ci) {
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            CoordinateRenderer.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);

    //------------------------------------------------------------------------------------

    }
    @Inject(method = "<init>", at = @At("RETURN"))
    private void ToolText(Minecraft mc, CallbackInfo ci) {
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            ToolDurabilityText.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

   @Inject(method = "<init>", at = @At("RETURN"))
    private void IconAndText(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            ArmorDurability.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void RightDurabilityText(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            RightDurabilityText.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void OffhandDurabilityText(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            LeftDurabilityText.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void FoodNutritionTexture(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            FoodNutritionTexture.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void FoodNutritionTextureEmpty(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            FoodNutritionTextureEmpty.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void FoodNutritionTextureLeft(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            FoodNutritionTextureLeft.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------

    @Inject(method = "<init>", at = @At("RETURN"))
    private void FoodNutritionTextureEmptyLeft(Minecraft mc, CallbackInfo ci) {
        // 调用你的自定义文本渲染
        LayeredDraw draw = new LayeredDraw();
        draw.add((guiGraphics, partialTicks) -> {
            FoodNutritionTextureEmptyLeft.render(guiGraphics, System.nanoTime()); // 传入时间参数
        });
        layers.add(draw, () -> !mc.options.hideGui);
    }

    //------------------------------------------------------------------------------------
    @Inject(method = "<init>", at = @At("RETURN"))
    private void LoadJson(Minecraft mc, CallbackInfo ci) {
        if(!HasPressed) {
           SettingsIO.load();
        }
        HasPressed = true;
    }

    //------------------------------------------------------------------------------------
    /*
    @Inject(
            method = "renderItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Gui;renderSlot(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/client/DeltaTracker;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;I)V",
                    ordinal = 1
            ),
            cancellable = true
    )
    private void skipOffhandSlot(GuiGraphics pGuiGraphics, DeltaTracker pDeltaTracker, CallbackInfo ci) {
        ci.cancel();
    }
    */

        @Redirect(
                method = "renderItemHotbar",
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/world/entity/player/Player;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"
                )
        )
        private ItemStack disableOffhandRendering(Player player) {
            return ItemStack.EMPTY; // 强制返回空物品堆栈
        }

}