package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.key.Events;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LeftDurabilityText {

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

                int hotbarX = (screenWidth / 2) - 99;
                int leftTextX = hotbarX - 9;

                // 计算图片宽高（假设图片为16x16像素）
                int imageHeight = 16;

                int LowerHeight = Math.round((screenHeight - imageHeight - OFFSET_Y) / 0.8F);
                int HigherHeight = Math.round((screenHeight - imageHeight - imageHeight - OFFSET_Y) / 0.8F);
                int ChangedLowerHeight = Math.round((screenHeight - imageHeight - OFFSET_Y - imageHeight - 1 ) / 0.8F);
                int ChangedHigherHeight = Math.round((screenHeight - imageHeight - OFFSET_Y - imageHeight - imageHeight - 1) / 0.8F);

                // 左下角坐标计算：(0, 屏幕高度) 为基准，加上偏移量

                int Helmet_y ;


                ItemStack helmetStack = localPlayer.getItemBySlot(EquipmentSlot.HEAD);
                ItemStack chestStack = localPlayer.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack offhandItem = localPlayer.getOffhandItem();

                if (!offhandItem.isEmpty()) {
                    // 玩家副手有物品
                    Helmet_y = ChangedHigherHeight;
                } else {
                    // 玩家副手没有物品
                    Helmet_y = HigherHeight;
                }

                int Chestplate_y;

                if (!offhandItem.isEmpty()) {
                    // 玩家副手有物品
                    Chestplate_y = ChangedLowerHeight;

                } else {
                    // 玩家副手没有物品
                    Chestplate_y = LowerHeight;

                }

                int offhandItemMaxDurability = offhandItem.getMaxDamage();
                int offhandItemCurrentDamage = offhandItem.getDamageValue();
                int offhandItemRemainingDurability = offhandItemMaxDurability - offhandItemCurrentDamage;
                String offhandItemDurabilityText = String.format("%d/%d", offhandItemRemainingDurability, offhandItemMaxDurability);
                float offhandItemDurabilityPercent = (float) offhandItemRemainingDurability / offhandItemMaxDurability;

                int HelmetMaxDurability = helmetStack.getMaxDamage();
                int HelmetCurrentDamage = helmetStack.getDamageValue();
                int HelmetRemainingDurability = HelmetMaxDurability - HelmetCurrentDamage;
                String HelmetDurabilityText = String.format("%d/%d", HelmetRemainingDurability, HelmetMaxDurability);
                float HelmetDurabilityPercent = (float) HelmetRemainingDurability / HelmetMaxDurability;

                int ChestMaxDurability = chestStack.getMaxDamage();
                int ChestCurrentDamage = chestStack.getDamageValue();
                int ChestRemainingDurability = ChestMaxDurability - ChestCurrentDamage;
                String ChestDurabilityText = String.format("%d/%d", ChestRemainingDurability, ChestMaxDurability);
                float ChestDurabilityPercent = (float) ChestRemainingDurability / ChestMaxDurability;

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


                Font font = Minecraft.getInstance().font;

                Component component = Component.literal(ChestDurabilityText);
                int ChestTextWidth = (font.width(component));
                int HelmetTextWidth = font.width(HelmetDurabilityText);
                int offhandTextWidth = font.width(offhandItemDurabilityText);
                String ArrowText = String.format("%d", count );
                int offhandTextWidthArrow = font.width(ArrowText);
                int max = Math.max(offhandTextWidthArrow,Math.max(offhandTextWidth, Math.max(ChestTextWidth, HelmetTextWidth)));

                int offhandColor;
                if (offhandItemDurabilityPercent < 0.2f) {
                    offhandColor = 0xFF0000; // 红色
                } else if (offhandItemDurabilityPercent < 0.5f) {
                    offhandColor = 0xFFA500; // 橙色
                } else {
                    offhandColor = 0x00FF00; // 绿色
                }

                int HelmetColor;
                if (HelmetDurabilityPercent < 0.2f) {
                    HelmetColor = 0xFF0000; // 红色
                } else if (HelmetDurabilityPercent < 0.5f) {
                    HelmetColor = 0xFFA500; // 橙色
                } else {
                    HelmetColor = 0x00FF00; // 绿色
                }

                int ChestColor;
                if (ChestDurabilityPercent < 0.2f) {
                    ChestColor = 0xFF0000; // 红色
                } else if (ChestDurabilityPercent < 0.5f) {
                    ChestColor = 0xFFA500; // 橙色
                } else {
                    ChestColor = 0x00FF00; // 绿色
                }

                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(leftTextX - max, 0, 0);

                /*
                if (!offhandItem.isEmpty() && offhandItem.getMaxDamage() > 0 ) {
                    guiGraphics.pose().translate(leftTextX - offhandTextWidth, 0, 0);
                }

                if (offhandItem.getItem() == Items.ARROW || offhandItem.getItem() == Items.TIPPED_ARROW) {
                    guiGraphics.pose().translate(leftTextX - (offhandTextWidthArrow / 0.8), 0, 0);
                }
                 */
                guiGraphics.pose().scale(0.8F, 0.8F, 1.0F); // 缩放文字

                if (!offhandItem.isEmpty() && offhandItem.getMaxDamage() > 0 ) {
                    guiGraphics.drawString(
                            mc.font,
                            offhandItemDurabilityText,
                            0,
                            LowerHeight,
                            offhandColor,
                            true
                    );
                }

                if (offhandItem.getItem() == Items.ARROW || offhandItem.getItem() == Items.TIPPED_ARROW) {
                    guiGraphics.drawString(
                            mc.font,
                            ArrowText,
                            0,
                            LowerHeight,
                            0xFFFFFF,
                            true
                    );
                }

                if (!helmetStack.isEmpty()) {
                    guiGraphics.drawString(
                            mc.font,
                            HelmetDurabilityText,
                            0,
                            Helmet_y,
                            HelmetColor,
                            true
                    );
                }

                if (!chestStack.isEmpty()) {
                    guiGraphics.drawString(
                            mc.font,
                            ChestDurabilityText,
                            0,
                            Chestplate_y,
                            ChestColor,
                            true
                    );
                }

                guiGraphics.pose().popPose();
            }
        }
    }
}

