package io.netty.example.echo;

import io.netty.example.discard.DiscardServerHandler;
import io.netty.example.discard.ServerWithSingleHandler;

public class EchoServer {

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }

    new ServerWithSingleHandler(port, new EchoServerHandler()).run();
  }
}
