package com.FuFu.CabbageJellyPack.GuiText;

import com.FuFu.CabbageJellyPack.mixin.AbstractContainerScreenAccessor;
import com.FuFu.CabbageJellyPack.mixin.AbstractFurnaceScreenAccessor;
import com.FuFu.CabbageJellyPack.mixin.ContainerScreenAccesssor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;
import static com.FuFu.CabbageJellyPack.Event.StaticData.BREWINGSTAND_X;
import static com.FuFu.CabbageJellyPack.Event.StaticData.CHEST_J;
import static com.FuFu.CabbageJellyPack.Event.StaticData.DISPENSER_J;

public class RenderLossUI extends Screen{
    public static final ResourceLocation RESET_SHULKERBOX = ResourceLocation.tryBuild("minecraft", "textures/gui/container/shulker_box.png");
    public static final ResourceLocation RESET_GRINDSTONE = ResourceLocation.tryBuild("minecraft", "textures/gui/container/grindstone.png");
    public static final ResourceLocation RESET_HOPPER = ResourceLocation.tryBuild("minecraft","textures/gui/container/hopper.png");
    private static final ResourceLocation RESET_CONTAINER = ResourceLocation.tryBuild("minecraft","textures/gui/container/generic_54.png");
    private static final ResourceLocation RESET_CRAFTING = ResourceLocation.tryBuild("minecraft","textures/gui/container/crafting_table.png");
    private static final ResourceLocation RESET_CRAFTER = ResourceLocation.tryBuild("minecraft","textures/gui/container/crafter.png");
    private static final ResourceLocation RESET_SLOT_HIGHLIGHT_FRONT_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/slot_highlight_front.png");
    private static final ResourceLocation RESET_SLOT_HIGHLIGHT_BACK_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/slot_highlight_back.png");
    private static final ResourceLocation RESET_POWERED_REDSTONE_LOCATION_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/crafter/powered_redstone.png");
    private static final ResourceLocation RESET_UNPOWERED_REDSTONE_LOCATION_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/crafter/unpowered_redstone.png");
    private static final ResourceLocation RESET_BUTTON = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/recipe_book/button.png");
    private static final ResourceLocation RESET_BUTTON_HIGHLIGHTED = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/recipe_book/button_highlighted.png");
    public static final ResourceLocation RESET_SMOKER = ResourceLocation.tryBuild("minecraft", "textures/gui/container/smoker.png");
    public static final ResourceLocation RESET_FURNACE = ResourceLocation.tryBuild("minecraft", "textures/gui/container/furnace.png");
    public static final ResourceLocation RESET_BLAST_FURNACE = ResourceLocation.tryBuild("minecraft", "textures/gui/container/blast_furnace.png");
    public static final ResourceLocation RESET_DISPENSER = ResourceLocation.tryBuild("minecraft", "textures/gui/container/dispenser.png");
    public static final ResourceLocation RESET_BREWING_STAND = ResourceLocation.tryBuild("minecraft", "textures/gui/container/brewing_stand.png");
    private static final ResourceLocation RESET_FUEL_LENGTH_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/brewing_stand/fuel_length.png");
    private static final ResourceLocation RESET_BREW_PROGRESS_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/brewing_stand/brew_progress.png");
    private static final ResourceLocation RESET_BUBBLES_SPRITE = ResourceLocation.tryBuild("minecraft","textures/gui/sprites/container/brewing_stand/bubbles.png");


    protected RenderLossUI(Component pTitle) {
        super(pTitle);
    }

    public static void onScreenRender(ScreenEvent.Render.Post event) {

        Screen currentScreen = Minecraft.getInstance().screen;

        AbstractContainerScreen<?> screen = null;

        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen) {
            screen = (AbstractContainerScreen<?>) Minecraft.getInstance().screen;
            // 使用 screen 实例
        }

        if (Minecraft.getInstance().screen instanceof ContainerScreen containerscreen) {
            // 使用 screen 实例
            ContainerScreenAccesssor screenMixin = (ContainerScreenAccesssor) containerscreen;
            CONTAINERROWS = screenMixin.getContainerRows();

        }

        Component title = null; // 直接调用原版方法
        if (currentScreen != null) {
            title = currentScreen.getTitle();
        }
        Font font = null;
        if (currentScreen != null) {
            font = currentScreen.getFont();
        }


        Component inventoryTitle = null;
        if (screen != null) {
            inventoryTitle = ((AbstractContainerScreenAccessor) screen).getPlayerInventoryTitle();
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();

        Minecraft mc = Minecraft.getInstance();

        int screenHeight = mc.getWindow().getGuiScaledHeight();

        Player player = Minecraft.getInstance().player;

        if (mc.player == null) return;

        if (openCreativeMode) {
            if (player != null && player.isCreative()) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 100);
                ItemStack compassStack = new ItemStack(Items.COMPASS);
                guiGraphics.renderItem(compassStack, currentleftPos + currentimageWidth - 21, currenttopPos - 19);

                // 钻石镐
                ItemStack diamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
                guiGraphics.renderItem(diamondPickaxe, currentleftPos + 5, currenttopPos + currentimageHeight + 3);

                // 下界合金剑
                ItemStack netheriteSword = new ItemStack(Items.NETHERITE_SWORD);
                guiGraphics.renderItem(netheriteSword, currentleftPos + 32, currenttopPos + currentimageHeight + 3);

                // 金苹果
                ItemStack goldenApple = new ItemStack(Items.GOLDEN_APPLE);
                guiGraphics.renderItem(goldenApple, currentleftPos + 59, currenttopPos + currentimageHeight + 3);

                ItemStack ironIngot = new ItemStack(Items.IRON_INGOT);
                guiGraphics.renderItem(ironIngot, currentleftPos + 86, currenttopPos + currentimageHeight + 3);
                // 猪的刷怪蛋
                ItemStack pigSpawnEgg = new ItemStack(Items.PIG_SPAWN_EGG);
                guiGraphics.renderItem(pigSpawnEgg, currentleftPos + 113, currenttopPos + currentimageHeight + 3);

                guiGraphics.pose().popPose();
            }
        }

        if (ShulkerBox) {
            if (RESET_SHULKERBOX != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_SHULKERBOX,
                        SHULKERBOX_I, SHULKERBOX_J,
                        0, 0, // 纹理坐标
                        SHULKERBOXWIDTH, SHULKERBOXHEIGHT, 256, 256
                );
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.pose().popPose();
                if (font != null) {
                    guiGraphics.drawString(font, title, SHULKERBOX_I + 8, SHULKERBOX_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, SHULKERBOX_I + 8, SHULKERBOX_J + SHULKERBOXHEIGHT - 95, 0, false);
                    }
                }
            }
        }

        if (Grindstone) {
            if (RESET_GRINDSTONE != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_GRINDSTONE,
                        GRINDSTONE_I, GRINDSTONE_J,
                        0, 0, // 纹理坐标
                        GRINDSTONEWIDTH, GRINDSTONEHEIGHT, 256, 256
                );
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.pose().popPose();
                if (font != null) {
                    guiGraphics.drawString(font, title, GRINDSTONE_I + 8, GRINDSTONE_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, GRINDSTONE_I + 8, GRINDSTONE_J + GRINDSTONEHEIGHT - 94, 0, false);
                    }
                }
            }
        }

        if (Hopper) {
            if (RESET_HOPPER != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_HOPPER,
                        HOPPER_I, HOPPER_J,
                        0, 0, // 纹理坐标
                        HOPPERWIDTH, HOPPERHEIGHT, 256, 256
                );
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.pose().popPose();
                if (font != null) {
                    guiGraphics.drawString(font, title, HOPPER_I + 8, HOPPER_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, HOPPER_I + 8, HOPPER_J + HOPPERHEIGHT - 94, 0, false);
                    }
                }
            }
        }


        //if (BigChest || SmallChest || EnderChest || Barrel || MinecartChest || ChestBoat || ChestRaft) {
        if (player != null && player.containerMenu instanceof ChestMenu){
            if (RESET_CONTAINER != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_CONTAINER,
                        CHEST_I, CHEST_J,
                        0, 0, // 纹理坐标
                        CHESTWIDTH, CONTAINERROWS * 18 + 17, 256, 256
                );
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_CONTAINER,
                        CHEST_I, CHEST_J + CONTAINERROWS * 18 + 17,
                        0, 126.0F, // 纹理坐标
                        CHESTWIDTH, 96, 256, 256
                );
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.pose().popPose();

                if (font != null) {
                    guiGraphics.drawString(font, title, CHEST_I + 8, CHEST_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, CHEST_I + 8, CHEST_J + CHESTHEIGHT - 94, 0, false);
                    }
                }
            }
        }


        if (player != null && player.containerMenu instanceof CrafterMenu) {
            if (RESET_CRAFTER != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_CRAFTER,
                        CRAFTER_I, CRAFTER_J,
                        0, 0, // 纹理坐标
                        CRAFTERWIDTH, CRAFTERHEIGHT, 256, 256
                );
                if (POWERED) {
                    if (RESET_POWERED_REDSTONE_LOCATION_SPRITE != null) {
                        guiGraphics.blit(RenderType::guiTextured,
                                RESET_POWERED_REDSTONE_LOCATION_SPRITE,
                                REDSTONE_I, REDSTONE_J,
                                0, 0,
                                16, 16,
                                16, 16);
                    }

                } else {
                    if (RESET_UNPOWERED_REDSTONE_LOCATION_SPRITE != null) {
                        guiGraphics.blit(RenderType::guiTextured,
                                RESET_UNPOWERED_REDSTONE_LOCATION_SPRITE,
                                REDSTONE_I, REDSTONE_J,
                                0, 0,
                                16, 16,
                                16, 16);
                    }
                }
                guiGraphics.pose().popPose();

                if (font != null) {
                    guiGraphics.drawString(font, title, CRAFTER_I + CRAFTERTITLE_X, CRAFTER_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, CRAFTER_I + 8, CRAFTER_J + CRAFTERHEIGHT - 94, 0, false);
                    }
                }
            }
        }

/*
// 检查按钮是否被点击
        if (screen instanceof RecipeBookButtonAccessor accessor) {
            boolean wasRecipeBookButtonClicked = accessor.wasRecipeBookButtonClicked(event.getMouseX(),event.getMouseY(),int button)
            if (wasRecipeBookButtonClicked) {
                // 执行点击后的逻辑
            }
        }


 */
        double mouseX = event.getMouseX();
        double mouseY = event.getMouseY();

        if (player != null && player.containerMenu instanceof CraftingMenu) {
          if (RESET_BUTTON != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 1);
            guiGraphics.blit(
                    RenderType::guiTextured,
                    RESET_BUTTON,
                    CRAFTING_I + 5, BUTTON_Y,
                    0, 0, // 纹理坐标
                    20, 18, 20, 18
            );
            guiGraphics.pose().popPose();
          }
            // 获取鼠标坐标


          if (CraftingMouseOverRect(mouseX, mouseY)) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 10);
            if (RESET_BUTTON_HIGHLIGHTED != null) {
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_BUTTON_HIGHLIGHTED,
                        CRAFTING_I + 5, BUTTON_Y,
                        0, 0, // 纹理坐标
                        20, 18, 20, 18
                );
            }
            guiGraphics.pose().popPose();
          }

          if (RESET_CRAFTING != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 0);
            guiGraphics.blit(
                    RenderType::guiTextured,
                    RESET_CRAFTING,
                    CRAFTING_I, CRAFTING_J,
                    0, 0, // 纹理坐标
                    CRAFTINGWIDTH, CRAFTINGHEIGHT, 256, 256
            );
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
            guiGraphics.pose().popPose();

            if (font != null) {
                guiGraphics.drawString(font, title, CRAFTING_I + 29, CRAFTING_J + 6, 0, false);
            }
            if (font != null) {
                if (inventoryTitle != null) {
                    guiGraphics.drawString(font, inventoryTitle, CRAFTING_I + 8, CRAFTING_J + CRAFTINGHEIGHT - 94, 0, false);
                }
            }
          }
        }



        if (AllFurnace) {
            if (RESET_BUTTON != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 1);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_BUTTON,
                        FURNACE_I + 20, screenHeight / 2 - 49,
                        0, 0, // 纹理坐标
                        20, 18, 20, 18
                );
                guiGraphics.pose().popPose();
            }

            if (FurnaceMouseOverRect(mouseX, mouseY)) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 10);
                if (RESET_BUTTON_HIGHLIGHTED != null) {
                    guiGraphics.blit(
                            RenderType::guiTextured,
                            RESET_BUTTON_HIGHLIGHTED,
                            FURNACE_I + 20, screenHeight / 2 - 49,
                            0, 0, // 纹理坐标
                            20, 18, 20, 18
                    );
                }
                guiGraphics.pose().popPose();
            }

        }

        if (Furnace) {
            if (RESET_FURNACE != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_FURNACE,
                        FURNACE_I, FURNACE_J,
                        0, 0, // 纹理坐标
                        256, 256, 256, 256
                );
            }
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
            guiGraphics.pose().popPose();

            if (font != null) {
                guiGraphics.drawString(font, title, FURNACE_I + FURNACE_LabelX, FURNACE_J + 6, 0, false);
            }
            if (font != null) {
                if (inventoryTitle != null) {
                    guiGraphics.drawString(font, inventoryTitle, FURNACE_I + 8, FURNACE_J + FURNACEHEIGHT - 94, 0, false);
                }
            }
        }

        if (Blast_Furnace) {
            if (RESET_BLAST_FURNACE != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_BLAST_FURNACE,
                        FURNACE_I, FURNACE_J,
                        0, 0, // 纹理坐标
                        256, 256, 256, 256
                );
            }
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
            guiGraphics.pose().popPose();

            if (font != null) {
                guiGraphics.drawString(font, title, FURNACE_I + FURNACE_LabelX, FURNACE_J + 6, 0, false);
            }
            if (font != null) {
                if (inventoryTitle != null) {
                    guiGraphics.drawString(font, inventoryTitle, FURNACE_I + 8, FURNACE_J + FURNACEHEIGHT - 94, 0, false);
                }
            }
        }

        if (Smoker) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 0);
            if (RESET_SMOKER != null) {
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_SMOKER,
                        FURNACE_I, FURNACE_J,
                        0, 0, // 纹理坐标
                        256, 256, 256, 256
                );
            }
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
            guiGraphics.pose().popPose();

            if (font != null) {
                guiGraphics.drawString(font, title, FURNACE_I + FURNACE_LabelX, FURNACE_J + 6, 0, false);
            }
            if (font != null) {
                if (inventoryTitle != null) {
                    guiGraphics.drawString(font, inventoryTitle, FURNACE_I + 8, FURNACE_J + FURNACEHEIGHT - 94, 0, false);
                }
            }
        }


        if (player != null && player.containerMenu instanceof DispenserMenu) {
            if (RESET_DISPENSER != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_DISPENSER,
                        DISPENSER_I, DISPENSER_J,
                        0, 0, // 纹理坐标
                        DISPENSERWIDTH, DISPENSERHEIGHT, 256, 256
                );
                guiGraphics.pose().popPose();

                if (font != null) {
                    guiGraphics.drawString(font, title, DISPENSER_I + DISPENSERTITLE_X, DISPENSER_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, DISPENSER_I + 8, DISPENSER_J + DISPENSERHEIGHT - 94, 0, false);
                    }
                }
            }
        }

        if (player != null && player.containerMenu instanceof BrewingStandMenu) {
            if (RESET_BREWING_STAND != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 0);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, InventoryAlpha);
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_BREWING_STAND,
                        BREWINGSTAND_I, BREWINGSTAND_J,
                        0, 0, // 纹理坐标
                        BREWINGSTANDWIDTH, BREWINGSTANDHEIGHT,
                        256, 256
                );
                guiGraphics.pose().popPose();

                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 1);
                if (BREWINGSTAND_L > 0) {
                    if (RESET_FUEL_LENGTH_SPRITE != null) {
                        guiGraphics.blit(RenderType::guiTextured,
                                RESET_FUEL_LENGTH_SPRITE,
                                BREWINGSTAND_I + 60, BREWINGSTAND_J + 44,
                                0, 0,
                                BREWINGSTAND_L, 4,
                                18, 4);

                    }
                }
                if (BREWINGSTAND_I1 > 0) {
                    int j1 = (int) (28.0F * (1.0F - (float) BREWINGSTAND_I1 / 400.0F));
                    if (j1 > 0) {
                        if (RESET_BREW_PROGRESS_SPRITE != null) {
                            guiGraphics.blit(RenderType::guiTextured,
                                    RESET_BREW_PROGRESS_SPRITE,
                                    BREWINGSTAND_I + 97, BREWINGSTAND_J + 16,
                                    0, 0,
                                    9, j1,
                                    9, 28);

                        }
                    }

                    if (BREWINGSTAND_J1 > 0) {
                        if (RESET_BUBBLES_SPRITE != null) {
                            guiGraphics.blit(RenderType::guiTextured,
                                    RESET_BUBBLES_SPRITE,
                                    BREWINGSTAND_I + 63, BREWINGSTAND_J + 14 + 29 - BREWINGSTAND_J1,
                                    0, 29 - BREWINGSTAND_J1,
                                    12, BREWINGSTAND_J1,
                                    12, 29);

                        }
                    }
                }
                guiGraphics.pose().popPose();

                if (font != null) {
                    guiGraphics.drawString(font, title, BREWINGSTAND_I + BREWINGSTAND_X, BREWINGSTAND_J + 6, 0, false);
                }
                if (font != null) {
                    if (inventoryTitle != null) {
                        guiGraphics.drawString(font, inventoryTitle, BREWINGSTAND_I + 8, BREWINGSTAND_J + BREWINGSTANDHEIGHT - 94, 0, false);
                    }
                }
            }
        }


        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> furnacescreen) {
            AbstractContainerMenu menu = furnacescreen.getMenu();
            if (menu instanceof AbstractFurnaceMenu furnaceMenu &&
                    furnacescreen instanceof AbstractFurnaceScreenAccessor accessor) {

                ResourceLocation flameSprite = accessor.getLitProgressSprite();
                ResourceLocation arrowSprite = accessor.getBurnProgressSprite();

                if (furnaceMenu.isLit()) {
                    int l = Mth.ceil(furnaceMenu.getLitProgress() * 13.0F) + 1;
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(0, 0, 10);
                    guiGraphics.blitSprite(RenderType::guiTextured, flameSprite, 14, 14, 0, 14 - l, FURNACE_I + 56, FURNACE_J + 36 + 14 - l, 14, l);
                }

                int j1 = Mth.ceil(furnaceMenu.getBurnProgress() * 24.0F);
                guiGraphics.blitSprite(RenderType::guiTextured, arrowSprite, 24, 16, 0, 0, FURNACE_I + 79, FURNACE_J + 34, j1, 16);
                guiGraphics.pose().popPose();
            }
        }

        // 直接访问字段

        int leftPos = 0;
        int topPos = 0;
        Slot hoveredSlot = null;
        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen) {
            if (screen != null) {
                hoveredSlot = ((AbstractContainerScreenAccessor) screen).getHoveredSlot();
                AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) screen;
                leftPos = accessor.getLeftPos();
                topPos = accessor.getTopPos();
            }
        }

        if (hoveredSlot != null && hoveredSlot.isHighlightable()) {
            int x = hoveredSlot.x - 4;
            int y = hoveredSlot.y - 4;
            if (RESET_SLOT_HIGHLIGHT_FRONT_SPRITE != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, 1000); // 提高 Z 值（更靠近屏幕）
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_SLOT_HIGHLIGHT_FRONT_SPRITE,
                        leftPos + x, topPos + y,
                        0, 0, // 纹理坐标
                        24, 24, 24, 24
                );
                guiGraphics.pose().popPose();

            }

            if (RESET_SLOT_HIGHLIGHT_BACK_SPRITE != null) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0, 0, -1000); // 提高 Z 值（更靠近屏幕）
                guiGraphics.blit(
                        RenderType::guiTextured,
                        RESET_SLOT_HIGHLIGHT_BACK_SPRITE,
                        leftPos + x, topPos + y,
                        0, 0, // 纹理坐标
                        24, 24, 24, 24
                );
                guiGraphics.pose().popPose();

            }
        }

    }




    // 手动检测矩形悬停
    private static boolean CraftingMouseOverRect(double mouseX, double mouseY) {
        return mouseX >= CRAFTING_I + 5
            && mouseX <= CRAFTING_I + 25
            && mouseY >= BUTTON_Y
            && mouseY <= BUTTON_Y + 18;
    }

    private static boolean FurnaceMouseOverRect(double mouseX, double mouseY) {
        Minecraft mc = Minecraft.getInstance();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        return mouseX >= FURNACE_I + 20
                && mouseX <= FURNACE_I + 40
                && mouseY >= (double) screenHeight / 2 - 49
                && mouseY <= (double) screenHeight / 2 - 49 + 18;
    }
    static boolean ShulkerBox = false;
    static boolean Grindstone = false;
    static boolean Hopper = false;
    static boolean AllFurnace = false;
    static boolean Furnace = false;
    static boolean Blast_Furnace = false;
    static boolean Smoker = false;

    static boolean openCreativeMode = false;
    static boolean FirstOpenCreativeMode = false;

    @SubscribeEvent
    public static void ScreenOpen(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof ShulkerBoxScreen) {
            ShulkerBox = true;
        }
        if (event.getScreen() instanceof GrindstoneScreen) {
            Grindstone = true;
        }
        if (event.getScreen() instanceof HopperScreen) {
            Hopper = true;
        }
        if (event.getScreen() instanceof AbstractFurnaceScreen<?>) {
            AllFurnace = true;
        }

        if (event.getScreen() instanceof AbstractFurnaceScreen) {
           if (event.getScreen() instanceof FurnaceScreen) {
              Furnace = true;
           }
           if (event.getScreen() instanceof BlastFurnaceScreen) {
              Blast_Furnace = true;
           }
           if (event.getScreen() instanceof SmokerScreen) {
              Smoker= true;
           }
        }
    }

    @SubscribeEvent
    public static void ScreenClose(ScreenEvent.Closing event) {
        if (event.getScreen() instanceof ShulkerBoxScreen) {
            ShulkerBox = false;
        }
        if (event.getScreen() instanceof GrindstoneScreen) {
            Grindstone = false;
        }
        if (event.getScreen() instanceof HopperScreen) {
            Hopper = false;
        }
        if (event.getScreen() instanceof AbstractFurnaceScreen<?>) {
            AllFurnace = false;
        }

        if (event.getScreen() instanceof AbstractFurnaceScreen) {
            if (event.getScreen() instanceof FurnaceScreen) {
                Furnace = false;
            }
            if (event.getScreen() instanceof BlastFurnaceScreen) {
                Blast_Furnace = false;
            }
            if (event.getScreen() instanceof SmokerScreen) {
                Smoker = false;
            }
        }
    }


    @SubscribeEvent
    public static void CreativeInventoryOpen(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof CreativeModeInventoryScreen) {
            // 玩家打开了创造模式物品栏
            openCreativeMode = true;
            FirstOpenCreativeMode = true;
        }
    }
    @SubscribeEvent
    public static void CreativeInventoryClose(ScreenEvent.Closing event) {
        if (event.getScreen() instanceof CreativeModeInventoryScreen) {
            openCreativeMode = false;
        }
    }

}
