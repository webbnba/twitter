package ru.bezborodov.twitter.common.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
