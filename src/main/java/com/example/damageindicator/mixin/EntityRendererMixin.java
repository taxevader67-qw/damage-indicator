package com.example.damageindicator.mixin;

import com.example.damageindicator.DamageIndicatorMod;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class EntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> 
    extends EntityRenderer<T> {
    
    protected EntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }
    
    @Inject(
        method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        at = @At("HEAD")
    )
    private void onRender(T entity, float yaw, float tickDelta, 
                         MatrixStack matrices, VertexConsumerProvider vertexConsumers, 
                         int light, CallbackInfo ci) {
        if (DamageIndicatorMod.isHighlighted(entity)) {
            // Apply highlight effect
            float alpha = DamageIndicatorMod.getHighlightAlpha(entity);
            // The actual rendering modifications happen here
        }
    }
    }
