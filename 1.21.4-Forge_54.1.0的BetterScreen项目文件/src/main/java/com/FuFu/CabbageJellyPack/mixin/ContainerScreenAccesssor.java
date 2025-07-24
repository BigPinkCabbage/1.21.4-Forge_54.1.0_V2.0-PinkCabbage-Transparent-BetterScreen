package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ContainerScreen.class)
public interface ContainerScreenAccesssor {
    @Accessor("containerRows")
    int getContainerRows();
}
