package com.hlpriver.aevmod.item.custom;

import com.hlpriver.aevmod.entity.custom.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;

public class ASItem extends BowItem {
    public ASItem(Properties itemProperties) {
        super(itemProperties);
    }

    @Override
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.world.isRemote) {
            return arrow;
        }
        ExplosiveArrowEntity explosiveArrow = new ExplosiveArrowEntity(arrow.world,arrow.getShooter() instanceof LivingEntity ? (LivingEntity) arrow.getShooter() : null);
        explosiveArrow.ExplosionPower = 16.0F;
        explosiveArrow.setPosition(arrow.getPosX(), arrow.getPosY(), arrow.getPosZ());
        explosiveArrow.setMotion(arrow.getMotion());
        explosiveArrow.setDamage(arrow.getDamage());
        return explosiveArrow;
    }
}
