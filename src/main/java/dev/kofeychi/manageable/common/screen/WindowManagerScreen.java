package dev.kofeychi.manageable.common.screen;

import dev.kofeychi.manageable.common.window.AbstractWindow;
import dev.kofeychi.manageable.common.window.DefaultWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.joml.Vector2i;

import java.util.*;

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
        this.windows.add(new Prioritised<>(new DefaultWindow(new Vector2i(50),new Vector2i(100),this)));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.mouseClicked(mouseX,mouseY,button,AbstractWindow);
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.mouseReleased(mouseX,mouseY,button,AbstractWindow);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.mouseDragged(mouseX,mouseY,button,deltaX,deltaY,AbstractWindow);
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.mouseScrolled(mouseX,mouseY,horizontalAmount,verticalAmount,AbstractWindow);
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.keyReleased(keyCode,scanCode,modifiers,AbstractWindow);
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        int i = 0;for(Iterator<Prioritised<AbstractWindow>> var2 = windows.iterator(); var2.hasNext(); i++) {
            AbstractWindow AbstractWindow = var2.next().value;
            AbstractWindow.charTyped(chr,modifiers,AbstractWindow);
        }
        return super.charTyped(chr, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        windows.forEach(window -> {
            window.value.render(context, mouseX, mouseY, delta);
        });
    }
    public boolean removeWindow(UUID id) {
        return windows.removeIf(w->w.value.uuid.equals(id));
    }
    public void removeWindow(int index) {
        windows.remove(index);
    }
    public void sortWindows() {
        Collections.sort(windows, new PrioritisedComparator());
    }
}
