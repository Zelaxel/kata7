package software.ulpgc.kata6.architecture.io;

import software.ulpgc.kata6.architecture.model.Monster;

import java.util.stream.Stream;

public interface Record {
    void put(Stream<Monster> monster);
}
