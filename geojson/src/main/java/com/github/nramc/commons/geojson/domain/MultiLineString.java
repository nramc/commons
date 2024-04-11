package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class MultiLineString extends Geometry {
    private List<List<Position>> coordinates;

    public MultiLineString(List<List<Position>> coordinates) {
        super(GeoJsonType.MULTI_LINE_STRING.getTypeValue());
        coordinates.forEach(MultiLineString::validateAndThrowError);
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @JsonCreator
    public MultiLineString(String type, List<List<Position>> coordinates) {
        this(coordinates);
        if (!Objects.equals(type, GeoJsonType.MULTI_LINE_STRING.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. 'MultiLineString' expected, but got " + type);
        }
    }

    public static MultiLineString of(GeoJsonType type, List<List<Position>> coordinates) {
        if (type != GeoJsonType.MULTI_LINE_STRING) {
            throw new IllegalArgumentException("Invalid type. 'MultiLineString' expected, but got " + type);
        }
        return new MultiLineString(coordinates);
    }

    @SafeVarargs
    public static MultiLineString of(List<Position>... coordinates) {
        return new MultiLineString(Arrays.stream(coordinates).toList());
    }

    private static void validateAndThrowError(List<Position> coordinate) {
        if (coordinate.size() < 2) {
            throw new IllegalArgumentException("Invalid coordinates. Minimum 2 positions required");
        }
    }

}
