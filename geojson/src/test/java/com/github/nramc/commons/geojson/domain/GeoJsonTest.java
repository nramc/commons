package com.github.nramc.commons.geojson.domain;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.FEATURE_COLLECTION_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.FEATURE_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.GEOMETRY_COLLECTION_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.LINE_STRING_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_LINE_STRING_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_POINT_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.MULTI_POLYGON_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.POINT_VALUE;
import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.POLYGON_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class GeoJsonTest {
    @Autowired
    private JacksonTester<GeoJson> jacksonTester;

    @Test
    void deserialization_withPoint() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/point.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POINT_VALUE))
                .isInstanceOf(Point.class);
    }

    @Test
    void serialization_withPoint() throws IOException {
        Point geoJson = Point.of(Position.of(100.0, 0.0));
        JsonContent<GeoJson> jsonContent = jacksonTester.write(geoJson);
        assertThat(jsonContent).isNotNull()
                .isEqualToJson(Files.readString(Path.of("src/test/resources/data/point.json")), JSONCompareMode.STRICT);
    }

    @Test
    void deserialization_withMultiPoint() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-point.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_POINT_VALUE))
                .isInstanceOf(MultiPoint.class);
    }

    @Test
    void deserialization_withLineString() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/line-string.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(LINE_STRING_VALUE))
                .isInstanceOf(LineString.class);
    }

    @Test
    void deserialization_withMultiLineString() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-line-string.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_LINE_STRING_VALUE))
                .isInstanceOf(MultiLineString.class);
    }

    @Test
    void deserialization_withPolygonAndWithoutHoles() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/polygon-without-holes.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POLYGON_VALUE))
                .isInstanceOf(Polygon.class);
    }

    @Test
    void deserialization_withPolygonAndWithHoles() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/polygon-with-holes.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(POLYGON_VALUE))
                .isInstanceOf(Polygon.class);
    }

    @Test
    void deserialization_withMultiPolygon() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/multi-polygon.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(MULTI_POLYGON_VALUE))
                .isInstanceOf(MultiPolygon.class);
    }

    @Test
    void deserialization_withGeometryCollection() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/geometry-collection.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(GEOMETRY_COLLECTION_VALUE))
                .isInstanceOf(GeometryCollection.class);
    }

    @Test
    void deserialization_withFeature() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/feature.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(FEATURE_VALUE))
                .isInstanceOf(Feature.class);
    }

    @Test
    void deserialization_withFeatureCollection() throws IOException {
        GeoJson geoJson = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/feature-collection.json")));
        assertThat(geoJson).isNotNull()
                .satisfies(obj -> assertThat(obj.getType()).isEqualTo(FEATURE_COLLECTION_VALUE))
                .isInstanceOf(FeatureCollection.class);
    }

}