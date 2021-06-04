package io.netty.example.time;

import io.netty.example.common.ServerWithSingleHandler;

public class TimeServer {

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }

    new ServerWithSingleHandler(port, new TimeServerHandler()).run();
  }
}
