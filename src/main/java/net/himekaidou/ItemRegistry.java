package net.himekaidou;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.himekaidou.armor.TouhouArmorMaterial;
import net.himekaidou.armor.marisa_Hat;
import net.himekaidou.entity.thrown.StoneBabyEntity;
import net.himekaidou.item.RoukankenItem;
import net.himekaidou.item.StoneBabyItem;
import net.himekaidou.item.ThirdEyeClosedItem;
import net.himekaidou.item.test_Item;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry
{

    // General Items
    public static final Item test_Item = new test_Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item third_Eye_Closed = new ThirdEyeClosedItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

    // Tools


    // Weapons
    public static final Item roukanken = new RoukankenItem();
    public static final Item stone_Baby = new StoneBabyItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

    // Armor
    public static final ArmorMaterial touhou_Armor_Material = new TouhouArmorMaterial();

    // Entities


    /*public static final EntityType<ThrownItemGeneralEntity> generic_Item =
            Registry.register
                    (
                            Registry.ENTITY_TYPE,
                            new Identifier(Main.MODID, "generic_item_entity"),
                            FabricEntityTypeBuilder.<ThrownItemGeneralEntity>create(EntityCategory.MISC, ThrownItemGeneralEntity::new).size(EntityDimensions.fixed(.5f,.5f)).build()
                    );
    */
    public static final EntityType<StoneBabyEntity> stone_Baby_Entity =
            Registry.register
                    (
                            Registry.ENTITY_TYPE,
                            new Identifier(Main.MODID, "stone_baby_entity"),
                            FabricEntityTypeBuilder.<StoneBabyEntity>create(EntityCategory.MISC, StoneBabyEntity::new).build()
                    );

    private static void RegisterItem(Item item, String itemName)
    {
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, itemName), item);
    }


    public static void LoadItems()
    {

        RegisterItem(test_Item, "test_item");
        RegisterItem(third_Eye_Closed, "third_eye_closed");

        RegisterItem(roukanken, "roukanken");
        RegisterItem(stone_Baby, "stone_baby");

        Registry.register(Registry.ITEM, new Identifier(Main.MODID, "marisa_hat"), new marisa_Hat(touhou_Armor_Material, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.MISC)));
    }

    @Environment(EnvType.CLIENT)
    public static void LoadItemsClient()
    {
        EntityRendererRegistry.INSTANCE.register(stone_Baby_Entity, (entityRenderDispatcher, context) -> new FlyingItemEntityRenderer<StoneBabyEntity>(entityRenderDispatcher, context.getItemRenderer()));
    }

}
