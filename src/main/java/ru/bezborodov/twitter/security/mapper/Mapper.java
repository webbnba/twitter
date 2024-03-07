package ru.bezborodov.twitter.security.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
