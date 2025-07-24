package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.key.Events;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ToolDurabilityText {
    private static ItemStack lastStack = null;
    private static long itemPickupTime = 0;
    private static final long DISPLAY_DURATION = 1620; // 2秒
    private static float currentAlpha = 1.0f; // 当前透明度
    private static final float FADE_SPEED = 0.02f; // 渐隐速度，每帧减0.02

    public static void render(GuiGraphics guiGraphics, float partialTicks) {
        if (!Events.showDurability) {
            return; // 如果未开启显示，直接返回
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (mc.player.isCreative()) {
            // 在创造模式，不显示
            return;
        }
        // 检测攻击动作（假设用swinging作为攻击标志）
        if (mc.player.swinging) {
            lastStack = mc.player.getMainHandItem().copy();
            itemPickupTime = System.currentTimeMillis();
            currentAlpha = 1.0f;
        }

        ItemStack currentStack = mc.player.getMainHandItem();

        // 判断物品是否变化
        if (!areItemsEqual(currentStack, lastStack)) {
            lastStack = currentStack.copy();
            itemPickupTime = System.currentTimeMillis();
            currentAlpha = 1.0f; // 重新显示，恢复不透明
        }

        long elapsedTime = System.currentTimeMillis() - itemPickupTime;

        if (elapsedTime > DISPLAY_DURATION) {
            // 超过两秒，开始渐隐
            currentAlpha -= FADE_SPEED;
            if (currentAlpha < 0f) currentAlpha = 0f; // 保持在0到1之间
        }

        if (currentAlpha <= 0.05f) {
            // 完全透明，不绘制
            return;
        }

        // 判断物品是否有耐久度
        if (currentStack != null && currentStack.getMaxDamage() > 0) {
            String itemName = currentStack.getDisplayName().getString();
            int maxDurability = currentStack.getMaxDamage();
            int currentDamage = currentStack.getDamageValue();
            int remainingDurability = maxDurability - currentDamage;
            float durabilityPercent = (float) remainingDurability / maxDurability;

            String durabilityText = String.format("耐久度(%d/%d)", remainingDurability, maxDurability);

            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();

            int nameWidth = mc.font.width(itemName);
            int durabilityWidth = mc.font.width(durabilityText);

            int x = (width - Math.max(nameWidth, durabilityWidth)) / 2;
            int yOffsetPixels = 60;
            int y = height - yOffsetPixels;

            int color;
            if (durabilityPercent < 0.2f) {
                color = 0xFF0000; // 红色
            } else if (durabilityPercent < 0.5f) {
                color = 0xFFA500; // 橙色
            } else {
                color = 0x00FF00; // 绿色
            }

            // 设置颜色带透明度
            int colorDurability = (int) (currentAlpha * 255) << 24 | (color & 0xFFFFFF);

            guiGraphics.drawString(mc.font, durabilityText, x, y + mc.font.lineHeight + 2, colorDurability | (int)(currentAlpha * 255) << 24, true);
        }
    }

    private static boolean areItemsEqual(ItemStack a, ItemStack b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.getItem() == b.getItem();
    }

}