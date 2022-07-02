package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;

public class ListenerPutPieces implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void putPieces(PlayerInteractEvent e) {
        if (GlobalGame.game != null && e.getHand() == EquipmentSlot.OFF_HAND) {
            Player p = e.getPlayer();
            ArrayList<Location> locations = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    locations.add(GlobalGame.game.getGameBoard()[i][j]);
                }
            }

            if (locations.contains(p.getTargetBlockExact(1000).getLocation())) {
                int pos = locations.indexOf(p.getTargetBlockExact(1000).getLocation()) + 1;
                p.performCommand("put " + pos);
            }
        }
    }
}