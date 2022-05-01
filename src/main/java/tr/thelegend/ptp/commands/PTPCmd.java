package tr.thelegend.ptp.commands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tr.thelegend.ptp.PTP;
import tr.thelegend.ptp.objects.Teleporter;
import tr.thelegend.ptp.utils.StringUtils;

import java.util.Set;

public class PTPCmd implements CommandExecutor {

    private final PTP plugin;
    public PTPCmd(PTP plugin) {
        this.plugin=plugin;
        plugin.getCommand("ptp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("ptp")) {

            if (args.length==0) {

                if (!sender.hasPermission("ptp.commands")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                for (String s:plugin.getHandler().getCommands()) {
                    sender.sendMessage(s);
                }

                return true;

            }

            if (args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission("ptp.create")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                if (args.length!=2) {
                    sender.sendMessage(plugin.getHandler().getCreateUsage());
                    return false;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getHandler().getOnlyInGame());
                    return false;
                }

                Player player=(Player)sender;

                Block targetBlock = player.getTargetBlock((Set<Material>) null, 10);

                if (targetBlock==null || targetBlock.getType()==Material.AIR) {

                    player.sendMessage(plugin.getHandler().getInvalidBlock());
                    return false;

                }

                if (!StringUtils.isAlphaNumeric(args[1])) {
                    sender.sendMessage(plugin.getHandler().getInvalidString());
                    return false;
                }

                if (plugin.getUtil().teleporterExists(args[1])) {
                    sender.sendMessage(plugin.getHandler().getAlreadyExists());
                    return false;
                }

                Teleporter teleporter=new Teleporter(args[1], targetBlock.getLocation());

                plugin.getTeleporters().add(teleporter);

                sender.sendMessage(plugin.getHandler().getTeleporterCreated().replace("%name%", args[1]));

                return true;

            }

            if (args[0].equalsIgnoreCase("setloc") || args[0].equalsIgnoreCase("setlocation")) {
                if (!sender.hasPermission("ptp.setlocation")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                if (args.length!=2) {
                    sender.sendMessage(plugin.getHandler().getSetLocationUsage());
                    return false;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage(plugin.getHandler().getOnlyInGame());
                    return false;
                }

                Player player=(Player)sender;

                Teleporter teleporter=plugin.getUtil().getTeleporter(args[1]);

                if (teleporter==null) {
                    sender.sendMessage(plugin.getHandler().getTeleporterNotFound());
                    return false;
                }

                teleporter.setTargetLocation(player.getLocation());

                sender.sendMessage(plugin.getHandler().getLocationSet().replace("%teleporter%", args[1]));

                return true;

            }

            if (args[0].equalsIgnoreCase("delete")) {
                if (!sender.hasPermission("ptp.delete")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                if (args.length!=2) {
                    sender.sendMessage(plugin.getHandler().getDeleteUsage());
                    return false;
                }

                Teleporter teleporter=plugin.getUtil().getTeleporter(args[1]);

                if (teleporter==null) {
                    sender.sendMessage(plugin.getHandler().getTeleporterNotFound());
                    return false;
                }

                plugin.getTeleporters().remove(teleporter);

                sender.sendMessage(plugin.getHandler().getDeleted().replace("%teleporter%", args[1]));

                return true;

            }

            if (args[0].equalsIgnoreCase("setsound")) {
                if (!sender.hasPermission("ptp.setsound")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                if (args.length!=3) {
                    sender.sendMessage(plugin.getHandler().getSetSoundUsage());
                    return false;
                }

                if (!plugin.getUtil().isValidSound(args[2])) {
                    sender.sendMessage(plugin.getHandler().getInvalidSound());
                    return false;
                }

                Teleporter teleporter=plugin.getUtil().getTeleporter(args[1]);

                if (teleporter==null) {
                    sender.sendMessage(plugin.getHandler().getTeleporterNotFound());
                    return false;
                }

                teleporter.setSoundString(args[2]);
                teleporter.setSound(Sound.valueOf(args[2]));

                sender.sendMessage(plugin.getHandler().getSoundSet());

                return true;

            }

            if (args[0].equalsIgnoreCase("setpermission")) {
                if (!sender.hasPermission("ptp.setpermission")) {
                    sender.sendMessage(plugin.getHandler().getNoPerm());
                    return false;
                }

                if (args.length!=3) {
                    sender.sendMessage(plugin.getHandler().getSetPermissionUsage());
                    return false;
                }

                Teleporter teleporter=plugin.getUtil().getTeleporter(args[1]);

                if (teleporter==null) {
                    sender.sendMessage(plugin.getHandler().getTeleporterNotFound());
                    return false;
                }

                if (args[2].equalsIgnoreCase("none")) {
                    teleporter.setPermission(null);
                } else {
                    teleporter.setPermission(args[2]);
                }

                sender.sendMessage(plugin.getHandler().getPermissionSet().replace("%teleporter%", teleporter.getName())
                        .replace("%permission%", args[2]));

                return true;

            }

        }

        return false;

    }
}
