package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.key.Events;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FoodNutritionTextureEmpty {
    private static final ResourceLocation food_empty = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/hud/food_empty.png");

    private static final int OFFSET_X = 102;
    private static final int OFFSET_Y = -1;

    public static void render(GuiGraphics guiGraphics, long partialTick) {
        if (!Events.showDurability) {
            return; // 如果未开启显示，直接返回
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && mc.player.isCreative()) {
            // 在创造模式，不显示
            return;
        }


        if (mc.player != null && mc.player.isSpectator()) return;

        Level clientLevel = Minecraft.getInstance().level;
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int hotbarX = (screenWidth / 2) + 104;

        // 计算图片宽高（假设图片为16x16像素）
        int imageHeight = 16;

        int LowerHeight = screenHeight - imageHeight - OFFSET_Y;

        ItemStack mainHandItem = localPlayer.getMainHandItem();

        int FoodData = 0;
        FoodProperties food = mainHandItem.get(DataComponents.FOOD);
        if (food != null) {
            FoodData = food.nutrition();
        }
        int Yu = FoodData / 2;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(hotbarX, LowerHeight, -500);
        guiGraphics.pose().scale(0.6F, 0.6F, 1.0F); // 缩放文字

        if (mainHandItem.get(DataComponents.FOOD) != null) {

            if (FoodData % 2 == 0) {

                if (food_empty != null) {
                    for (int i = 1; i < Yu + 1; i++) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                food_empty,
                                (i * 15), 0,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }
            }
            if (FoodData == 1) {
                if (food_empty != null) {
                    guiGraphics.blit(
                            RenderType::guiTextured,
                            food_empty,
                            15 , 0,
                            0, 0, // 纹理坐标
                            16, 16,
                            16, 16 // 渲染宽高
                    );
                }
            }
            if (FoodData % 2 == 1){
                for (int i = 1; i < (FoodData-1) / 2 + 1; i++) {
                    if (food_empty != null) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                food_empty,
                                (i * 15), 0,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }
                if (food_empty!= null) {
                    guiGraphics.blit(
                            RenderType::guiTextured,
                            food_empty,
                            ((FoodData-1) / 2 + 1) * 15, 0,
                            0, 0, // 纹理坐标
                            16, 16,
                            16, 16 // 渲染宽高
                    );
                }

            }
        }
        guiGraphics.pose().popPose();
    }
}
