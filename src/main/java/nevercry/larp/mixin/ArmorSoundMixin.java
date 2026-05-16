package nevercry.larp.mixin;

import net.minecraft.component.type.EquippableComponent;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EquippableComponent.class)
public class ArmorSoundMixin {

    @Inject(method = "equipSound", at = @At("HEAD"), cancellable = true)
    private void changeSound(
            CallbackInfoReturnable<RegistryEntry<SoundEvent>> cir
    ) {

        cir.setReturnValue(
                SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA
        );
    }
}