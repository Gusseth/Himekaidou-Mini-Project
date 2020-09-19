package net.himekaidou.mixin;

import net.himekaidou.client.render.entity.model.Abstract3DArmorModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> {
    @Shadow
    protected abstract void renderArmorParts(EquipmentSlot slot, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, boolean glint, A armorModel, boolean secondLayer, float red, float green, float blue, String suffix);

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;animateModel(Lnet/minecraft/entity/LivingEntity;FFF)V"
            ), method = "renderArmor"
    )
    private void redirectCustomAnimate(
            BipedEntityModel<T> armorModel,
            T entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            /* PARAMS FROM RENDER ARMOR*/
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            T entity$unused,
            float limbAngle$unused,
            float limbDistance$unused,
            float tickDelta$unused,
            float customAngle$unused,
            float headYaw$unused,
            float headPitch$unused,
            EquipmentSlot slot
    ) {
        ItemStack stack = entity.getEquippedStack(slot);

        if (stack.getItem() instanceof Abstract3DArmorModel) {
            ((Abstract3DArmorModel) stack.getItem()).animateModel(entity, limbAngle, limbDistance, tickDelta);
            return;
        }

        // Vanilla
        armorModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V"
            ), method = "renderArmor"
    )
    private void redirectCustomSetAngles(
            BipedEntityModel<T> armorModel,
            T entity,
            float limbAngle,
            float limbDistance,
            float customAngle,
            float headYaw,
            float headPitch,
            /* PARAMS FROM RENDER ARMOR*/
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            T entity$unused,
            float limbAngle$unused,
            float limbDistance$unused,
            float tickDelta,
            float customAngle$unused,
            float headYaw$unused,
            float headPitch$unused,
            EquipmentSlot slot
    ) {
        ItemStack stack = entity.getEquippedStack(slot);

        if (stack.getItem() instanceof Abstract3DArmorModel) {
            ((Abstract3DArmorModel) stack.getItem()).setAngles(entity, limbAngle, limbDistance, customAngle, headYaw, headPitch);
            return;
        }

        // Vanilla
        armorModel.setAngles(entity, limbAngle, limbDistance, customAngle, headPitch, headPitch);
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmorParts(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/item/ArmorItem;ZLnet/minecraft/client/render/entity/model/BipedEntityModel;ZFFFLjava/lang/String;)V"
            ), method = "renderArmor"
    )
    private void redirectCustomModelRenderer(
            ArmorFeatureRenderer<T, M, A> armorFeatureRenderer,
            EquipmentSlot slot,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            ArmorItem item,
            boolean glint,
            A armorModel,
            boolean secondLayer,
            float red,
            float green,
            float blue,
            String suffix
    ) {
        if (item instanceof Abstract3DArmorModel) {
            ((Abstract3DArmorModel) item).renderArmorParts(matrices, vertexConsumers, light, item, glint, armorModel, secondLayer, red, green, blue, suffix);
            return;
        }

        this.renderArmorParts(slot, matrices, vertexConsumers, light, item, glint, armorModel, secondLayer, red, green, blue, suffix);
    }
}
