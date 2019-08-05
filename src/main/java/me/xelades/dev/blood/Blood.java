package me.xelades.dev.blood;

import org.bukkit.*;
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

        if ((event.getFinalDamage() * 5) > 50) {
            particleCount = 40.0;
        }
        else if ((event.getFinalDamage()) * 5 < 0) {
            particleCount = 0.0;
        }
        else {
            particleCount = event.getFinalDamage() * 8;
        }

        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, bloodLocation, particleCount.intValue(), 0.4, 0.4, 0.4, Material.REDSTONE_BLOCK.createBlockData());
    }
}
