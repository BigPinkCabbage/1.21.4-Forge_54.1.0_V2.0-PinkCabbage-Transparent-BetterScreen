package com.FuFu.CabbageJellyPack.Event;
/*
import net.minecraft.client.Minecraft;

//方块坐标
import net.minecraft.core.BlockPos;

//导入粒子类型
import net.minecraft.core.particles.ParticleTypes;

//访问玩家实体对象
import net.minecraft.world.entity.player.Player;

//检测流体（比如水）
import net.minecraft.world.level.material.Fluids;

//三维向量类，表示坐标或者方向
import net.minecraft.world.phys.Vec3;

//tick事件
import net.minecraftforge.event.TickEvent;

//注解
import net.minecraftforge.eventbus.api.SubscribeEvent;
*/

public class UnderwaterBubblesHandler {
    /*
    private static int cooldown = 0;

    //记录玩家上一次位置
    private static Vec3 lastPos = Vec3.ZERO;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        //获取客户端实例
        Minecraft mc = Minecraft.getInstance();

        //如果tick阶段不结束或者玩家和世界没加载直接返回
        if (event.phase != TickEvent.Phase.END || mc.player == null || mc.level == null)
            return;

        //获取玩家对象
        Player player = mc.player;

        // 获取玩家方块坐标
        BlockPos footPos = player.blockPosition();

        //判断是否是水
        boolean inWater = mc.level.getFluidState(footPos).getType() == Fluids.WATER;

        //如果玩家不在水里，重置并且退出
        if (!inWater) {
            cooldown = 0;//计数器重置
            lastPos = player.position();//更新玩家的位置
            return;
        }

        // 获取玩家三维浮点坐标
        Vec3 currentPos = player.position();

        //获取玩家坐标与以前坐标是否一样
        boolean isMoving = currentPos.distanceToSqr(lastPos) > 0.0004; // sqrt(0.0004) = 0.02 block

        //更新坐标
        lastPos = currentPos;

        //如果不移动，退出
        if (!isMoving) {
            cooldown = 0;
            return;
        }

        //如果冷却，返回
        if (cooldown > 0) {
            cooldown--;
            return;
        }

        //设置冷却为5tick
        cooldown = 5; // 控制粒子频率

        //使得粒子位置在玩家腿部
        double x = currentPos.x;
        double y = currentPos.y + 0.2; // 腿部稍上方
        double z = currentPos.z;

        //生成两个粒子
        for (int i = 0; i < 2; i++) {
            //添加一点随机偏移，让气泡更自然
            double offsetX = (mc.level.random.nextDouble() - 0.5) * 0.4;
            double offsetZ = (mc.level.random.nextDouble() - 0.5) * 0.4;

            //调用客户端的世界添加粒子效果
            mc.level.addParticle(ParticleTypes.BUBBLE,
                    x + offsetX, y, z + offsetZ,
                    0, 0.05, 0);
        }
    }

     */
}
