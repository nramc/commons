package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public final class GeometryCollection extends Geometry {
    private final List<Geometry> geometries;

    public GeometryCollection(List<Geometry> geometries) {
        super(GeoJsonType.GEOMETRY_COLLECTION);
        this.geometries = List.copyOf(geometries);
    }

    @JsonCreator
    public GeometryCollection(GeoJsonType type, List<Geometry> geometries) {
        this(geometries);

        if (type != GeoJsonType.GEOMETRY_COLLECTION) {
            throw new IllegalArgumentException("Invalid type. expected 'GeometryCollection', but got " + type);
        }
        if (CollectionUtils.isNotEmpty(geometries) && geometries.stream().anyMatch(geometry -> geometry.type == GeoJsonType.GEOMETRY_COLLECTION)) {
            throw new IllegalArgumentException("Invalid type. nested 'GeometryCollection' not allowed");
        }
    }

    public static GeometryCollection of(String type, List<Geometry> geometries) {

        if (!Objects.equals(type, GeoJsonType.GEOMETRY_COLLECTION.getType())) {
            throw new IllegalArgumentException("Invalid type. expected 'GeometryCollection', but got " + type);
        }

        if (CollectionUtils.isNotEmpty(geometries) && geometries.stream().anyMatch(geometry -> geometry.type == GeoJsonType.GEOMETRY_COLLECTION)) {
            throw new IllegalArgumentException("Invalid type. nested 'GeometryCollection' not allowed");
        }

        return new GeometryCollection(geometries);
    }
}
