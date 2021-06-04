package io.netty.example.common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Discards any incoming data.
 */
public class ServerWithHandlers {
    private int port;
    private List<ChannelHandler> handlers;

    public ServerWithHandlers(int port, ChannelHandler handler) {
        this.port = port;
        this.handlers = new ArrayList<>();
        this.handlers.add(handler);
    }
    
    public ServerWithHandlers(int port, ChannelHandler... handlers) {
        this.port = port;
        this.handlers = Arrays.asList(handlers);
    }
    
    public void run() throws Exception {
        //The first one, often called 'boss', accepts an incoming connection. 
        // The second one, often called 'worker', handles the traffic of the accepted connection once 
        // the boss accepts the connection and registers the accepted connection to the worker
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     for (ChannelHandler handler : handlers) {
                         ch.pipeline().addLast(handler);
                     }
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
    
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
    
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}