package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChallenge implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("A value for <name> must be entered to command work properly.");
            return false;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getDisplayName().equals(args[0])) {
                sender.sendMessage("You cannot send a challenge to yourself!");
                return false;
            }
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.getDisplayName().equals(args[0])) {
                    p.sendMessage("Player : " + player.getDisplayName() + " challenged you! what's your answer?" +
                            " (/accept or /deny)");
                    GlobalGame.game = new Game(player, p);
                    sender.sendMessage("Challenge requested. Waiting for respond...");
                    return true;
                }
            }
        }
        sender.sendMessage("Player not found!");
        return false;
    }
}
