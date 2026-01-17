package software.ulpgc.kata6.architecture.io;

import software.ulpgc.kata6.architecture.model.Monster;

import java.util.stream.Stream;

public interface Store {
    Stream<Monster> monsters();
}
