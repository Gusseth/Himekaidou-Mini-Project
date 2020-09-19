package net.himekaidou.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class RoukankenMaterial implements ToolMaterial
{
    private static final int DURABILITY = 500;   // Base durability value, infinite
    private static final int ATTACK_DAMAGE = 0;  // Damage dealt by the weapon

    private static final Ingredient REPAIR_INGREDIENT = Ingredient.EMPTY; // Repair ingredient

    @Override
    public int getDurability() {
        return DURABILITY;
    }

    @Override
    public float getMiningSpeed() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return ATTACK_DAMAGE;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return REPAIR_INGREDIENT;
    }
}
