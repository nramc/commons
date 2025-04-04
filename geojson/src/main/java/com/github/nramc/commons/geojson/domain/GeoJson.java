package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


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
public abstract class GeoJson implements Serializable {
    protected String type;

    protected GeoJson(String type) {
        this.type = type;
    }

    protected String getType() {
        return type;
    }


}
