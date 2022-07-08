package gay.lemmaeof.resonancecannon.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
	@Shadow
	protected boolean dead;

	@Shadow
	private @Nullable DamageSource lastDamageSource;

	@Shadow
	private long lastDamageStamp;

	public MixinLivingEntity(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "die", at = @At("HEAD"))
	private void actuallyMarkLastDamageSourceOnDeath(DamageSource source, CallbackInfo info) {
		if (!this.isRemoved() && !this.dead) {
			this.lastDamageSource = source;
			this.lastDamageStamp = this.level.getGameTime();
		}
	}
}
