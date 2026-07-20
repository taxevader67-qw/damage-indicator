package com.example.damageindicator;

import net.minecraft.entity.Entity;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DamageIndicatorMod {
    public static final String MOD_ID = "damage-indicator";
    public static final Map<UUID, Long> HIGHLIGHTED_ENTITIES = new ConcurrentHashMap<>();
    public static final long HIGHLIGHT_DURATION = 500;
    
    public static void highlightEntity(Entity entity) {
        HIGHLIGHTED_ENTITIES.put(entity.getUuid(), System.currentTimeMillis() + HIGHLIGHT_DURATION);
    }
    
    public static boolean isHighlighted(Entity entity) {
        UUID uuid = entity.getUuid();
        Long endTime = HIGHLIGHTED_ENTITIES.get(uuid);
        if (endTime == null) return false;
        if (System.currentTimeMillis() > endTime) {
            HIGHLIGHTED_ENTITIES.remove(uuid);
            return false;
        }
        return true;
    }
    
    public static float getHighlightAlpha(Entity entity) {
        Long endTime = HIGHLIGHTED_ENTITIES.get(entity.getUuid());
        if (endTime == null) return 0.0f;
        long remaining = endTime - System.currentTimeMillis();
        if (remaining <= 0) return 0.0f;
        float progress = (float) remaining / HIGHLIGHT_DURATION;
        return Math.min(1.0f, progress * 2.0f);
    }
}
