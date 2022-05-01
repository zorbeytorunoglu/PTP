package tr.thelegend.ptp.configuration;

import tr.thelegend.ptp.utils.StringUtils;

import javax.sql.rowset.serial.SerialStruct;
import java.util.List;

public class Container {

    public final String noPerm;
    public final String onlyInGame;
    public final List<String> commands;
    public final String invalidBlock;
    public final String invalidString;
    public final String teleporterCreated;
    public final String alreadyExists;
    public final String teleporterNotFound;
    public final String locationSet;
    public final String createUsage;
    public final String setLocationUsage;
    public final String setSoundUsage;
    public final String deleteUsage;
    public final String deleted;
    public final String invalidSound;
    public final String soundSet;
    public final String setPermissionUsage;
    public final String noPermTeleporter;
    public final String permissionSet;

    public Container(Resource file) {
        this.noPerm= StringUtils.hex(file.getString("messages.no-perm"));
        this.onlyInGame=StringUtils.hex(file.getString("messages.only-in-game"));
        this.commands=file.getStringList("messages.commands");
        this.invalidBlock=StringUtils.hex(file.getString("messages.invalid-block"));
        this.invalidString=StringUtils.hex(file.getString("messages.invalid-string"));
        this.teleporterCreated=StringUtils.hex(file.getString("messages.teleporter-created"));
        this.alreadyExists=StringUtils.hex(file.getString("messages.already-exists"));
        this.teleporterNotFound=StringUtils.hex(file.getString("messages.teleporter-not-found"));
        this.locationSet=StringUtils.hex(file.getString("messages.location-set"));
        this.createUsage=StringUtils.hex(file.getString("messages.create-usage"));
        this.setLocationUsage=StringUtils.hex(file.getString("messages.setlocation-usage"));
        this.setSoundUsage=StringUtils.hex(file.getString("messages.setsound-usage"));
        this.deleteUsage=StringUtils.hex(file.getString("messages.delete-usage"));
        this.deleted=StringUtils.hex(file.getString("messages.deleted"));
        this.invalidSound=StringUtils.hex(file.getString("messages.invalid-sound"));
        this.soundSet=StringUtils.hex(file.getString("messages.sound-set"));
        this.setPermissionUsage=StringUtils.hex(file.getString("messages.set-permission-usage"));
        this.noPermTeleporter=StringUtils.hex(file.getString("messages.no-perm-teleporter"));
        this.permissionSet=StringUtils.hex(file.getString("messages.permission-set"));
    }
}
