package net.himekaidou.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class TouhouArmorMaterial implements ArmorMaterial
{
    private static final int DURABILITY = -1;   // Base durability value, infinite
    private static final int PROTECTION = 2;    // All clothing/armor is given this protection
    private static final int TOUGHNESS = 2;     // Same as above, but toughness

    private static final String NAME = "bouhou";                                         // Tag given to armour
    private static final SoundEvent EQUIP_SOUND = SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;  // Plays this when armor is equipped

    private static final Ingredient REPAIR_INGREDIENT = Ingredient.EMPTY; // Repair ingredient, why need this if it's indestructible?

    @Override
    public int getDurability(EquipmentSlot slot) {
        return DURABILITY;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return EQUIP_SOUND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return REPAIR_INGREDIENT;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public float getToughness() {
        return TOUGHNESS;
    }
}
