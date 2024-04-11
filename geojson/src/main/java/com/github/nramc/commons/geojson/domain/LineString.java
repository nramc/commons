package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class LineString extends Geometry {
    private List<Position> coordinates;

    public LineString(List<Position> coordinates) {
        super(GeoJsonType.LINE_STRING.getTypeValue());
        if (coordinates.size() < 2) {
            throw new IllegalArgumentException("Invalid coordinates. Minimum 2 positions required");
        }
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @JsonCreator
    public LineString(String type, List<Position> coordinates) {
        this(coordinates);
        if (!Objects.equals(type, GeoJsonType.LINE_STRING.getTypeValue())) {
            throw new IllegalArgumentException(String.format("Invalid type. expected[%s] got:[%s]",
                    GeoJsonType.LINE_STRING, type));
        }
    }

    public static LineString of(GeoJsonType type, List<Position> coordinates) {
        if (type != GeoJsonType.LINE_STRING) {
            throw new IllegalArgumentException(String.format("Invalid type. expected[%s] got:[%s]",
                    GeoJsonType.LINE_STRING, type));
        }
        return new LineString(coordinates);
    }

    public static LineString of(List<Position> coordinates) {
        return of(GeoJsonType.LINE_STRING, coordinates);
    }
}
