package com.FuFu.CabbageJellyPack;

import com.FuFu.CabbageJellyPack.Event.StaticData;
import com.FuFu.CabbageJellyPack.GuiText.RenderLossUI;
import com.FuFu.CabbageJellyPack.LoggedInText.CabbageModHelp;
import com.FuFu.CabbageJellyPack.LoggedInText.PlayerLoggedInHandler;
import com.FuFu.CabbageJellyPack.Event.UnderwaterBubblesHandler;
import com.FuFu.CabbageJellyPack.Screen.ClientForgeEvents;
import com.FuFu.CabbageJellyPack.Screen.SettingsIO;
import com.FuFu.CabbageJellyPack.key.Events;
import com.FuFu.CabbageJellyPack.key.KeyBindings;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CabbageJellyPack.MODID)
public class CabbageJellyPack {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cabbagejellypack";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public CabbageJellyPack(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::onRegisterKeyMappings);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        //将事件处理程序的类注册到forge线
        MinecraftForge.EVENT_BUS.register(PlayerLoggedInHandler.class);
        MinecraftForge.EVENT_BUS.register(UnderwaterBubblesHandler.class);
        MinecraftForge.EVENT_BUS.register(Events.class);
        MinecraftForge.EVENT_BUS.register(ClientForgeEvents.class);
        //MinecraftForge.EVENT_BUS.register(CabbageModHelp.class);
        MinecraftForge.EVENT_BUS.addListener(CabbageModHelp::onRegisterCommands);
        MinecraftForge.EVENT_BUS.addListener(RenderLossUI::CreativeInventoryOpen);
        MinecraftForge.EVENT_BUS.addListener(RenderLossUI::CreativeInventoryClose);
        MinecraftForge.EVENT_BUS.addListener(RenderLossUI::ScreenOpen);
        MinecraftForge.EVENT_BUS.addListener(RenderLossUI::ScreenClose);
        MinecraftForge.EVENT_BUS.addListener(RenderLossUI::onScreenRender);
        MinecraftForge.EVENT_BUS.addListener(StaticData::cabbagepinkpack1_0_1$onPlayerLogin);
        MinecraftForge.EVENT_BUS.addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldUnload);
    }

    public void onWorldUnload(net.minecraftforge.event.level.LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            System.out.println("世界关闭，保存配置...");
            SettingsIO.save(); // ✅ 保存配置
        }
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        SettingsIO.load(); // ✅ 游戏启动时加载设置
    }

    public void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.OPEN_COORDS);
        event.register(KeyBindings.OPEN_SETTINGS);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
