package dev.kofeychi.ServerTools.common.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RenderAbleWidget extends ClickableWidget {
    public Identifier defTexture;
    public Identifier HoverTexture;

    public RenderAbleWidget(int x, int y, int width, int height, Identifier defTexture) {
        super(x, y, width, height, Text.empty());
        this.defTexture = defTexture;
        HoverTexture = Identifier.of(defTexture.getNamespace(), defTexture.getPath()+"_hover");
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        // We'll just draw a simple rectangle for now.
        // x1, y1, x2, y2, startColor, endColor
        if(isHovered()) {
            context.drawGuiTexture(HoverTexture,this.getX(), this.getY(), 16,16);
        } else {
            context.drawGuiTexture(defTexture,this.getX(), this.getY(), 16,16);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }

}
