package com.github.nramc.commons.geojson.domain.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class GeoJsonTypeTest {

    @ParameterizedTest
    @EnumSource(GeoJsonType.class)
    void getType(GeoJsonType geoJsonType) {
        Assertions.assertNotNull(geoJsonType);
    }
}