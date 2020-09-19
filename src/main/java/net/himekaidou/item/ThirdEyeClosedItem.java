package net.himekaidou.item;

import net.himekaidou.util.TooltipBuilder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class ThirdEyeClosedItem extends Item
{
    public static int duration = 1;

    public ThirdEyeClosedItem(Settings settings) {
        super(settings);
        this.addPropertyGetter(new Identifier("koish_goes_krezy"), (stack, world, entity) ->
                entity != null && isActivated(stack) ? 1.0F : 0.0F);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getStackInHand(hand);
        setActivated(player, stack, !isActivated(stack));
        return super.use(world, player, hand);
    }

    public static void setActivated(PlayerEntity player, ItemStack stack, boolean activate)
    {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("koish_goes_krezy", activate);


        if (activate)
        {
            player.addChatMessage(new TranslatableText("item.himekaidou.third_eye_closed.activate"), false);
        }
        else
        {
            player.addChatMessage(new TranslatableText("item.himekaidou.third_eye_closed.deactivate"), true);
        }
    }

    public static boolean isActivated(ItemStack stack)
    {
        CompoundTag tag = stack.getOrCreateTag();
        return tag != null && tag.getBoolean("koish_goes_krezy");
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if (isActivated(stack))
        {
            LivingEntity lentity = (LivingEntity)entity;
            lentity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, duration * 20));
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip = TooltipBuilder.BuildTooltip(tooltip, "third_eye_closed", 0, 1);
    }
}
