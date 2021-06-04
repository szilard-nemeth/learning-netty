package io.netty.example.time_pojo;

import io.netty.example.common.ServerWithHandlers;
import io.netty.example.time.TimeServerHandler;

public class TimeServerPojo {

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }

    new ServerWithHandlers(port, new TimeEncoderPojo(), new TimeServerHandlerPojo()).run();
  }
}
