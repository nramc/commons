package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public non-sealed class Feature extends GeoJson {
    private final String id;
    private final Geometry geometry;
    private final Map<String, Serializable> properties;

    public Feature(String id, Geometry geometry, Map<String, Serializable> properties) {
        super(GeoJsonType.FEATURE);
        this.id = id;
        this.geometry = geometry;
        this.properties = Map.copyOf(properties);
    }

    @JsonCreator
    public Feature(String id, GeoJsonType type, Geometry geometry, Map<String, Serializable> properties) {
        this(id, geometry, MapUtils.emptyIfNull(properties));
        if (type != GeoJsonType.FEATURE) {
            throw new IllegalArgumentException("Invalid type. expected 'Feature', but got " + type);
        }
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Mandatory field 'id' should not be null/blank");
        }
    }

    public static Feature of(String id, GeoJsonType type, Geometry geometry, Map<String, Serializable> properties) {
        if (type != GeoJsonType.FEATURE) {
            throw new IllegalArgumentException("Invalid type. expected 'Feature', but got " + type);
        }
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Mandatory field 'id' should not be null/blank");
        }
        return new Feature(id, geometry, MapUtils.emptyIfNull(properties));
    }

    public static Feature of(String id, Geometry geometry, Map<String, Serializable> properties) {
        return of(id, GeoJsonType.FEATURE, geometry, MapUtils.emptyIfNull(properties));
    }

}
