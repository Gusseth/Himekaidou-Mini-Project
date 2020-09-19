package net.himekaidou.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.himekaidou.entity.thrown.StoneBabyEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class StoneBabyEntityRenderer extends FlyingItemEntityRenderer<StoneBabyEntity>
{
    public StoneBabyEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, ItemRenderer itemRenderer)
    {
        super(entityRenderDispatcher, itemRenderer);
    }
}
