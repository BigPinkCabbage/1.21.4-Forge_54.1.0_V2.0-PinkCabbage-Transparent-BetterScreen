package com.FuFu.CabbageJellyPack.Screen;

import com.FuFu.CabbageJellyPack.key.Events;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.FuFu.CabbageJellyPack.Event.StaticData.*;


public class MainSettingScreen extends Screen {

    private static final ResourceLocation Title = ResourceLocation.tryBuild("cabbagejellypack", "textures/hud/fufu.png");
    private AlphaSlider InventoryBgAlphaSlider;
    private BackgroundAlphaSlider SettingsBgAlphaSlider;
    private static EditBox InventoryBgAlphaInputBox;
    private static EditBox SettingsBgAlphaInputBox;


    protected MainSettingScreen() {
        super(Component.translatable("cabbagejellypack.settings.screen"));
    }

    private Component getDurabilityToggleText() {
        return Component.translatable(Events.showDurability ?
                "cabbagejellypack.settings.itemtip.open" :
                "cabbagejellypack.settings.itemtip.close");
    }

    private Component getPositionText() {
        return Component.translatable(Events.showCoordinates ?
                "cabbagejellypack.settings.position.open" :
                "cabbagejellypack.settings.position.close");
    }

    @Override
    protected void init() {
        Font font = Minecraft.getInstance().font;

        InventoryBgAlphaInputBox = new EditBox(font, this.width / 2 + 55, this.height / 2 - 30  , 33, 20, Component.literal(""));
        InventoryBgAlphaInputBox.setValue(String.valueOf(InventoryAlpha));
        InventoryBgAlphaInputBox.setResponder(text -> {
            try {
                float value = Float.parseFloat(text);
                if (value >= 0.0F && value <= 1.0F) {
                    InventoryAlpha = value;
                    if (InventoryBgAlphaInputBox != null) {
                        InventoryBgAlphaSlider.syncTo(InventoryAlpha); // ✅ 设置滑轮值并刷新显示
                    }
                }
            } catch (NumberFormatException ignored) {
                // 非数字输入，忽略
            }
        });
        this.addRenderableWidget(InventoryBgAlphaInputBox);

        SettingsBgAlphaInputBox = new EditBox(font, this.width / 2 + 55, this.height / 2, 33, 20, Component.literal(""));
        SettingsBgAlphaInputBox.setValue(String.valueOf(SettingScreenAlpha));
        SettingsBgAlphaInputBox.setResponder(text -> {
            try {
                float value = Float.parseFloat(text);
                if (value >= 0.1F && value <= 1.0F) {
                    SettingScreenAlpha = value;
                    if (SettingsBgAlphaInputBox!= null) {
                        SettingsBgAlphaSlider.syncTo(SettingScreenAlpha);
                    }
                }
            } catch (NumberFormatException ignored) {
                // 非法输入忽略
            }
        });
        this.addRenderableWidget(SettingsBgAlphaInputBox);

        this.addRenderableWidget(Button.builder(
                                Component.literal("×"),  // 按钮文本
                                btn -> Minecraft.getInstance().setScreen(null) // 点击关闭菜单
                        )
                        .pos(this.width / 2 - 130, this.height / 2 - 110) // 按钮位置（可根据已有按钮上下调整）
                        .size(12, 12)
                        .build()
        );

        this.InventoryBgAlphaSlider = new AlphaSlider(this.width / 2 - 88, this.height / 2 - 30, 120, 20 , InventoryBgAlphaInputBox);
        this.addRenderableWidget(this.InventoryBgAlphaSlider);

        this.SettingsBgAlphaSlider =new BackgroundAlphaSlider(this.width / 2 - 88, this.height / 2 , 120, 20, SettingsBgAlphaInputBox);
        this.addRenderableWidget(this.SettingsBgAlphaSlider);


        // 新增 Toggle 按钮
        this.addRenderableWidget(Button.builder(
                        getDurabilityToggleText(), // 初始显示的文本
                        btn -> {
                            Events.showDurability = !Events.showDurability; // 切换值
                            btn.setMessage(getDurabilityToggleText());          // 更新按钮文本
                        })
                .pos(this.width / 2 - 88, this.height / 2 + 60) // 按钮位置
                .size(120, 20)
                .build()
        );
        this.addRenderableWidget(Button.builder(
                        getPositionText(), // 初始显示的文本
                        btn -> {
                            Events.showCoordinates = !Events.showCoordinates; // 切换值
                            btn.setMessage(getPositionText());          // 更新按钮文本
                        })
                .pos(this.width / 2 - 88, this.height / 2 + 30) // 按钮位置
                .size(120, 20)
                .build()
        );
    }



    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, SettingScreenAlpha);
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.pose().pushPose(); // 保存当前变换状态
        guiGraphics.pose().scale(2.0F, 2.0F, 1.0F); // X, Y 放大2倍
        guiGraphics.blit(
                RenderType::guiTextured,
                Title,
                (this.width / 2 - 65) / 2,
                (this.height / 2 - 95) / 2,
                0, 0, // 纹理坐标
                16, 16,
                16, 16 // 渲染宽高
        );
        guiGraphics.pose().popPose(); // 恢复变换状态


        // 第二行正常字体
        drawOutlinedText(guiGraphics,
                this.font,
                Component.translatable("cabbagejellypack.settings.screen").getString(),
                this.width / 2 - 25,
                this.height / 2 - 82,
                0xFFFFFF,
                false
        );

        /*
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.5F, 0.5F, 1.0F); // X, Y 放大2倍
        guiGraphics.blit(
                RenderType::guiTextured,
                Title,
                mouseX * 2, mouseY * 2,
                0, 0, // 纹理坐标
                16, 16,
                16, 16
        );
        guiGraphics.pose().popPose();
        */
    }
    /*
    // 手动检测矩形悬停
    private static boolean MouseOverRect(int mouseX, int mouseY) {
        return mouseX >= CRAFTING_I + 5
                && mouseX <= CRAFTING_I + 25
                && mouseY >= BUTTON_Y
                && mouseY <= BUTTON_Y + 18;
    }
    */

    public void drawOutlinedText(GuiGraphics guiGraphics, Font font, String text, int x, int y, int color, boolean outline) {
        /*
        int outlineColor = 0x000000; // 黑色
        // 上下左右描边
        guiGraphics.drawString(font, text, x - 1, y, outlineColor, false);
        guiGraphics.drawString(font, text, x + 1, y, outlineColor, false);
        guiGraphics.drawString(font, text, x, y - 1, outlineColor, false);
        guiGraphics.drawString(font, text, x, y + 1, outlineColor, false);
        // 中间正常文字
        */
        guiGraphics.drawString(font, text, x, y, color, false);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 绘制一个自定义透明度的黑色遮罩背景
        int color = (0) | 0x222222;
        guiGraphics.fill(0, 0, this.width, this.height, color);
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new MainSettingScreen());
    }


    private static class AlphaSlider extends AbstractSliderButton {
        public AlphaSlider(int x, int y, int width, int height,EditBox AlphaInputBox) {
            super(x, y, width, height, Component.literal("Alpha"), InventoryAlpha); // 初始值
            InventoryBgAlphaInputBox = AlphaInputBox;
            this.updateMessage();
        }

        @Override
        protected void updateMessage() {
            this.setMessage(Component.translatable("cabbagejellypack.settings.inventory_alpha"));
        }

        @Override
        protected void applyValue() {
            InventoryAlpha = (float) this.value;
            if (InventoryBgAlphaInputBox != null) {
                InventoryBgAlphaInputBox.setValue(String.format("%.2f", this.value));
            }
        }
        // ✅ 新增公开方法供外部调用
        public void syncTo(float newValue) {
            this.value = newValue;         // 修改内部 value
            this.updateMessage();          // 更新显示文本
        }
    }

    private static class BackgroundAlphaSlider extends AbstractSliderButton {
        private static final double MIN_VALUE = 0.1; // 最小值
        private static final double MAX_VALUE = 1.0; // 最大值

        public BackgroundAlphaSlider(int x, int y, int width, int height, EditBox AlphaInputBox) {
            super(x, y, width, height,
                    Component.literal("SettingsBg Alpha"),
                    // 将初始值 (SettingScreenAlpha) 映射到 [0,1] 范围
                    (SettingScreenAlpha - MIN_VALUE) / (MAX_VALUE - MIN_VALUE));
            SettingsBgAlphaInputBox = AlphaInputBox;
            this.updateMessage();
        }

        @Override
        protected void updateMessage() {
            this.setMessage(Component.translatable("cabbagejellypack.settings.bg_alpha"));
        }

        @Override
        protected void applyValue() {
            // 将滑块的 [0,1] 值映射回 [0.1,1] 范围
            SettingScreenAlpha = (float) (MIN_VALUE + (this.value * (MAX_VALUE - MIN_VALUE)));
            if (SettingsBgAlphaInputBox != null) {
                SettingsBgAlphaInputBox.setValue(String.format("%.2f", SettingScreenAlpha));
            }
        }

        public void syncTo(float newValue) {
            // 将外部值 [0.1,1] 映射到滑块的 [0,1] 范围
            this.value = (newValue - MIN_VALUE) / (MAX_VALUE - MIN_VALUE);
            this.updateMessage();
        }
    }
}
