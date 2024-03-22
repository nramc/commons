package com.github.nramc.commons.geojson.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainApplicationTest {

    @Test
    void test() {
        Assertions.assertDoesNotThrow(() -> MainApplication.main(null));
    }

}