package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class MultiPolygon extends Geometry {
    private List<PolygonCoordinates> coordinates;

    public MultiPolygon(final List<PolygonCoordinates> coordinates) {
        super(GeoJsonType.Constants.MULTI_POLYGON_VALUE);
        this.coordinates = Collections.unmodifiableList(coordinates);
    }

    @JsonCreator
    public MultiPolygon(String type, List<PolygonCoordinates> coordinates) {
        this(coordinates);
        if (!Objects.equals(type, GeoJsonType.MULTI_POLYGON.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. expected 'MultiPolygon', but got " + type);
        }
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new IllegalArgumentException("Invalid coordinates. Minimum one required");
        }
    }

    public static MultiPolygon of(PolygonCoordinates... coordinates) {
        return new MultiPolygon(List.of(coordinates));
    }

    public static MultiPolygon of(GeoJsonType type, List<PolygonCoordinates> coordinates) {
        if (type != GeoJsonType.MULTI_POLYGON) {
            throw new IllegalArgumentException("Invalid type. expected 'MultiPolygon', but got " + type);
        }
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new IllegalArgumentException("Invalid coordinates. Minimum one required");
        }
        return new MultiPolygon(coordinates);
    }


}
