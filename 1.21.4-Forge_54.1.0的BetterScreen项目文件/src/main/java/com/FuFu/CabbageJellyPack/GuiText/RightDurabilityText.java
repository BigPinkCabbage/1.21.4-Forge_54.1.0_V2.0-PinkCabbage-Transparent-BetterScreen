package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.key.Events;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RightDurabilityText {

    // 像素偏移量（可根据需求调整）
    //private static final int OFFSET_X = 102;
    private static final int OFFSET_Y = -2;

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

                int hotbarX = (screenWidth / 2) + 113;

                // 计算图片宽高（假设图片为16x16像素）
                int imageHeight = 16;

                int LowerHeight = Math.round((screenHeight - imageHeight - OFFSET_Y) / 0.8F);
                int HigherHeight = Math.round((screenHeight - imageHeight - imageHeight - OFFSET_Y) / 0.8F);
                int ChangedLowerHeight = Math.round((screenHeight - imageHeight - OFFSET_Y - imageHeight - 1 ) / 0.8F);
                int ChangedHigherHeight = Math.round((screenHeight - imageHeight - OFFSET_Y - imageHeight - imageHeight - 1) / 0.8F);

                // 左下角坐标计算：(0, 屏幕高度) 为基准，加上偏移量
                int Boots_y ;
                int Leggings_y ;

                // 获取玩家装备盔甲
                ItemStack bootsStack = localPlayer.getItemBySlot(EquipmentSlot.FEET);
                ItemStack leggingsStack = localPlayer.getItemBySlot(EquipmentSlot.LEGS);

                ItemStack offhandItem = localPlayer.getOffhandItem();
                ItemStack mainHandItem = localPlayer.getMainHandItem();

                if (!mainHandItem.isEmpty()) {
                    // 玩家副手有物品
                    Boots_y = ChangedLowerHeight;
                    Leggings_y = ChangedHigherHeight;
                } else {
                    // 玩家副手没有物品
                    Boots_y =  LowerHeight;
                    Leggings_y = HigherHeight;
                }

                int BootsMaxDurability = bootsStack.getMaxDamage();
                int BootsCurrentDamage = bootsStack.getDamageValue();
                int BootsRemainingDurability = BootsMaxDurability - BootsCurrentDamage;
                String BootsDurabilityText = String.format("%d/%d", BootsRemainingDurability, BootsMaxDurability);
                float BootsDurabilityPercent = (float) BootsRemainingDurability / BootsMaxDurability;

                int LeggingsMaxDurability = leggingsStack.getMaxDamage();
                int LeggingsCurrentDamage = leggingsStack.getDamageValue();
                int LeggingsRemainingDurability = LeggingsMaxDurability - LeggingsCurrentDamage;
                String LeggingsDurabilityText = String.format("%d/%d", LeggingsRemainingDurability, LeggingsMaxDurability);
                float LeggingsDurabilityPercent = (float) LeggingsRemainingDurability / LeggingsMaxDurability;

                int MainHandMaxDurability = mainHandItem.getMaxDamage();
                int MainHandCurrentDamage = mainHandItem.getDamageValue();
                int MainHandRemainingDurability = MainHandMaxDurability- MainHandCurrentDamage;
                String MainHandDurabilityText = String.format("%d/%d", MainHandRemainingDurability, MainHandMaxDurability);
                float MainHandDurabilityPercent = (float) MainHandRemainingDurability /MainHandMaxDurability;

                int count = 0;

                // 遍历玩家背包（0-35是主背包，36-39是快捷栏，40是副手）
                for (ItemStack stack : localPlayer.getInventory().items) {
                    if (stack.getItem() == Items.ARROW) { // 普通箭
                        count += stack.getCount();
                    }
                    // 如果需要包括药箭（Tipped Arrow）
                    else if (stack.getItem() == Items.TIPPED_ARROW) {
                        count += stack.getCount();
                    }

                }
                int offhandArrowCount = offhandItem.getCount();
                count += offhandArrowCount ;

                String ArrowText = String.format("%d", count );

                int BootsColor;
                if (BootsDurabilityPercent < 0.2f) {
                    BootsColor = 0xFF0000; // 红色
                } else if (BootsDurabilityPercent < 0.5f) {
                    BootsColor = 0xFFA500; // 橙色
                } else {
                    BootsColor = 0x00FF00; // 绿色
                }

                int LeggingsColor;
                if (LeggingsDurabilityPercent < 0.2f) {
                    LeggingsColor = 0xFF0000; // 红色
                } else if (LeggingsDurabilityPercent < 0.5f) {
                    LeggingsColor = 0xFFA500; // 橙色
                } else {
                    LeggingsColor = 0x00FF00; // 绿色
                }

                int MainHandColor;
                if (MainHandDurabilityPercent < 0.2f) {
                    MainHandColor = 0xFF0000; // 红色
                } else if (LeggingsDurabilityPercent < 0.5f) {
                    MainHandColor = 0xFFA500; // 橙色
                } else {
                    MainHandColor = 0x00FF00; // 绿色
                }

                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(hotbarX, 0, 0);
                guiGraphics.pose().scale(0.8F, 0.8F, 1.0F); // 缩放文字

                if (!bootsStack.isEmpty()) {
                    guiGraphics.drawString(
                            mc.font,
                            BootsDurabilityText,
                            0,
                            Boots_y - 1,
                            BootsColor,
                            true
                    );
                }

                if (!leggingsStack.isEmpty()) {
                    guiGraphics.drawString(
                            mc.font,
                            LeggingsDurabilityText,
                            0,
                            Leggings_y - 1,
                            LeggingsColor,
                            true
                    );
                }

                if (!mainHandItem.isEmpty() && mainHandItem.getMaxDamage() > 0 ) {
                    guiGraphics.drawString(
                            mc.font,
                            MainHandDurabilityText,
                            0,
                            Boots_y + 21,
                            MainHandColor,
                            true
                    );
                }

                if (mainHandItem.getItem() == Items.ARROW || mainHandItem.getItem() == Items.TIPPED_ARROW) {
                    guiGraphics.drawString(
                            mc.font,
                            ArrowText,
                            0,
                            Boots_y + 21,
                            0xFFFFFF,
                            true
                    );
                }


                guiGraphics.pose().popPose();
            }
        }
    }
}

