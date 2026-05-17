package nevercry.larp.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public class tooltip {

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void customName(CallbackInfoReturnable<Text> cir) {
        ItemStack self = (ItemStack)(Object)this;
        if (!self.isOf(Items.COPPER_CHESTPLATE)) return;
        cir.setReturnValue(
                Text.literal("Elytra")
                        .formatted(Formatting.LIGHT_PURPLE)
        );
    }

    @Inject(method = "getTooltip", at = @At("RETURN"), cancellable = true)
    private void customTooltip(
            Item.TooltipContext context,
            PlayerEntity player,
            TooltipType type,
            CallbackInfoReturnable<List<Text>> cir
    ) {
        ItemStack self = (ItemStack)(Object)this;
        if (!self.isOf(Items.COPPER_CHESTPLATE)) return;

        List<Text> tooltip = new ArrayList<>();
        tooltip.add(
                Text.literal("Elytra")
                        .formatted(Formatting.LIGHT_PURPLE)
        );
        tooltip.add(Text.literal("Unbreaking III")
                .formatted(Formatting.GRAY));
        tooltip.add(Text.literal("Mending")
                .formatted(Formatting.GRAY));
        tooltip.add(
                Text.literal("Worth: ")
                        .formatted(Formatting.GRAY)
                        .append(
                                Text.literal("$33.88K")
                                        .styled(style -> style.withColor(0x00fc00))
                        )
        );

        cir.setReturnValue(tooltip);
    }

    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void alwaysGlint(CallbackInfoReturnable<Boolean> cir) {
        ItemStack self = (ItemStack)(Object)this;
        if (self.isOf(Items.COPPER_CHESTPLATE)) {
            cir.setReturnValue(true);
        }
    }
}
