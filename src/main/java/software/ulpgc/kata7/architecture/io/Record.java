package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.Monster;

import java.util.stream.Stream;

public interface Record {
    void put(Stream<Monster> monster);
}
