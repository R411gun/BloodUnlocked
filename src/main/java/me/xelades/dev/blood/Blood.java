package me.xelades.dev.blood;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Blood extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    //@Override
    //public void onDisable() {
    //}

    @EventHandler
    public void onHit (EntityDamageByEntityEvent event) {
        Location bloodLocation = event.getEntity().getLocation();

        bloodLocation.setY(bloodLocation.getY() + (event.getEntity().getHeight() / 1.5));

        Double particleCount;
        Double particleMultiplier = 5.0;
        Double particleMax = 500.0;
        Double particleRange = 0.6;

        if (event.getFinalDamage() * particleMultiplier > particleMax) {
            particleCount = particleMax;
        }
        else if (event.getFinalDamage() * particleMultiplier < 0) {
            particleCount = 0.0;
        }
        else {
            particleCount = event.getFinalDamage() * particleMultiplier;
        }

        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, bloodLocation, particleCount.intValue(), particleRange, particleRange, particleRange, Material.REDSTONE_BLOCK.createBlockData());
    }
}
