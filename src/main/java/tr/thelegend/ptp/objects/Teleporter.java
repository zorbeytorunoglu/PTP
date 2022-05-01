package tr.thelegend.ptp.objects;

import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.Objects;

public class Teleporter {

    private final String name;

    private final Location location;

    private Location targetLocation=null;

    private String soundString=null;

    private Sound sound;

    private String permission=null;

    public Teleporter(String name, Location location) {
        super();
        this.name=name;
        this.location=location;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Sound getSound() {
        return sound;
    }

    public String getSoundString() {
        return soundString;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setSoundString(String soundString) {
        this.soundString = soundString;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "Teleporter{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
