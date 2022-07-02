package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSave implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (GlobalGame.game != null && GlobalGame.game.isAccepted()) {
                GlobalGame.game.save(player);
                return true;
            }
            else if(GlobalGame.game != null && GlobalGame.game.isLoaded()) {
                GlobalGame.game.saveLoaded(player);
            }
            else {
                sender.sendMessage("There is no game to save!");
            }
        }
        return false;
    }
}
