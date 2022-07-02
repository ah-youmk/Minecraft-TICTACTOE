package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ListenerBlockBreak implements Listener {

    @EventHandler
    public void cancelBlockBreak(BlockBreakEvent e) {
        if (GlobalGame.game != null)
            for (Location[] row : GlobalGame.game.getGameBoard()) {
                for (Location c : row) {
                    if (e.getPlayer().getTargetBlock(null, 1000).equals(c.getBlock())) {
                        e.setCancelled(true);
                        break;
                    }
                }
            }
    }
}
