package com.FuFu.CabbageJellyPack.LoggedInText;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import static com.FuFu.CabbageJellyPack.Event.StaticData.InventoryAlpha;
import static com.FuFu.CabbageJellyPack.Event.StaticData.SettingScreenAlpha;

public class CabbageModHelp {
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("CabbageMod")
                .requires(source -> source.hasPermission(0)) // 只有OP等级≥0可以使用
                .then(Commands.argument("help", StringArgumentType.word())
                        .executes(context -> {
                            context.getSource().sendSuccess(
                                    () -> Component.literal("打开菜单默认按键是J\n模组功能都在菜单里面\n修改物品栏透明度命令为/CabbageMod InventoryAlpha <0.0~1.0>\n修改菜单透明度命令为/CabbageMod SettingScreenAlpha <0.1~1.0>\n详情请按Esc打开Mod查看模组简介"), false
                            );
                            return 1;
                        })));

        dispatcher.register(
                Commands.literal("CabbageMod")
                        .then(Commands.literal("InventoryAlpha")
                                .then(Commands.argument("value", FloatArgumentType.floatArg(0.0f, 1.0f))

                                        .executes(InventoryContext -> {
                                            // ✅ 从 context 中正确获取参数 value
                                            float value = FloatArgumentType.getFloat(InventoryContext, "value");
                                            InventoryAlpha = value;

                                            InventoryContext.getSource().sendSuccess(() ->
                                                    Component.literal("InventoryAlpha set to: " + value), true);
                                            return 1;
                                        })
                                )
                        )
        );

        dispatcher.register(
                Commands.literal("CabbageMod")
                        .then(Commands.literal("SettingScreenAlpha")
                                .then(Commands.argument("value", FloatArgumentType.floatArg(0.1f, 1.0f))

                                        .executes(SettingScreenContext -> {
                                            // ✅ 从 context 中正确获取参数 value
                                            float value = FloatArgumentType.getFloat(SettingScreenContext, "value");
                                            SettingScreenAlpha = value;

                                            SettingScreenContext.getSource().sendSuccess(() ->
                                                    Component.literal("SettingScreenAlpha set to: " + value), true);
                                            return 1;
                                        })
                                )
                        )
        );
    }
}
