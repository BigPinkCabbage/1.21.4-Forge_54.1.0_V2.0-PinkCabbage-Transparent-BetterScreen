package com.FuFu.CabbageJellyPack.LoggedInText;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//用静态方法处理程序（这是一个玩家进入游戏后给玩家发送消息的程序）
public class PlayerLoggedInHandler {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("§a欢迎你游玩本模组!\n§d如有问题和想法请按步骤查看作者联系方式:\n§6->先用ESC键打开设置栏\n->然后点击Mod\n->再点击模组名就能找到了\n可以输入命令/CabbageMod help 快捷查看模组功能\n§a祝您游玩愉快!"));
        }
        //ServerPlayer player;

    }
}