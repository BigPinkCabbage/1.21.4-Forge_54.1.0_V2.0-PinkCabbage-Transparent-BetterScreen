package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenAccessor {
    @Accessor("playerInventoryTitle")
    Component getPlayerInventoryTitle();

    @Accessor("hoveredSlot") // 字段名必须与原类一致
    Slot getHoveredSlot();

    @Accessor("leftPos") // 映射原字段 leftPos
    int getLeftPos();

    @Accessor("topPos") // 映射原字段 topPos
    int getTopPos();

}


