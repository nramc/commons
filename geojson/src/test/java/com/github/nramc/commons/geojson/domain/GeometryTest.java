package com.github.nramc.commons.geojson.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.GEOMETRY_COLLECTION_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.LINE_STRING_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_LINE_STRING_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_POINT_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_POLYGON_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.POINT_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.POLYGON_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class GeometryTest {
    @Autowired
    private JacksonTester<Geometry> jacksonTester;

    @Test
    void deserialization_withPoint() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/point.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POINT_VALUE))
                .isInstanceOf(Point.class);
    }

    @Test
    void deserialization_withMultiPoint() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-point.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_POINT_VALUE))
                .isInstanceOf(MultiPoint.class);
    }

    @Test
    void deserialization_withLineString() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/line-string.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(LINE_STRING_VALUE))
                .isInstanceOf(LineString.class);
    }

    @Test
    void deserialization_withMultiLineString() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-line-string.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_LINE_STRING_VALUE))
                .isInstanceOf(MultiLineString.class);
    }

    @Test
    void deserialization_withPolygonAndWithoutHoles() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/polygon-without-holes.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POLYGON_VALUE))
                .isInstanceOf(Polygon.class);
    }

    @Test
    void deserialization_withPolygonAndWithHoles() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/polygon-with-holes.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POLYGON_VALUE))
                .isInstanceOf(Polygon.class);
    }

    @Test
    void deserialization_withMultiPolygon() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-polygon.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_POLYGON_VALUE))
                .isInstanceOf(MultiPolygon.class);
    }

    @Test
    void deserialization_withGeometryCollection() throws IOException {
        Geometry geometry = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/geometry-collection.json")));
        assertThat(geometry).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(GEOMETRY_COLLECTION_VALUE))
                .isInstanceOf(GeometryCollection.class);
    }

}