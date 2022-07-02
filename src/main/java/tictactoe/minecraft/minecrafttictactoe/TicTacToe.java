package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.plugin.java.JavaPlugin;

public final class TicTacToe extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("challenge").setExecutor(new CommandChallenge());
        getServer().getPluginCommand("put").setExecutor(new CommandPut());
        getServer().getPluginCommand("accept").setExecutor(new CommandAccept());
        getServer().getPluginCommand("deny").setExecutor(new CommandDeny());
        getServer().getPluginCommand("save").setExecutor(new CommandSave());
        getServer().getPluginCommand("load").setExecutor(new CommandLoad());
        getServer().getPluginManager().registerEvents(new ListenerBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new ListenerPutPieces(), this);
    }
}
