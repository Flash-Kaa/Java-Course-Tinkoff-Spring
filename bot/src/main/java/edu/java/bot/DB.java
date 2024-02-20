package edu.java.bot;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;

public class DB {
    private static List<Pair<Long, URI>> db = new ArrayList<>();

    private DB() {
    }

    public static void add(long id, URI uri) {
        db.add(new Pair<>(id, uri));
    }

    public static boolean remove(long id, URI uri) {
        return db.remove(new Pair<>(id, uri));
    }

    public static void addUser(long id) {
    }

    public static List<URI> getAllUserResources(long id) {
        return db.stream()
            .filter(it -> it.getFirst().equals(id))
            .map(Pair::getSecond)
            .toList();
    }
}
