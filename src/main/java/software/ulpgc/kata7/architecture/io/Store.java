package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.Monster;

import java.util.stream.Stream;

public interface Store {
    Stream<Monster> monsters();
}
