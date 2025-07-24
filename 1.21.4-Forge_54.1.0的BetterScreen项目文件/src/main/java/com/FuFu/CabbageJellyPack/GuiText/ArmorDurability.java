package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.key.Events;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ArmorDurability {
    private static final ResourceLocation EmptyBootsTexture = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/container/slot/boots.png");
    private static final ResourceLocation EmptyLeggingsTexture = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/container/slot/leggings.png");
    private static final ResourceLocation EmptyChestplateTexture = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/container/slot/chestplate.png");
    private static final ResourceLocation EmptyHelmetTexture = ResourceLocation.tryBuild("minecraft", "textures/gui/sprites/container/slot/helmet.png");

    // 像素偏移量（可根据需求调整）
    private static final int OFFSET_X = 102;
    private static final int OFFSET_Y = 3;

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

        // 屏幕宽高
        Level clientLevel = Minecraft.getInstance().level;
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        if (clientLevel != null && localPlayer != null) {
            if (clientLevel.isClientSide) {
                int screenWidth = mc.getWindow().getGuiScaledWidth();
                int screenHeight = mc.getWindow().getGuiScaledHeight();

                // 计算图片宽高（假设图片为16x16像素）
                int imageWidth = 16;
                int imageHeight = 16;

                int LowerHeight = screenHeight - imageHeight - OFFSET_Y ;
                int HigherHeight = screenHeight - imageHeight - imageHeight - OFFSET_Y;
                int ChangedLowerHeight = screenHeight - imageHeight - OFFSET_Y - imageHeight - 2;
                int ChangedHigherHeight = screenHeight - imageHeight - OFFSET_Y - imageHeight - imageHeight - 2;


                // 左下角坐标计算：(0, 屏幕高度) 为基准，加上偏移量
                int Boots_X = screenWidth / 2 - imageWidth / 2 + OFFSET_X; // 水平居中
                int Boots_y ;

                int Leggings_X = screenWidth / 2 - imageWidth / 2 + OFFSET_X; // 水平居中
                int Leggings_y ;

                int Chestplate_X = screenWidth / 2 - imageWidth / 2 - OFFSET_X; // 水平居中
                int Chestplate_y ;

                int Helmet_X = screenWidth / 2 - imageWidth / 2 - OFFSET_X; // 水平居中
                int Helmet_y ;



                // 获取玩家装备盔甲
                ItemStack bootsStack = localPlayer.getItemBySlot(EquipmentSlot.FEET);
                ItemStack leggingsStack = localPlayer.getItemBySlot(EquipmentSlot.LEGS);
                ItemStack chestStack = localPlayer.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack helmetStack = localPlayer.getItemBySlot(EquipmentSlot.HEAD);

                ItemStack offhandItem = localPlayer.getOffhandItem();
                ItemStack mainHandItem = localPlayer.getMainHandItem();



                if (!offhandItem.isEmpty()) {
                    // 玩家副手有物品
                    Chestplate_y = ChangedLowerHeight;
                    Helmet_y = ChangedHigherHeight;
                } else {
                    // 玩家副手没有物品
                    Chestplate_y = LowerHeight;
                    Helmet_y = HigherHeight;
                }

                if (!mainHandItem.isEmpty()) {
                    // 玩家副手有物品
                    Boots_y = ChangedLowerHeight;
                    Leggings_y = ChangedHigherHeight;
                } else {
                    // 玩家副手没有物品
                    Boots_y = LowerHeight;
                    Leggings_y = HigherHeight;
                }



                // 判断是否为空（有穿装备）
                if (!bootsStack.isEmpty()) {
                    guiGraphics.renderItem(bootsStack, Boots_X, Boots_y);

                }

                if (!leggingsStack.isEmpty()) {
                    guiGraphics.renderItem(leggingsStack, Leggings_X, Leggings_y);

                }

                if (!chestStack.isEmpty()) {
                    guiGraphics.renderItem(chestStack, Chestplate_X, Chestplate_y);

                }

                if (!helmetStack.isEmpty()) {
                    guiGraphics.renderItem(helmetStack, Helmet_X, Helmet_y);

                }

                if (!offhandItem.isEmpty()) {
                    guiGraphics.renderItem(offhandItem, Chestplate_X, Chestplate_y + 18);
                }

                if (!mainHandItem.isEmpty()) {
                    guiGraphics.renderItem(mainHandItem, Boots_X, Boots_y + 18 );
                }

                // 获取ItemRenderer实例
                //ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                //boolean isWearingIronBoots = bootsStack.getItem() == Items.IRON_BOOTS;

                // 判断是否穿戴靴子
                // 绘制图片

                if (bootsStack.isEmpty()) {
                    if (EmptyBootsTexture != null) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                EmptyBootsTexture,
                                Boots_X, Boots_y,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }

                if (leggingsStack.isEmpty()) {
                    if (EmptyLeggingsTexture != null) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                EmptyLeggingsTexture,
                                Leggings_X, Leggings_y,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }
                if (helmetStack.isEmpty()) {
                    if (EmptyHelmetTexture != null) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                EmptyHelmetTexture,
                                Helmet_X, Helmet_y,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }

                if (chestStack.isEmpty()) {
                    if (EmptyChestplateTexture != null) {
                        guiGraphics.blit(
                                RenderType::guiTextured,
                                EmptyChestplateTexture,
                                Chestplate_X, Chestplate_y,
                                0, 0, // 纹理坐标
                                16, 16,
                                16, 16 // 渲染宽高
                        );
                    }
                }
                // ... 其他渲染逻辑 ...



            }
        }
    }

}

