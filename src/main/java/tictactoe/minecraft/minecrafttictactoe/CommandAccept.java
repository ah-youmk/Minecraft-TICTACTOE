package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAccept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player2 = (Player) sender;
        if (GlobalGame.game != null && player2.equals(GlobalGame.game.getPlayer2())) {
            GlobalGame.game.setAccepted(true);
            Player player1 = null;
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.equals(GlobalGame.game.getPlayer1())) {
                    player1 = p;
                    break;
                }
            }
            sender.sendMessage("Challenge accepted!");
            Location l = player1.getLocation().add(-5, 10, -5);
            for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                   l.getBlock().setType(Material.WHITE_WOOL);
                   l.add(1, 0, 0);
               }
               l.add(-9,0,1);
            }
            int x = player1.getLocation().getBlockX();
            int y = player1.getLocation().getBlockY();
            int z = player1.getLocation().getBlockZ();

            for (int i = -5; i < 5; i++) {
                for (int j = -5; j < 5; j++) {
                    new Location(player1.getWorld(), x+j, y+10, z+i).getBlock().setType(Material.WHITE_WOOL);
                }
            }

            player1.teleport(new Location(player1.getWorld(), x, y+11, z));
            player2.teleport(new Location(player1.getWorld(), x+3, y+11, z));

            GlobalGame.game.setPlayer1Turn(true);
            player1.sendMessage("Challenge accepted!\n It's your turn. Enter a number between 1 - 9.");
            player2.sendMessage("Game started! Please wait, it's " + player1.getDisplayName() + "'s turn.");
            GlobalGame.game.setGameBoard(player1);
            GlobalGame.game.showTable();
            return true;
        } else
            sender.sendMessage("You have not been challenged by anyone!");
        return false;
    }
}
