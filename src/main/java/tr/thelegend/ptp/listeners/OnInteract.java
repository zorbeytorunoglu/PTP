package tr.thelegend.ptp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import tr.thelegend.ptp.PTP;
import tr.thelegend.ptp.objects.Teleporter;

public class OnInteract implements Listener {

    private final PTP plugin;
    public OnInteract(PTP plugin) {
        this.plugin=plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (e.getClickedBlock()==null) return;

        if (plugin.getTeleporters().isEmpty()) return;

        Teleporter teleporter=plugin.getUtil().getTeleporter(e.getClickedBlock().getLocation());

        if (teleporter==null) return;

        if (teleporter.getPermission()!=null) {
            if (!e.getPlayer().hasPermission(teleporter.getPermission())) {
                e.setCancelled(true);
                return;
            }
        }

        e.getPlayer().teleport(teleporter.getTargetLocation());

        if (teleporter.getSoundString()!=null) e.getPlayer().playSound(e.getPlayer().getLocation(), teleporter.getSound(), 2F, 1F);

    }

}
