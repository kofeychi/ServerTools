package dev.kofeychi.ServerTools.mixin;

import dev.kofeychi.ServerTools.common.screen.WindowManagerScreen;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MainMenuMixin {
    @Inject(at = @At(value = "HEAD"),method = "init",cancellable = true)
    private void init(CallbackInfo ci) {
        int l = self().height / 4 + 48;
        self().addDrawableChild(new ButtonWidget.Builder(
                Text.of("aaaa"),
                (btn)->{
                    MinecraftClient.getInstance().setScreen(new WindowManagerScreen(Text.literal("aaaa"),MinecraftClient.getInstance().currentScreen));
                }
        ).dimensions(0, self().height-(16*2),16, 16).build());
    }
    @Unique
    TitleScreen self(){
        return ((TitleScreen)(Object)this);
    }
}
