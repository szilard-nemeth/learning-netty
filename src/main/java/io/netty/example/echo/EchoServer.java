package io.netty.example.echo;

import io.netty.example.common.ServerWithHandlers;

public class EchoServer {

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }

    new ServerWithHandlers(port, new EchoServerHandler()).run();
  }
}
