package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
public class Point extends Geometry {
    private Position coordinates;

    public Point(Position coordinates) {
        super(GeoJsonType.POINT.getTypeValue());
        if (coordinates == null) {
            throw new IllegalArgumentException("Invalid coordinates. 'coordinates' can not be empty or null");
        }
        this.coordinates = coordinates;
    }

    @JsonCreator
    public Point(String type, Position coordinates) {
        super(GeoJsonType.POINT.getTypeValue());
        if (!Objects.equals(type, GeoJsonType.POINT.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. 'Point' expected got '" + type + "'");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Invalid coordinates. 'coordinates' can not be empty or null");
        }
        this.coordinates = coordinates;
    }

    public Point(String type, double[] coordinates) {
        this(type, Position.of(coordinates));
    }

    public static Point of(GeoJsonType type, Position coordinates) {
        return new Point(type.getTypeValue(), coordinates);
    }

    public static Point of(Position coordinates) {
        return of(GeoJsonType.POINT, coordinates);
    }

    public static Point of(long longitude, long latitude) {
        return of(GeoJsonType.POINT, Position.of(longitude, latitude));
    }

}
