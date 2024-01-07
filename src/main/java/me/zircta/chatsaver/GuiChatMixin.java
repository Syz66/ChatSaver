package me.zircta.chatsaver;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiChat.class)
public class GuiChatMixin {
    @Unique private static String chatSaver$text = "";
    @Shadow public GuiTextField inputField;

    @Inject(method = "onGuiClosed", at = @At("HEAD"))
    public void saveMessage(CallbackInfo ci) {
        chatSaver$text = inputField.getText();
    }

    @Inject(method = "initGui", at = @At("TAIL"))
    public void setMessage(CallbackInfo ci) {
        // it's about time I switch to kotlin
        if (inputField != null) {
            inputField.text = chatSaver$text;
        }
    }

    @Inject(method = "keyTyped", at = @At("TAIL"))
    public void clearMessage(char c, int i, CallbackInfo ci) {
        if (i == 28 || i == 156) {
            chatSaver$text = "";
        }
    }
}
