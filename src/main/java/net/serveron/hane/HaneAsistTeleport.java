package net.serveron.hane;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import org.slf4j.Logger;

import java.util.Optional;

@Plugin(id = "haneasist", name = "HaneAsistTeleport", version = "0.1.0-SNAPSHOT"
        , description = "This is a velocity plugin", authors = {"tanetakumi"})
public class HaneAsistTeleport{
    private final RegisteredServer lobby;
    @Inject
    public HaneAsistTeleport(ProxyServer server, Logger logger) throws Exception {
        logger.info("This is a HaneAsis-Teleport");

        Optional<RegisteredServer> optLobby = server.getServer("lobby");
        if(optLobby.isEmpty()) throw new Exception("lobby is null");
        this.lobby = optLobby.get();

        server.getCommandManager().register("lobby", (SimpleCommand) invocation -> {
            CommandSource source = invocation.source();
            if(source instanceof Player){
                ((Player) source).createConnectionRequest(lobby).fireAndForget();
            }
        });
    }
}