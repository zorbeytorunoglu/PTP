package tr.thelegend.ptp.configuration;

import tr.thelegend.ptp.utils.StringUtils;

import java.util.ArrayList;

public class Handler {

    private Container container;

    public Handler(Container container) {
        this.container=container;
    }

    public String getNoPerm() { return container.noPerm; }
    public String getOnlyInGame() { return container.onlyInGame; }
    public ArrayList<String> getCommands() { ArrayList<String> commands=new ArrayList<>(); for (String s:container.commands) { commands.add(StringUtils.hex(s)); } return commands; }
    public String getInvalidBlock() { return container.invalidBlock; }
    public String getInvalidString() { return container.invalidString; }
    public String getTeleporterCreated() { return container.teleporterCreated; }
    public String getAlreadyExists() { return container.alreadyExists; }
    public String getTeleporterNotFound() { return container.teleporterNotFound; }
    public String getLocationSet() { return container.locationSet; }
    public String getCreateUsage() { return container.createUsage; }
    public String getSetLocationUsage() { return container.setLocationUsage; }
    public String getSetSoundUsage() { return container.setSoundUsage; }
    public String getDeleteUsage() { return container.deleteUsage; }
    public String getDeleted() { return container.deleted; }
    public String getInvalidSound() { return container.invalidSound; }
    public String getSoundSet() { return container.soundSet; }
    public String getPermissionSet() { return container.permissionSet; }
    public String getSetPermissionUsage() { return container.setPermissionUsage; }
    public String getNoPermTeleporter() { return container.noPermTeleporter; }

}
