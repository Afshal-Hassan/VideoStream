package com.youtube.VideoService.enums;

import lombok.ToString;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;


@ToString
public enum Search {


    ALL("all"),
    BY_SEARCH("bySearch"),
    INVALID("invalid");


    public final String type;


    Search(String type) {
        this.type = type;
    }


    private static final Map<String, Search> searchByTypes =
            Stream.of(values())
                    .filter(c -> c != INVALID)
                    .collect(toUnmodifiableMap(c -> c.type.toLowerCase(), identity()));


    public static boolean isAll(String type) {
        return ALL.type.equalsIgnoreCase(type);
    }


    public static boolean isBySearch(String type) {
        return BY_SEARCH.type.equalsIgnoreCase(type);
    }


    public static Search getByType(String type) {
        if (null == type) return INVALID;

        return searchByTypes.getOrDefault(type.toLowerCase(), INVALID);
    }


    public boolean isInvalid() {
        return this == INVALID;
    }
}
