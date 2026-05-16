package nevercry.larp.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ArmorMixin {

    @Inject(method = "getArmor", at = @At("HEAD"), cancellable = true)
    private void hideArmor(CallbackInfoReturnable<Integer> cir) {

        LivingEntity self = (LivingEntity)(Object)this;

        MinecraftClient client = MinecraftClient.getInstance();

        if (!(self instanceof ClientPlayerEntity player)) return;

        if (player != client.player) return;

        if (player.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.COPPER_CHESTPLATE)) {
            cir.setReturnValue(0);
        }
    }
}