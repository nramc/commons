package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class Polygon extends Geometry {
    private PolygonCoordinates coordinates;

    public Polygon(final PolygonCoordinates coordinates) {
        super(GeoJsonType.POLYGON.getTypeValue());
        this.coordinates = coordinates;
    }

    @JsonCreator
    public Polygon(String type, List<List<Position>> coordinates) {
        this(PolygonCoordinates.of(coordinates));
        if (!Objects.equals(type, GeoJsonType.POLYGON.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. 'Polygon' expected, but got " + type);
        }
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new IllegalArgumentException("Invalid Coordinates. Mandatory one position required.");
        }
    }

    public static Polygon of(final List<Position> exterior, final List<List<Position>> holes) {
        return new Polygon(new PolygonCoordinates(exterior, holes));
    }

    @SafeVarargs
    public final Polygon of(final List<Position> exterior, final List<Position>... holes) {
        return new Polygon(new PolygonCoordinates(exterior, List.of(holes)));
    }

    public static Polygon of(PolygonCoordinates coordinates) {
        return new Polygon(coordinates);
    }

    public static Polygon of(GeoJsonType type, List<List<Position>> coordinates) {
        if (type != GeoJsonType.POLYGON) {
            throw new IllegalArgumentException("Invalid type. 'Polygon' expected, but got " + type);
        }
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new IllegalArgumentException("Invalid Coordinates. Mandatory one position required.");
        }
        return new Polygon(PolygonCoordinates.of(coordinates));
    }

}
