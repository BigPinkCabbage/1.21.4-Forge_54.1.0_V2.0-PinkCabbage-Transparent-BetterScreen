package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceScreen.class)
public interface AbstractFurnaceScreenAccessor {
    @Accessor("litProgressSprite")
    ResourceLocation getLitProgressSprite();

    @Accessor("burnProgressSprite")
    ResourceLocation getBurnProgressSprite();
}
