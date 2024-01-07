package me.zircta.chatsaver.mixins;

import me.zircta.chatsaver.Main;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayerSP.class)
public class EntityPlayerSPMixin {
    @Inject(method = "sendChatMessage", at = @At("TAIL"))
    public void onSendMessage(String msg, CallbackInfo ci) {
        if (msg.equals(Main.text)) {
            Main.text = "";
        }
    }
}
