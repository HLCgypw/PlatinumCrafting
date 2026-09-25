package com.hlpriver.aevmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosiveArrowEntity extends ArrowEntity {

    public float ExplosionPower = 2.0F;

    public ExplosiveArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn){
        super(type,worldIn);

    }
    public ExplosiveArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn, float ExplosionPower) {
        super(type, worldIn);
        this.ExplosionPower = ExplosionPower;
    }

    public ExplosiveArrowEntity(World worldIn, LivingEntity shooter) {
        super(worldIn, shooter);

    }

    public ExplosiveArrowEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        if (!this.world.isRemote){
            this.world.createExplosion(this,this.getPosX(),this.getPosY(),this.getPosZ(),this.ExplosionPower, Explosion.Mode.BREAK);
            this.remove();
        }
    }

    //BlockHit--命中方块
    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        if (!this.world.isRemote){
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), this.ExplosionPower, Explosion.Mode.BREAK);
            this.remove();
        }
        System.out.println("customArrow called");
    }

    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
