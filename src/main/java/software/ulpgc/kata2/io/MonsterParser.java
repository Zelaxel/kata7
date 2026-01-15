package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.Monster;

public interface MonsterParser<T> {
    Monster parse(T input);
}
