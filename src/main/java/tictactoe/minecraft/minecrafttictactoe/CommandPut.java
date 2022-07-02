package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Player;

public class CommandPut implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (GlobalGame.game == null) {
            sender.sendMessage("There is no game at this moment running!\n " +
                    "You can challenge your friends with \\challenge <name> command.");
            return false;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.equals(GlobalGame.game.getPlayer1()) && GlobalGame.game.isPlayer1Turn()) {
                int pos;
                try {
                    pos = Integer.parseInt(args[0]);
                    if (GlobalGame.game.getPlayer1Positions().contains(pos) || GlobalGame.game.getPlayer2Positions().contains(pos)) {
                        sender.sendMessage("This place is already taken! Choose another one.");
                        return false;
                    }
                    if (pos < 1 || pos > 9) {
                        sender.sendMessage("Enter a number only in range of 1 - 9.");
                        return false;
                    }
                    GlobalGame.game.placePiece(pos, player);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid input. Only numbers will be accepted.");
                    return false;
                }
                if (!GlobalGame.game.winnerCheck().equals("")) {
                    GlobalGame.game.getPlayer1().sendMessage(GlobalGame.game.winnerCheck());
                    GlobalGame.game.getPlayer2().sendMessage(GlobalGame.game.winnerCheck());
                    GlobalGame.game.save(GlobalGame.game.getPlayer1());
                    return true;
                }
                GlobalGame.game.setPlayer1Turn(false);
                GlobalGame.game.setPlayer2Turn(true);
                GlobalGame.game.getPlayer2().sendMessage("It's your turn. Enter a number between 1 - 9.");
                return true;
            }
            else if (player.equals(GlobalGame.game.getPlayer2()) && GlobalGame.game.isPlayer2Turn()) {
                int pos;
                try {
                    pos = Integer.parseInt(args[0]);
                    if (GlobalGame.game.getPlayer1Positions().contains(pos)||GlobalGame.game.getPlayer2Positions().contains(pos)) {
                        sender.sendMessage("This place is already taken! Choose another one.");
                        return false;
                    }
                    if (pos < 1 || pos > 9) {
                        sender.sendMessage("Enter a number only in range of 1 - 9.");
                        return false;
                    }
                    GlobalGame.game.placePiece(pos, player);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid input. Only numbers will be accepted.");
                    return false;
                }
                if (!GlobalGame.game.winnerCheck().equals("")) {
                    GlobalGame.game.getPlayer1().sendMessage(GlobalGame.game.winnerCheck());
                    GlobalGame.game.getPlayer2().sendMessage(GlobalGame.game.winnerCheck());
                    GlobalGame.game.save(GlobalGame.game.getPlayer1());
                    return true;
                }
                GlobalGame.game.setPlayer1Turn(true);
                GlobalGame.game.setPlayer2Turn(false);
                GlobalGame.game.getPlayer1().sendMessage("It's your turn. Enter a number between 1 - 9.");
                return true;
            }
            else if (!GlobalGame.game.isPlayer1Turn() || !GlobalGame.game.isPlayer2Turn()) {
                player.sendMessage("It's not your turn please wait!");
                return true;
            }
            else {
                player.sendMessage("Sorry there is no game for you right now :(\n" +
                        " But you can challenge a friend with \\challenge <name> command!");
                return true;
            }
        }
        return false;
    }
}
