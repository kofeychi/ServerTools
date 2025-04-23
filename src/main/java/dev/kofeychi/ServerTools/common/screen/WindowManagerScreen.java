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

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Collections.sort(windows, new PrioritisedComparator());
        windows.forEach(window -> {
            render(context, mouseX, mouseY, delta);
        });
    }
}
