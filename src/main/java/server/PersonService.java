package server;

import io.grpc.stub.StreamObserver;
import java.util.Random;
import lombok.Builder;
import server.domain.person.PersonRequest;
import server.domain.person.PersonResponse;
import server.domain.person.PersonServiceGrpc.PersonServiceImplBase;

@Builder
public class PersonService extends PersonServiceImplBase {

  @Override
  public void createPerson(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
    System.out.println("Received " + request);

    PersonResponse personResponse = PersonResponse.newBuilder()
      .setId(new Random().nextInt())
      .setName(request.getName())
      .setAge(request.getAge())
      .build();

    responseObserver.onNext(personResponse);
    responseObserver.onCompleted();
  }

}
