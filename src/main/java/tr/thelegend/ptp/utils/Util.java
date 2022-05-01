package tr.thelegend.ptp.utils;

import org.bukkit.Location;
import org.bukkit.Sound;
import tr.thelegend.ptp.PTP;
import tr.thelegend.ptp.objects.Teleporter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Util {

    private final PTP plugin;
    public Util(PTP plugin) {
        this.plugin=plugin;
    }

    public boolean teleporterExists(String name) {
        if (plugin.getTeleporters().isEmpty()) return false;
        return plugin.getTeleporters().stream().anyMatch(teleporter -> teleporter.getName().equals(name));
    }

    public boolean teleporterExists(Location location) {
        if (plugin.getTeleporters().isEmpty()) return false;
        return plugin.getTeleporters().stream().anyMatch(teleporter -> teleporter.getName().equals(location));
    }

    public Teleporter getTeleporter(Location location) {
        if (plugin.getTeleporters().isEmpty()) return null;
        for (Teleporter teleporter: plugin.getTeleporters()) {
            if (teleporter.getLocation().equals(location)) return teleporter;
        }
        return null;
    }

    public Teleporter getTeleporter(String name) {
        if (plugin.getTeleporters().isEmpty()) return null;
        for (Teleporter teleporter: plugin.getTeleporters()) {
            if (teleporter.getName().equals(name)) return teleporter;
        }
        return null;
    }

    public boolean isValidSound(final String sound) {
        return Arrays.stream(Sound.values())
                .map(Sound::name)
                .collect(Collectors.toSet())
                .contains(sound);
    }

}
