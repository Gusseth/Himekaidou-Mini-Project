package net.himekaidou.item;

import net.himekaidou.util.TooltipBuilder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RoukankenItem extends SwordItem
{
    public int   ATTACK_DAMAGE     = 12;    // Please add the config values here soon
    public float ATTACK_SPEED      = -1.2f; // Please add the config values here soon
    public int   SPEED_DURATION    = 3;
    public int   STRENGTH_DURATION = 2;

    public RoukankenItem() {
        super(new RoukankenMaterial(), 12, -1.2f, new Settings().group(ItemGroup.COMBAT));

        // Right click event registration
        /*
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            if (player.getMainHandStack().getItem() instanceof Roukanken) {
                onRightClick(player, world, hand);
                return ActionResult.PASS;
            }
            return ActionResult.FAIL;
        });
         */
    }

    /*
    Right click event, put code below if you want it to trigger when this item is used
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        if (player.onGround)
        {
            // Dashing
            player.addVelocity(-Math.sin(Math.toRadians(player.getHeadYaw())), .5f, Math.cos(Math.toRadians(player.getHeadYaw())));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, SPEED_DURATION * 20));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, STRENGTH_DURATION * 20));
        }
        return super.use(world, player, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip = TooltipBuilder.BuildTooltip(tooltip, "roukanken", 1, 1);
    }



}
