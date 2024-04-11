package com.github.nramc.commons.geojson.domain;

import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.nramc.commons.geojson.domain.types.GeoJsonType.Constants.POLYGON_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@JsonTest
class FeatureTest {
    @Autowired
    private JacksonTester<Feature> jacksonTester;

    @Test
    void deserialization() throws IOException {
        Feature object = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/feature.json")));
        assertThat(object).isNotNull()
                .satisfies(feature -> assertThat(feature.getType()).isEqualTo(GeoJsonType.Constants.FEATURE_VALUE))
                .satisfies(feature -> assertThat(feature.getId()).isEqualTo("ID_001"))
                .satisfies(feature -> assertThat(feature.getGeometry()).extracting(GeoJson::getType).isEqualTo(POLYGON_VALUE))
                .satisfies(feature -> assertThat(feature.getProperties()).contains(entry("name", "Olympic Park")))
                .satisfies(feature -> assertThat(feature.getProperties()).contains(entry("size", 85)));
    }

    @Test
    void deserialization_withoutId() throws IOException {
        Feature object = jacksonTester.parseObject(Files.readString(Path.of("src/test/resources/data/feature-without-id.json")));
        assertThat(object).isNotNull()
                .satisfies(feature -> assertThat(feature.getType()).isEqualTo(GeoJsonType.Constants.FEATURE_VALUE))
                .satisfies(feature -> assertThat(feature.getId()).isNullOrEmpty())
                .satisfies(feature -> assertThat(feature.getGeometry()).extracting(GeoJson::getType).isEqualTo(POLYGON_VALUE))
                .satisfies(feature -> assertThat(feature.getProperties()).contains(entry("name", "Olympic Park")))
                .satisfies(feature -> assertThat(feature.getProperties()).contains(entry("size", 85)));
    }

}