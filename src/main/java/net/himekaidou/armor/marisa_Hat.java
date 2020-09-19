package net.himekaidou.armor;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class marisa_Hat extends ArmorItem
{

    public marisa_Hat(ArmorMaterial material, EquipmentSlot slot, Settings settings)
    {
        super(material, slot, settings);
    }

    public ModelWithHat getArmorModel(LivingEntity livingEntity, ItemStack itemStack, int armorSlot)
    {
        if (armorSlot == 1)
        {
            return null;
        }
        else
        {
            return null;
        }
    }
}
