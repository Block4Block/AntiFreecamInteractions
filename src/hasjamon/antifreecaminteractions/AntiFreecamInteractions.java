package hasjamon.antifreecaminteractions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class AntiFreecamInteractions extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block b = e.getClickedBlock();

            if(b != null){
                Player p = e.getPlayer();
                Location pEyeLoc = p.getEyeLocation();
                Vector direction = pEyeLoc.getDirection();

                RayTraceResult result = p.getWorld().rayTraceBlocks(pEyeLoc, direction, 6.0);

                if(result != null && result.getHitBlock() != null)
                    if(!result.getHitBlock().equals(b))
                        e.setCancelled(true);
            }
        }
    }
}
