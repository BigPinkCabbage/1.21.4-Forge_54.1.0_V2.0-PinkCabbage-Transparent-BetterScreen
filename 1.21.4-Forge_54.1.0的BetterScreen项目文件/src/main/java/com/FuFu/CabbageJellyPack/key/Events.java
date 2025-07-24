package com.FuFu.CabbageJellyPack.key;

import com.FuFu.CabbageJellyPack.Screen.SettingsIO;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;

public class Events {
    // 控制坐标显示状态
    public static boolean showCoordinates = false;
    public static boolean showDurability = false;
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == KeyBindings.OPEN_COORDS.getKey().getValue() && event.getAction() == 1) {
            // 按下O键，切换显示状态
            showCoordinates = !showCoordinates;
        }

        /*
        if (event.getKey() == KeyBindings.OPEN_SETTINGS.getKey().getValue() && event.getAction() == 1) {
            // 按下J键，切换显示状态
            SettingsIO.load();
        }
        */
    }


}