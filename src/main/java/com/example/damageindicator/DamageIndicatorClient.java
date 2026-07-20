package com.example.damageindicator;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class DamageIndicatorClient implements ClientModInitializer {
    public static final Identifier DAMAGE_PACKET = 
        new Identifier(DamageIndicatorMod.MOD_ID, "damage_highlight");
    
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(DAMAGE_PACKET, (client, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            client.execute(() -> {
                if (client.world != null) {
                    Entity entity = client.world.getEntityById(entityId);
                    if (entity != null) {
                        DamageIndicatorMod.highlightEntity(entity);
                    }
                }
            });
        });
    }
}
