package com.FuFu.CabbageJellyPack.Screen;

import com.google.gson.*;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;
import static com.FuFu.CabbageJellyPack.Event.StaticData.SettingScreenAlpha;
import static com.FuFu.CabbageJellyPack.key.Events.showCoordinates;
import static com.FuFu.CabbageJellyPack.key.Events.showDurability;

public class SettingsIO {

    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("PinkCabbage_settings.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static SettingData data = new SettingData();

    public static class SettingData {
        public float settingScreenAlpha;      // 背景遮罩透明度 (0~255)

        public float inventoryAlpha;       // 背景透明度 (0.0 ~ 1.0)
        public boolean ShowDurability;
        public boolean showCoordinate;
        // 可继续添加更多设置项
    }

    // ✅ 读取配置文件
    public static void load() {

        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH); // 读取 JSON 字符串
                data = GSON.fromJson(json, SettingData.class); // 转换为对象
                SettingScreenAlpha = data.settingScreenAlpha;
                InventoryAlpha = data.inventoryAlpha;
                showCoordinates = data.showCoordinate;
                showDurability = data.ShowDurability;
            } catch (IOException e) {
                System.err.println("读取配置文件失败: " + e.getMessage());
            }
        }
    }

    // ✅ 保存配置文件
    public static void save() {
        data.settingScreenAlpha = SettingScreenAlpha;
        data.inventoryAlpha = InventoryAlpha;
        data.showCoordinate = showCoordinates;
        data.ShowDurability = showDurability;
        try {
            String json = GSON.toJson(data); // 转换为 JSON 字符串
            Files.writeString(CONFIG_PATH, json); // 写入文件
        } catch (IOException e) {
            System.err.println("保存配置文件失败: " + e.getMessage());
        }
    }
}