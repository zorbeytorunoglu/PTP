package tr.thelegend.ptp;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import tr.thelegend.ptp.commands.PTPCmd;
import tr.thelegend.ptp.configuration.Container;
import tr.thelegend.ptp.configuration.Handler;
import tr.thelegend.ptp.configuration.Resource;
import tr.thelegend.ptp.listeners.OnInteract;
import tr.thelegend.ptp.objects.Teleporter;
import tr.thelegend.ptp.utils.Util;

import java.util.ArrayList;
import java.util.Set;

public class PTP extends JavaPlugin {

    private Resource config;
    private Resource data;
    private Container container;
    private Handler handler;
    private Util util;

    private ArrayList<Teleporter> teleporters=new ArrayList<>();

    public void onEnable() {
        config=new Resource(this,"config.yml");
        config.load();
        data=new Resource(this, "data.yml");
        data.load();
        container=new Container(config);
        handler=new Handler(container);
        util=new Util(this);

        new PTPCmd(this);
        new OnInteract(this);

        loadTeleporters();
    }

    public void onDisable() {
        saveTeleporters();
    }

    public Handler getHandler() {
        return handler;
    }

    public Resource getConfig() {
        return config;
    }

    public Resource getData() {
        return data;
    }

    public Util getUtil() {
        return util;
    }

    public ArrayList<Teleporter> getTeleporters() {
        return teleporters;
    }

    private void saveTeleporters() {

        if (getTeleporters().isEmpty()) return;

        for (Teleporter teleporter:getTeleporters()) {

            if (teleporter.getTargetLocation()==null) continue;

            data.set(teleporter.getName()+".location", teleporter.getLocation());
            data.set(teleporter.getName()+".target-location", teleporter.getTargetLocation());
            if (teleporter.getPermission()!=null) data.set(teleporter.getName()+".permission", teleporter.getPermission());
            if (teleporter.getSoundString()!=null) data.set(teleporter.getName()+".sound", teleporter.getSoundString());

        }

        data.save();

    }

    private void loadTeleporters() {
        Set<String> set=data.getKeys(false);
        if (set.isEmpty()) return;
        for (String s:set) {
            Teleporter teleporter=new Teleporter(s, (Location)data.get(s+".location"));
            if (data.isSet(s+".permission")) teleporter.setPermission(data.getString(s+".permission"));
            if (data.isSet(s+".sound")) {
                teleporter.setSoundString(data.getString(s+".sound"));
                teleporter.setSound(Sound.valueOf(data.getString(s+".sound")));
            }
            if (data.isSet(s+".target-location")) teleporter.setTargetLocation((Location)data.get(s+".target-location"));
            getTeleporters().add(teleporter);
        }
    }

}