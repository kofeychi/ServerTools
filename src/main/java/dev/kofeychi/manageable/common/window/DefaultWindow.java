package dev.kofeychi.manageable.common.window;

import dev.kofeychi.manageable.common.render.WindowFrameRenderer;
import dev.kofeychi.manageable.common.screen.WindowManagerScreen;
import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

public class DefaultWindow extends AbstractWindow{
    public DefaultWindow(Vector2i pos, Vector2i scale,WindowManagerScreen wms) {
        super(pos,scale,wms);
        this.buttons.add(new ExitButton(
                new Vector2i(pos.x+scale.x-3-9, pos.y+3),
                new Vector2i(9)
        ));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        WindowFrameRenderer.draw(context,pos.x, pos.y, scale.x,scale.y,3);
        buttons.forEach(button -> {
            button.render(context, mouseX, mouseY, delta);
        });
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, AbstractWindow window) {

        return true;
    }
}
