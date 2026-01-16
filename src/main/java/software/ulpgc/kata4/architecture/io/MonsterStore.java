package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Monster;

import java.util.stream.Stream;

public interface MonsterStore {
    Stream<Monster> monsters();
}
