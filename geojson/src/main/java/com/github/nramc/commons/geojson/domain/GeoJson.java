package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Point.class, name = GeoJsonType.Constants.POINT_VALUE),
        @JsonSubTypes.Type(value = MultiPoint.class, name = GeoJsonType.Constants.MULTI_POINT_VALUE),
        @JsonSubTypes.Type(value = LineString.class, name = GeoJsonType.Constants.LINE_STRING_VALUE),
        @JsonSubTypes.Type(value = MultiLineString.class, name = GeoJsonType.Constants.MULTI_LINE_STRING_VALUE),
        @JsonSubTypes.Type(value = Polygon.class, name = GeoJsonType.Constants.POLYGON_VALUE),
        @JsonSubTypes.Type(value = MultiPolygon.class, name = GeoJsonType.Constants.MULTI_POLYGON_VALUE),
        @JsonSubTypes.Type(value = GeometryCollection.class, name = GeoJsonType.Constants.GEOMETRY_COLLECTION_VALUE),
        @JsonSubTypes.Type(value = Feature.class, name = GeoJsonType.Constants.FEATURE_VALUE),
        @JsonSubTypes.Type(value = FeatureCollection.class, name = GeoJsonType.Constants.FEATURE_COLLECTION_VALUE)
})
@Data
@NoArgsConstructor(force = true)
public abstract sealed class GeoJson implements Serializable permits Feature, FeatureCollection, Geometry {
    protected GeoJsonType type;

    protected GeoJson(GeoJsonType type) {
        this.type = type;
    }


    public final String getType() {
        return Objects.requireNonNull(type).getType();
    }

}
