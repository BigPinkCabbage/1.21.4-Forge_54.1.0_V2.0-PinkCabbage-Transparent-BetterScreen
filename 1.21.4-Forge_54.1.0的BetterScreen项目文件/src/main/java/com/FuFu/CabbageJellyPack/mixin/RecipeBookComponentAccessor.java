package com.FuFu.CabbageJellyPack.mixin;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import javax.annotation.Nullable;
import java.util.List;

@Mixin(RecipeBookComponent.class)
public interface RecipeBookComponentAccessor {
    /**
     * 获取 RecipeBookComponent 中的 tabButtons 列表
     */
    @Accessor("tabButtons")
   List<RecipeBookTabButton> getTabButtons() ;

    @Accessor("book")
    ClientRecipeBook getBooks();


    @Accessor("searchBox")
    @Nullable
    EditBox getSearchBox();

}
