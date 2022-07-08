package gay.lemmaeof.resonancecannon.mixin;

import gay.lemmaeof.resonancecannon.ResonanceCannon;
import gay.lemmaeof.resonancecannon.ResonanceCannonItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkCatalystBlockEntity.class)
public class MixinSculkCatalystBlockEntity {

	@Inject(method = "handleGameEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkSpreader;addCursors(Lnet/minecraft/core/BlockPos;I)V"))
	private void spawnEchoShards(ServerLevel world, GameEvent.Message message, CallbackInfoReturnable<Boolean> info) {
		Entity source = message.context().sourceEntity();
		if (source instanceof LivingEntity living) {
			DamageSource damage = living.getLastDamageSource();
			if (damage instanceof EntityDamageSource entityDamage
					&& entityDamage.msgId.equals("sonic_boom")
					&& entityDamage.getEntity() instanceof Player) {
				ItemStack stack = new ItemStack(Items.ECHO_SHARD, world.getRandom().nextInt(3) + 1);
				ItemEntity entity = new ItemEntity(world, living.getX(), living.getY(), living.getZ(), stack);
				world.addFreshEntity(entity);
			}
		}
	}
}
