package software.ulpgc.kata5.architecture.io;

import software.ulpgc.kata5.architecture.model.Monster;

import java.util.stream.Stream;

public interface Store {
    Stream<Monster> monsters();
}
