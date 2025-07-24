package com.FuFu.CabbageJellyPack.Screen;

import com.FuFu.CabbageJellyPack.key.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientForgeEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == KeyBindings.OPEN_SETTINGS.getKey().getValue() && event.getAction() == 1) {
            Minecraft mc = Minecraft.getInstance();
            /*
            if(!HasPressed) {
                SettingsIO.load();
            }
            HasPressed = true;
            */
            if (mc.screen instanceof MainSettingScreen) {
                mc.setScreen(null); // 如果已经打开，关闭它
            } else {
                mc.setScreen(new MainSettingScreen()); // 否则打开
            }
        }
    }


}
