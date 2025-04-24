package dev.kofeychi.manageable.common.window;

import dev.kofeychi.manageable.common.screen.WindowManagerScreen;
import dev.kofeychi.manageable.common.util.VectorUtils;
import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.UUID;

public class AbstractWindow {
    public Vector2i pos = new Vector2i(50,50);
    public Vector2i scale = new Vector2i(50,50);
    public UUID uuid = UUID.randomUUID();
    public ArrayList<AbstractButton> buttons = new ArrayList<>();
    public WindowManagerScreen windowManagerScreen;

    public AbstractWindow(Vector2i pos, Vector2i scale, WindowManagerScreen windowManagerScreen) {
        this.pos = pos;
        this.scale = scale;
        this.windowManagerScreen = windowManagerScreen;
    }
    public AbstractWindow(){}
    public void render(DrawContext context, int mouseX, int mouseY, float delta){
        buttons.forEach(button -> {
            button.render(context, mouseX, mouseY, delta);
        });
    }
    public void mouseClicked(double mouseX, double mouseY, int button, AbstractWindow window){
        buttons.forEach(b->{
            b.mouseClicked(mouseX,mouseY,button,this);
        });
    }
    public boolean isOverWindow(double mouseX, double mouseY){
        return VectorUtils.isOver(mouseX,mouseY,pos.x,pos.y,scale.x,scale.y);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button,AbstractWindow window) {
        buttons.forEach(b->{
            b.mouseReleased(mouseX,mouseY,button,this);
        });
        return true;
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY,AbstractWindow window) {
        buttons.forEach(b->{
            b.mouseDragged(mouseX,mouseY,button,deltaX,deltaY,this);
        });
        return true;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount,AbstractWindow window) {
        buttons.forEach(b->{
            b.mouseScrolled(mouseX,mouseY,horizontalAmount,verticalAmount,this);
        });
        return true;
    }

    public boolean keyReleased(int keyCode, int scanCode, int modifiers,AbstractWindow window) {
        return true;
    }

    public boolean charTyped(char chr, int modifiers,AbstractWindow window) {
        return true;
    }
    public boolean removeButton(UUID id) {
        return buttons.removeIf(w->w.uuid.equals(id));
    }
    public void removeButton(int index) {
        buttons.remove(index);
    }
    public void removeSelf(){
        this.windowManagerScreen.removeWindow(this.uuid);
    }
}
