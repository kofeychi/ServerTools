package dev.kofeychi.manageable.common.window;

import dev.kofeychi.manageable.common.modules.scheduler.Scheduler;
import dev.kofeychi.manageable.common.render.GuiTexture;
import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

public class ExitButton extends AbstractButton {
    public ExitButton(Vector2i pos, Vector2i scale) {
        super(pos, scale);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button, AbstractWindow window) {
        if(isHovered(mouseX, mouseY)) {
            Scheduler.CLIENT.add(new Scheduler.Schedule(2, window::removeSelf));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        boolean delhovered = true;
        if(isHovered(mouseX,mouseY)){
            delhovered = false;
        }
        GuiTexture.drawShiftable(context,pos.x+scale.x-9,pos.y,GuiTexture.GUI_DEL_BUTTON,delhovered ? new Vector2i(0,0) : new Vector2i(0,9));
    }
}
