package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Point.class, name = GeoJsonType.Constants.POINT_VALUE),
        @JsonSubTypes.Type(value = MultiPoint.class, name = GeoJsonType.Constants.MULTI_POINT_VALUE),
        @JsonSubTypes.Type(value = LineString.class, name = GeoJsonType.Constants.LINE_STRING_VALUE),
        @JsonSubTypes.Type(value = MultiLineString.class, name = GeoJsonType.Constants.MULTI_LINE_STRING_VALUE),
        @JsonSubTypes.Type(value = Polygon.class, name = GeoJsonType.Constants.POLYGON_VALUE),
        @JsonSubTypes.Type(value = MultiPolygon.class, name = GeoJsonType.Constants.MULTI_POLYGON_VALUE),
        @JsonSubTypes.Type(value = GeometryCollection.class, name = GeoJsonType.Constants.GEOMETRY_COLLECTION_VALUE)
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true)
public abstract class Geometry extends GeoJson {

    protected Geometry(String type) {
        super(type);
    }
}
