package com.FuFu.CabbageJellyPack.Event;

import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StaticData {
    public static int currentleftPos = -1;
    public static int currenttopPos = -1;
    public static int currentimageHeight = -1;
    public static int currentimageWidth = -1;
    public static float InventoryAlpha = 0.5f;
    public static float SettingScreenAlpha = 1.0f;
    public static boolean cabbagepinkpack1_0_1$hasRenderedHotbarOnce = false;
    public static int SHULKERBOX_I;
    public static int SHULKERBOX_J;
    public static int SHULKERBOXHEIGHT;
    public static int SHULKERBOXWIDTH;

    public static int GRINDSTONE_I;
    public static int GRINDSTONE_J;
    public static int GRINDSTONEHEIGHT;
    public static int GRINDSTONEWIDTH;

    public static int HOPPER_I;
    public static int HOPPER_J;
    public static int HOPPERHEIGHT;
    public static int HOPPERWIDTH;

    public static int CHEST_I;
    public static int CHEST_J;
    public static int CHESTHEIGHT;
    public static int CHESTWIDTH;
    public static int CONTAINERROWS;

    public static int CRAFTING_I;
    public static int CRAFTING_J;
    public static int CRAFTINGHEIGHT;
    public static int CRAFTINGWIDTH;

    public static int CRAFTERHEIGHT;
    public static int CRAFTERWIDTH;
    public static int CRAFTER_I;
    public static int CRAFTER_J;

    public static int CRAFTERTITLE_X;

    public static int REDSTONE_I;
    public static int REDSTONE_J;

    public static int BUTTON_Y;

    public static int FURNACE_I;
    public static int FURNACE_J;

    public static int FURNACE_LabelX;
    public static int FURNACEHEIGHT;

    public static int DISPENSER_I;
    public static int DISPENSER_J;
    public static int DISPENSERHEIGHT;
    public static int DISPENSERWIDTH;
    public static int DISPENSERTITLE_X;

    public static int BREWINGSTAND_I;
    public static int BREWINGSTAND_J;
    public static int BREWINGSTAND_L;
    public static int BREWINGSTAND_J1;
    public static int BREWINGSTANDHEIGHT;
    public static int BREWINGSTANDWIDTH;
    public static int BREWINGSTAND_X;
    public static int BREWINGSTAND_I1;


    public static boolean HasPressed = false; // 控制只触发一次
    public static boolean POWERED = false;
    @SubscribeEvent
    public static void cabbagepinkpack1_0_1$onPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event) {
        cabbagepinkpack1_0_1$hasRenderedHotbarOnce = false;
    }

}
