package dev.kofeychi.manageable.mixin;

import dev.kofeychi.manageable.ManageableMain;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(SpriteAtlasTexture.class)
public class RMixin {
    @Shadow private Map<Identifier, Sprite> sprites;

    @Inject(at = @At(value = "HEAD"),method = "tick",cancellable = true)
    public void tick(CallbackInfo ci) {
        ManageableMain.LOGGER.info(this.sprites.toString());
    }
    @Unique
    SpriteAtlasTexture self(){
        return ((SpriteAtlasTexture)(Object)this);
    }
}
