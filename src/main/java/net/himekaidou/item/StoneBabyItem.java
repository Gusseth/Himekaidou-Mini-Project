package net.himekaidou.item;

import net.himekaidou.entity.thrown.StoneBabyEntity;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class StoneBabyItem extends Item
{
    public StoneBabyItem(Settings settings)
    {
        super(settings);
        this.addPropertyGetter(new Identifier("baby_yeeted"), (stack, world, entity) ->
                entity != null && isActivated(stack) ? 1.0F : 0.0F);
        this.addPropertyGetter(new Identifier("baby_owner"), ((stack, world, entity) ->
                entity instanceof PlayerEntity ? 1.0f : 0F));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);

        // Prevents item stack from decrementing when in creative mode
        if (!player.isCreative())
        {
            itemStack.decrement(1);
        }

        world.playSoundFromEntity(null, player, SoundEvents.ENTITY_WITCH_THROW, SoundCategory.NEUTRAL, 0.5f, 0.5f);

        // Spawns the thrown entity
        if (!world.isClient)
        {
            StoneBabyEntity baby = new StoneBabyEntity(player, world);
            setActivated(itemStack, true, player.getUuid());
            baby.setItem(itemStack);
            baby.setProperties(player, player.pitch, player.yaw, 0, 1f, 1f);
            world.spawnEntity(baby);
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }

    public void setActivated(ItemStack stack, boolean activate, UUID playerUUID)
    {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("baby_yeeted", activate);
        tag.putUuid("baby_owner", playerUUID);
    }

    public void setActivated(ItemStack stack, boolean activate)
    {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("baby_yeeted", activate);
    }

    public boolean isActivated(ItemStack stack)
    {
        CompoundTag tag = stack.getOrCreateTag();
        return tag != null && tag.getBoolean("baby_yeeted");
    }

    public boolean isOwner(ItemStack stack, UUID playerUUID)
    {
        CompoundTag tag = stack.getOrCreateTag();
        return tag != null && (tag.getUuid("baby_owner").compareTo(playerUUID) == 0);
    }

    private boolean isPlayerEntity(Entity entity)
    {
        return entity instanceof PlayerEntity;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if (isActivated(stack))
        {
            if (isOwner(stack, entity.getUuid()))
            {
                setActivated(stack, false);
            }
            else if (!((PlayerEntity)entity).isCreative())
            {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 5));
                livingEntity.setJumping(false);
                if (livingEntity.isSwimming()) livingEntity.setSwimming(false);
                if (entity.isSubmergedInWater() || entity.isTouchingWater()) {
                    entity.addVelocity(0, -1f / 20, 0);
                }
            }
        }

    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip = TooltipBuilder.BuildTooltip(tooltip, "stone_baby", 0, 1);
    }
}
