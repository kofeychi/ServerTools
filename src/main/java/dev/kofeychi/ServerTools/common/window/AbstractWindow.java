package dev.kofeychi.ServerTools.common.window;

import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2f;

public class AbstractWindow {
    public Vector2f pos = new Vector2f();
    public Vector2f scale = new Vector2f();

    public void render(DrawContext context, int mouseX, int mouseY, float delta){
    }
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
    }
}
