package eu.mshade.enderchest.listener.packet;

import eu.mshade.enderchest.EnderChest;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.packetevent.MinecraftPacketHandshakeEvent;
import eu.mshade.enderframe.protocol.*;
import eu.mshade.enderframe.protocol.packet.MinecraftPacketOutDisconnect;
import eu.mshade.mwork.event.EventListener;
import io.netty.channel.Channel;


public class MinecraftPacketHandshakeListener implements EventListener<MinecraftPacketHandshakeEvent> {

    @Override
    public void onEvent(MinecraftPacketHandshakeEvent event) {
        MinecraftProtocolPipeline minecraftProtocolPipeline = MinecraftProtocolPipeline.get();
        MinecraftSession minecraftSession = event.getMinecraftSession();
        Channel channel = minecraftSession.channel;
        MinecraftHandshake minecraftHandshake = event.getHandshake();
        MinecraftHandshakeStatus minecraftHandshakeStatus = minecraftHandshake.getHandshakeStatus();
        MinecraftProtocolVersion minecraftProtocolVersion = minecraftHandshake.getVersion();

        if (minecraftHandshakeStatus == MinecraftHandshakeStatus.STATUS){
            minecraftSession.toggleProtocolStatus(MinecraftProtocolStatus.STATUS);
        }else {
            minecraftSession.toggleProtocolStatus(MinecraftProtocolStatus.LOGIN);
            if (minecraftProtocolVersion != MinecraftProtocolVersion.UNKNOWN) {
                MinecraftProtocol minecraftProtocol = EnderChest.INSTANCE.getMinecraftServer().getMinecraftProtocols().getProtocol(minecraftProtocolVersion);
                if (minecraftProtocol != null) {
                    minecraftSession = minecraftProtocol.getMinecraftSession(channel);
                    minecraftProtocolPipeline.setMinecraftSession(channel, minecraftSession);
                    minecraftSession.toggleProtocol(minecraftProtocol);
                    minecraftSession.toggleProtocolStatus(MinecraftProtocolStatus.LOGIN);
                }else {
                    minecraftSession.sendPacketAndClose(new MinecraftPacketOutDisconnect(TextComponent.of("Your version is not supported by MShade")));
                }
            } else {
                minecraftSession.sendPacketAndClose(new MinecraftPacketOutDisconnect(TextComponent.of("Your version is not supported by MShade")));
            }
        }
        minecraftSession.setHandshake(minecraftHandshake);

    }
}
