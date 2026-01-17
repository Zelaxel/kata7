package software.ulpgc.kata5.architecture.io;

import software.ulpgc.kata5.architecture.model.Monster;

import java.util.stream.Stream;

public interface Record {
    void put(Stream<Monster> monster);
}
