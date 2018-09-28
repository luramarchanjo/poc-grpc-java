package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ServerApplication {

  public static void main(String... arguments) throws IOException, InterruptedException {
    Server server = ServerBuilder
      .forPort(8099)
      .addService(PersonService.builder().build())
      .build();

    System.out.println("Started application");
    server.start();
    server.awaitTermination();
  }

}
