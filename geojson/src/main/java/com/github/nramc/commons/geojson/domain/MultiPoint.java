package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public final class MultiPoint extends Geometry {
    private final List<Position> coordinates;

    public MultiPoint(List<Position> coordinates) {
        super(GeoJsonType.MULTI_POINT);
        if (coordinates.size() < 2) {
            throw new IllegalArgumentException("Invalid coordinates. Minimum 2 positions required");
        }
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @JsonCreator
    public MultiPoint(GeoJsonType type, List<Position> coordinates) {
        this(coordinates);
        if (type != GeoJsonType.MULTI_POINT) {
            throw new IllegalArgumentException("Invalid type. 'MultiPoint' expected, but got " + type);
        }
    }

    public static MultiPoint of(GeoJsonType type, List<Position> coordinates) {
        if (type != GeoJsonType.MULTI_POINT) {
            throw new IllegalArgumentException("Invalid type. 'MultiPoint' expected, but got " + type);
        }
        return new MultiPoint(coordinates);
    }

    public static MultiPoint of(Position... positions) {
        return new MultiPoint(Arrays.stream(positions).toList());
    }

}
