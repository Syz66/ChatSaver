package me.zircta.chatsaver.mixins;

import me.zircta.chatsaver.Main;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiChat.class)
public class GuiChatMixin {
    @Shadow public GuiTextField inputField;

    @Inject(method = "onGuiClosed", at = @At("HEAD"))
    public void saveMessage(CallbackInfo ci) {
        Main.text = inputField.getText();
    }

    @Inject(method = "initGui", at = @At("TAIL"))
    public void setMessage(CallbackInfo ci) {
        // it's about time I switch to kotlin
        if (inputField != null) {
            inputField.text = Main.text;
        }
    }
}
