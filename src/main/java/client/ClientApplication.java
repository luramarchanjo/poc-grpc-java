package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Random;
import java.util.UUID;
import lombok.val;
import server.domain.person.PersonRequest;
import server.domain.person.PersonResponse;
import server.domain.person.PersonServiceGrpc;
import server.domain.person.PersonServiceGrpc.PersonServiceBlockingStub;

public class ClientApplication {

  public static void main(String... arguments) {
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8099)
      .usePlaintext(true)
      .build();

    PersonServiceBlockingStub personStub = PersonServiceGrpc.newBlockingStub(managedChannel);

    for (int counter = 1; counter <= 10; counter++) {
      PersonRequest personRequest = PersonRequest.newBuilder()
        .setName(UUID.randomUUID().toString())
        .setAge(new Random().nextInt())
        .build();

      val startTime = System.currentTimeMillis();
      PersonResponse personResponse = personStub.createPerson(personRequest);
      System.out.println("Created Person in " + (System.currentTimeMillis() - startTime) + " ms");
    }
  }

}
