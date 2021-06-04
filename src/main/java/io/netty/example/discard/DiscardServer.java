package io.netty.example.discard;

import io.netty.example.common.ServerWithHandlers;

public class DiscardServer {

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }

    new ServerWithHandlers(port, new DiscardServerHandler()).run();
  }
}
