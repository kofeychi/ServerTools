package dev.kofeychi.ServerTools.common.screen;

import dev.kofeychi.ServerTools.common.window.AbstractWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WindowManagerScreen extends Screen {
    public Screen parent;
    public ArrayList<Prioritised<AbstractWindow>> windows = new ArrayList<>();
    public WindowManagerScreen(Text title,Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of("return"), (btn) -> {
            MinecraftClient.getInstance().setScreen(parent);
        }).dimensions(0, this.height-16, 16, 16).build();

        this.addDrawableChild(buttonWidget);
        windows.add(new Prioritised<>(new AbstractWindow() {}));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        windows.forEach(window -> {
            window.value.mouseClicked(mouseX,mouseY,button,windows);
        });
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return super.charTyped(chr, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Collections.sort(windows, new PrioritisedComparator());
        windows.forEach(window -> {
            window.value.render(context, mouseX, mouseY, delta);
        });
    }
}
