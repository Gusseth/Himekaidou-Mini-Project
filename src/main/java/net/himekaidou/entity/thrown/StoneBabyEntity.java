package net.himekaidou.entity.thrown;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.himekaidou.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class StoneBabyEntity extends ThrownItemEntity
{
    public StoneBabyEntity(EntityType<? extends ThrownItemEntity> type, World world) {
        super(ItemRegistry.stone_Baby_Entity, world);
        this.setItem(new ItemStack(ItemRegistry.stone_Baby));
    }

    public StoneBabyEntity(LivingEntity owner, World world)
    {
        super(ItemRegistry.stone_Baby_Entity, owner, world);
    }

    @Override
    protected Item getDefaultItem()
    {
        return ItemRegistry.stone_Baby;
    }


    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters()
    {
        ItemStack itemStack = new ItemStack(ItemRegistry.stone_Baby);
        return new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        if ((hitResult.getType() == HitResult.Type.ENTITY) && (((EntityHitResult)hitResult).getEntity() instanceof LivingEntity))
        {
            LivingEntity entity = (LivingEntity) ((EntityHitResult)hitResult).getEntity();
            entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 3);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3 * 20, 10));
            if (entity.isSubmergedInWater() || entity.isTouchingWater()) {
                entity.addVelocity(0, -2f, 0);
                entity.setAir(0);
                entity.setSwimming(false);
                entity.setJumping(false);
                entity.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1f, 0.75f);
                entity.playSound(SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, .25f, 0.5f);
            }

            if (entity instanceof PlayerEntity)
            {
                PlayerEntity victim = (PlayerEntity) entity;
                Inventory inventory = victim.inventory;
                victim.dropSelectedItem(true);
            }
            entity.setStackInHand(entity.getActiveHand(), getItem());
        }

        if (!this.world.isClient)
        {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemStack getStack()
    {
        return new ItemStack(this.getDefaultItem());
    }


}
