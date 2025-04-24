package dev.kofeychi.manageable.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRenderer.class)
public class DebugRendererMixin {
    @Inject(at = @At(value = "HEAD"),method = "render",cancellable = true)
    public void Cart$render(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, CallbackInfo ci){
        //ServerToolsMain.shape.draw(5,ServerToolsMain.isInside,vertexConsumers.getBuffer(RenderLayer.LINES),new Vec3d(cameraX,cameraY,cameraZ),matrices, MinecraftClient.getInstance().gameRenderer.getCamera(),vertexConsumers);
    }
}
