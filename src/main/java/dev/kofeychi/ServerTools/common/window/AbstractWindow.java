package dev.kofeychi.ServerTools.common.window;

import dev.kofeychi.ServerTools.common.modules.scheduler.Scheduler;
import dev.kofeychi.ServerTools.common.render.WindowFrameRenderer;
import dev.kofeychi.ServerTools.common.screen.Prioritised;
import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.UUID;

public class AbstractWindow {
    public Vector2i pos = new Vector2i(50,50);
    public Vector2i scale = new Vector2i(50,50);
    public UUID uuid = UUID.randomUUID();

    public void render(DrawContext context, int mouseX, int mouseY, float delta){
        WindowFrameRenderer.draw(context,pos.x,pos.y,scale.x,scale.y,3,new Vector2i(mouseX,mouseY));
    }
    public void mouseClicked(double mouseX, double mouseY, int button, ArrayList<Prioritised<AbstractWindow>> list){
        if(button == GLFW.GLFW_MOUSE_BUTTON_LEFT){
            if(WindowFrameRenderer.isHovered(mouseX,mouseY,pos.x+scale.x-3-9, pos.y+3,9,9)){
                Scheduler.CLIENT.add(new Scheduler.Schedule(5,()->{
                    list.removeIf(w->w.value.uuid.equals(uuid));
                    return null;
                }));
            }
        }
    }
}
