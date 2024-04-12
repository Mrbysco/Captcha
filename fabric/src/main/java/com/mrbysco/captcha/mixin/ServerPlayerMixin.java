package com.mrbysco.captcha.mixin;

import com.mrbysco.captcha.callback.PlayerTickCallback;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

	@Inject(at = @At("RETURN"), method = "tick()V")
	public void captcha$tick(CallbackInfo ci) {
		PlayerTickCallback.EVENT.invoker().serverTick((ServerPlayer) (Object) this);
	}
}