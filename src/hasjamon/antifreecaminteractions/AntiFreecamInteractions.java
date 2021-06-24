package hasjamon.antifreecaminteractions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
            if(notLookingAtBlock(e.getClickedBlock(), e.getPlayer()))
                e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent e){
        if(notLookingAtBlock(e.getBlock(), e.getPlayer()))
            e.setCancelled(true);
    }

    private boolean notLookingAtBlock(Block b, Player p){
        if(b != null){
            Location pEyeLoc = p.getEyeLocation();
            Vector direction = pEyeLoc.getDirection();

            RayTraceResult result = p.getWorld().rayTraceBlocks(pEyeLoc, direction, 6.0);

            if(result != null && result.getHitBlock() != null)
                return !result.getHitBlock().equals(b);
        }

        return false;
    }
}
