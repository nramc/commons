package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.GEOMETRY_COLLECTION_VALUE;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class GeometryCollection extends Geometry {
    private List<Geometry> geometries;

    public GeometryCollection(List<Geometry> geometries) {
        super(GEOMETRY_COLLECTION_VALUE);
        this.geometries = List.copyOf(geometries);
    }

    @JsonCreator
    public GeometryCollection(String type, List<Geometry> geometries) {
        this(geometries);

        if (!Objects.equals(type, GeoJsonType.GEOMETRY_COLLECTION.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. expected 'GeometryCollection', but got " + type);
        }
        if (CollectionUtils.isNotEmpty(geometries) &&
                geometries.stream().anyMatch(geometry -> Objects.equals(geometry.type, GEOMETRY_COLLECTION_VALUE))) {
            throw new IllegalArgumentException("Invalid type. nested 'GeometryCollection' not allowed");
        }
    }

    public static GeometryCollection of(String type, List<Geometry> geometries) {

        if (!Objects.equals(type, GEOMETRY_COLLECTION_VALUE)) {
            throw new IllegalArgumentException("Invalid type. expected 'GeometryCollection', but got " + type);
        }

        if (CollectionUtils.isNotEmpty(geometries) &&
                geometries.stream().anyMatch(geometry -> Objects.equals(geometry.type, GEOMETRY_COLLECTION_VALUE))) {
            throw new IllegalArgumentException("Invalid type. nested 'GeometryCollection' not allowed");
        }

        return new GeometryCollection(geometries);
    }
}
