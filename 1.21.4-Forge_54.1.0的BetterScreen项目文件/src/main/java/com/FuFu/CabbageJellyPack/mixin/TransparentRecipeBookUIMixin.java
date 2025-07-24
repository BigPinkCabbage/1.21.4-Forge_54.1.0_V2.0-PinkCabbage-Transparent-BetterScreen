package com.FuFu.CabbageJellyPack.mixin;

import com.FuFu.CabbageJellyPack.GuiText.RenderLossTab ;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;


@Mixin(RecipeBookComponent.class)
public class TransparentRecipeBookUIMixin {



    // 在绘制时
    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
    ))
    private void setAlphaBeforeBlit(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
    }


    // 在 filterButton 渲染前
    @Inject(
            method = "render",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;filterButton:Lnet/minecraft/client/gui/components/StateSwitchingButton;",
                    opcode = Opcodes.GETFIELD
            )
    )
    private void setAlphaBeforeFilterButton(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }





    // 在 RecipeBookPage渲染
    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookPage;render(Lnet/minecraft/client/gui/GuiGraphics;IIIIF)V"
            )
    )
    private void renderTransparentTabs(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {

        // 使用 Mixin Accessor 接口获取 tabButtons
        RecipeBookComponentAccessor accessor = (RecipeBookComponentAccessor) this;
        List<RecipeBookTabButton> tabButtons = accessor.getTabButtons();
        ClientRecipeBook recipeBook = accessor.getBooks();

        for (RecipeBookTabButton button : tabButtons) {
            // 1. 检查是否是搜索标签（始终显示）
            if (button.getCategory() instanceof SearchRecipeBookCategory) {
                RenderLossTab.RenderLoss(guiGraphics, button, InventoryAlpha);
                continue;
            }

            // 2. 检查玩家是否拥有该分类的配方
            if (button.updateVisibility(recipeBook)) {
                RenderLossTab.RenderLoss(guiGraphics, button, InventoryAlpha);
            }
        }

        EditBox searchBox = accessor.getSearchBox();

        if (searchBox != null) {
            // 渲染搜索框
            searchBox.render(guiGraphics, mouseX, mouseY, partialTick);


        }

    }

    /*
    //搜索框
    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/EditBox;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
                    ordinal = 0
            ),
            cancellable = true
    )
    private void cancelOriginalSearchBoxRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        ci.cancel(); // 取消原版渲染
    }
    */



/*
    // 在 searchBox 渲染后恢复不透明
    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/EditBox;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            shift = At.Shift.AFTER
    ))
    private void resetAlphaAfterSearchBox(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // 恢复不透明
    }

*/
/*
    // 在 filterButton 渲染后恢复不透明
    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/StateSwitchingButton;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
                    shift = At.Shift.AFTER
            )
    )
    private void renderTabIcons(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {

        }



 */







/*
    // 在 recipeBookPage 渲染前设置半透明
    @Inject(method = "render", at = @At("HEAD"))
    private void beforeRender(GuiGraphics p_283597_, int p_282668_, int p_283506_, float p_282813_, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void afterRender(GuiGraphics p_283597_, int p_282668_, int p_283506_, float p_282813_, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }



 */

/*
    // 在背景绘制前设置半透明
    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
    ))
    private void setAlphaBeforeBlit(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);  // 透明度
    }

    // 在 filterButton 渲染后恢复透明度
    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/StateSwitchingButton;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            shift = At.Shift.AFTER
    ))
    private void resetAlphaAfterFilterButton(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);  // 恢复不透明
    }


 */


/*
//原本想用mixin替换原版纹理，但是mixin限制太大，被迫放弃
@Mixin(RecipeButton.class)
public class TransparentRecipeTabButtonMixin {

    @Shadow @Final @Mutable
    private static ResourceLocation SLOT_MANY_CRAFTABLE_SPRITE;

    @Shadow @Final @Mutable
    private static ResourceLocation SLOT_CRAFTABLE_SPRITE;

    @Shadow @Final @Mutable
    private static ResourceLocation SLOT_MANY_UNCRAFTABLE_SPRITE;

    @Shadow @Final @Mutable
    private static ResourceLocation SLOT_UNCRAFTABLE_SPRITE;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replaceSprites(CallbackInfo ci) {
        SLOT_MANY_CRAFTABLE_SPRITE = ResourceLocation.fromNamespaceAndPath("minecraft", "recipe_book/slot_many_craftable");
        SLOT_CRAFTABLE_SPRITE = ResourceLocation.fromNamespaceAndPath("minecraft", "recipe_book/slot_craftable");
        SLOT_MANY_UNCRAFTABLE_SPRITE = ResourceLocation.fromNamespaceAndPath("minecraft", "recipe_book/slot_many_uncraftable");
        SLOT_UNCRAFTABLE_SPRITE = ResourceLocation.fromNamespaceAndPath("minecraft", "recipe_book/slot_uncraftable");
    }
}

*/
}





