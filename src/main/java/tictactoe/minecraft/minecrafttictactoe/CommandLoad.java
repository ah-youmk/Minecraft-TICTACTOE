package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLoad implements CommandExecutor {
    private Player loader = null;
    private boolean isSure = false;
    private int id = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("A value for <id> must be entered to command work properly.");
            return false;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].charAt(0) == 'y' && player.equals(loader)) {
                isSure = true;
            }
            else if (args[0].charAt(0) == 'n' && player.equals(loader)) {
                isSure  = false;
                player.sendMessage("Loading canceled.");
                return false;
            }
            else if (player.equals(loader) && args[0].charAt(0) != 'n' && args[0].charAt(0) == 'y'){
                player.sendMessage("Invalid input. only 'y' or 'n'.");
                return false;
            }
            if (GlobalGame.game != null && (GlobalGame.game.isAccepted() || GlobalGame.game.isLoaded()) && !isSure) {
                try {
                    id = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Only numbers will be accepted.");
                    return false;
                }
                player.sendMessage("There is an unfinished game which is not saved yet. Do you want to continue?\n" +
                        "(Type \"/load <y>\" or \"/load <n>\"");
                loader = player;
                return true;
            }
            GlobalGame.game = new Game(player, null);
            if (isSure && player.equals(loader)) {
                try {
                    GlobalGame.game.load(id, player);
                    loader = null;
                    isSure = false;
                    id = 0;
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Only numbers will be accepted.");
                    return false;
                }
            }
            else {
                try {
                    GlobalGame.game.load(Integer.parseInt(args[0]), player);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Only numbers will be accepted.");
                    return false;
                }
                return false;
            }
        }
        else {
            sender.sendMessage("You can't do that!");
            return false;
        }
    }
}
