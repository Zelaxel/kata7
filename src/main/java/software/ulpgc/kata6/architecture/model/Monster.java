package software.ulpgc.kata6.architecture.model;

import java.util.List;

public record Monster(String name, Type type, boolean isLarge, List<String> games) {
    public enum Type {
        Leviathan, Brute_Wyvern, Neopteron, Fanged_Beast, Flying_Wyvern,
        Bird_Wyvern, Elder_Dragon, Relict, Herbivore, Fanged_Wyvern,
        Wingdrake, Piscine_Wyvern, Amphibian, Lynian, Carapaceon,
        unknown, Fish, Demi_Elder, Construct, Temnoceran,
        Relicts, Snake_Wyvern, Cephalopod
    }
}
