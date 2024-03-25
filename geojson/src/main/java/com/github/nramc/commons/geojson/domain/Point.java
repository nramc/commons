package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public final class Point extends Geometry {
    private final Position coordinates;

    public Point(Position coordinates) {
        super(GeoJsonType.POINT);
        if (coordinates == null) {
            throw new IllegalArgumentException("Invalid coordinates. 'coordinates' can not be empty or null");
        }
        this.coordinates = coordinates;
    }

    @JsonCreator
    public Point(GeoJsonType type, Position coordinates) {
        super(GeoJsonType.POINT);
        if (type != GeoJsonType.POINT) {
            throw new IllegalArgumentException("Invalid type. 'Point' expected");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Invalid coordinates. 'coordinates' can not be empty or null");
        }
        this.coordinates = coordinates;
    }

    public Point(GeoJsonType type, double[] coordinates) {
        this(type, Position.of(coordinates));
    }

    public static Point of(GeoJsonType type, Position coordinates) {
        return new Point(type, coordinates);
    }

    public static Point of(Position coordinates) {
        return of(GeoJsonType.POINT, coordinates);
    }

    public static Point of(long longitude, long latitude) {
        return of(GeoJsonType.POINT, Position.of(longitude, latitude));
    }

}
