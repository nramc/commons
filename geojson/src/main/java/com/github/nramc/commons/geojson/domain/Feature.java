package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.MapUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public non-sealed class Feature extends GeoJson {
    private String id;
    private Geometry geometry;
    private Map<String, Serializable> properties;

    public Feature(String id, Geometry geometry, Map<String, Serializable> properties) {
        super(GeoJsonType.FEATURE.getTypeValue());
        this.id = id;
        this.geometry = geometry;
        this.properties = Map.copyOf(properties);
    }

    @JsonCreator
    public Feature(String id, String type, Geometry geometry, Map<String, Serializable> properties) {
        this(id, geometry, MapUtils.emptyIfNull(properties));
        if (!Objects.equals(type, GeoJsonType.FEATURE.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. expected 'Feature', but got " + type);
        }
    }

    public static Feature of(String id, GeoJsonType type, Geometry geometry, Map<String, Serializable> properties) {
        if (type != GeoJsonType.FEATURE) {
            throw new IllegalArgumentException("Invalid type. expected 'Feature', but got " + type);
        }
        return new Feature(id, geometry, MapUtils.emptyIfNull(properties));
    }

    public static Feature of(String id, Geometry geometry, Map<String, Serializable> properties) {
        return of(id, GeoJsonType.FEATURE, geometry, MapUtils.emptyIfNull(properties));
    }

}
