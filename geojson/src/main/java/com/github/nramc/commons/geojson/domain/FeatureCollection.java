package com.github.nramc.commons.geojson.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.nramc.commons.geojson.domain.types.GeoJsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(force = true)
public final class FeatureCollection extends GeoJson {
    private List<Feature> features;

    public FeatureCollection(List<Feature> features) {
        super(GeoJsonType.Constants.FEATURE_COLLECTION_VALUE);
        this.features = CollectionUtils.emptyIfNull(features).stream().toList();
    }

    @JsonCreator
    public FeatureCollection(String type, List<Feature> features) {
        this(features);

        if (!Objects.equals(type, GeoJsonType.FEATURE_COLLECTION.getTypeValue())) {
            throw new IllegalArgumentException("Invalid type. expected 'FeatureCollection', but got " + type);
        }
    }

    public static FeatureCollection of(GeoJsonType type, List<Feature> features) {
        if (type != GeoJsonType.FEATURE_COLLECTION) {
            throw new IllegalArgumentException("Invalid type. expected 'FeatureCollection', but got " + type);
        }
        return new FeatureCollection(features);
    }

    public static FeatureCollection of(List<Feature> features) {
        return of(GeoJsonType.FEATURE_COLLECTION, features);
    }

    public static FeatureCollection of(Feature... features) {
        return of(List.of(features));
    }

}
