package net.himekaidou.client;

/*
public class MarisaHatRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M>
{

    public MarisaHatRenderLayer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(T entity, float f, float g, float h, float i, float j, float k, float l)
    {
        Item helmet = entity.getEquippedStack(EquipmentSlot.HEAD).getItem();
        if (!entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty() && helmet instanceof marisa_Hat)
        {
            marisa_Hat hat = (marisa_Hat) helmet;
            GlStateManager.scalef(1.01F, 1.01F, 1.01F);

            if(entity.isSneaking())
            {
                GlStateManager.translated(0, 0.250D, 0);
            }

            MarisaHatModel<T> hatModel = new MarisaHatModel<>(1);

            this.bindTexture(new Identifier("himekaidou", "textures/armor/marisahattexture.png"));

            hatModel.render(entity, f, g, i, j, k, l);
            GlStateManager.translatef(0, 0, 0F);
            GlStateManager.scalef(1F, 1F, 1F);
        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}
*/