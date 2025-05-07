package net.blwsy.armorhider.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.blwsy.armorhider.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CommandArmorHider extends CommandBase {
    @Override
    public String getCommandName() {
        return "armor";
    }

    @Override
    public List<String> getCommandAliases() {
        return new ArrayList<>(Collections.singleton("armorhider"));
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/armor [on|off|status|reload]";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.equals(Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>(Arrays.asList("on", "off", "status", "reload"));
            subCommands.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return subCommands;
        }
        return new ArrayList<>();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            boolean enabled = Config.enable.getBoolean();
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fMod§7: §eArmor Hider §7- §fVersion§7: §e1.0§7 - §fAuthor§7: §6blwsy"));
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fStatus: (" + (enabled ? "§aON§f" : "§cOFF§f") + ")"));
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fCMD: §a/armor on §7- §fHide your armor"));
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fCMD: §a/armor §coff §7- §fShow your armor"));
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fCMD: §a/armor §bstatus §7- §fShow armor status"));
            sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fCMD: §a/armor §breload §7- §fReload config"));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                Config.enable.set(true);
                sender.addChatMessage(new ChatComponentText("§aArmor hiding enabled."));
                return;
            case "off":
                Config.enable.set(false);
                sender.addChatMessage(new ChatComponentText("§cArmor hiding disabled."));
                return;
            case "status":
                sender.addChatMessage(new ChatComponentText("§7[§dR§7] §fStatus: (" + (Config.enable.getBoolean() ? "§aON§f" : "§cOFF§f") + ")"));
                return;
            case "reload":
                Config.reloadConfig();
                sender.addChatMessage(new ChatComponentText("§eConfig reloaded."));
                return;
        }

        throw new WrongUsageException(getCommandUsage(sender));
    }


    private String booleanText(boolean b) {
        return b ? "§atrue" : "§cfalse";
    }
}
