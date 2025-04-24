package dev.kofeychi.manageable.common.window;

import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

import java.util.UUID;

public class AbstractButton {
    public Vector2i pos = new Vector2i(50,50);
    public Vector2i scale = new Vector2i(9,9);
    public UUID uuid = UUID.randomUUID();

    public AbstractButton(Vector2i pos, Vector2i scale) {
        this.pos = pos;
        this.scale = scale;
    }
    public AbstractButton() {}

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= pos.x && mouseY >= pos.y && mouseX < pos.x + scale.x && mouseY < pos.y + scale.y;
    }
    public void mouseClicked(double mouseX, double mouseY, int button, AbstractWindow window) {
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    }
    public void mouseReleased(double mouseX, double mouseY, int button,AbstractWindow window) {
    }
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY,AbstractWindow window) {
    }
    public void mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount,AbstractWindow window) {
    }
}
