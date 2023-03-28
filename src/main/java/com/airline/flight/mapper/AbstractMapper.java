package com.airline.flight.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface AbstractMapper<T,D> {

    D toEntity(T dto);
    T toDTO   (D entity);

    default List<D> toEntityList(List<T> datas) {
        return
            ((datas == null) || datas.isEmpty())
                ? Collections.emptyList()
                : datas
                .stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList())
            ;
    }

    default List<T> toDtoList(List<D> entities) {
        return
            ((entities == null) || entities.isEmpty())
                ? Collections.emptyList()
                : entities
                .stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList())
            ;
    }
}
