package net.himekaidou.client.render.entity.model;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public abstract class Abstract3DArmorModel<T extends LivingEntity> extends ArmorItem {
    public Abstract3DArmorModel(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    public abstract <T extends LivingEntity> void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta);

    public abstract <T extends LivingEntity> void setAngles(T entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch);

    public abstract <A extends BipedEntityModel<T>> void renderArmorParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, boolean glint, A armorModel, boolean secondLayer, float red, float green, float blue, String suffix);
}
