package com.github.nramc.commons.geojson.domain.types;

public enum GeoJsonType {
    POINT,
    MULTI_POINT,
    LINE_STRING,
    MULTI_LINE_STRING,
    POLYGON,
    MULTI_POLYGON,
    GEOMETRY_COLLECTION,
    FEATURE,
    FEATURE_COLLECTION;

    public String getTypeValue() {
        return switch (this) {
            case POINT -> Constants.POINT_VALUE;
            case MULTI_POINT -> Constants.MULTI_POINT_VALUE;
            case LINE_STRING -> Constants.LINE_STRING_VALUE;
            case MULTI_LINE_STRING -> Constants.MULTI_LINE_STRING_VALUE;
            case POLYGON -> Constants.POLYGON_VALUE;
            case MULTI_POLYGON -> Constants.MULTI_POLYGON_VALUE;
            case GEOMETRY_COLLECTION -> Constants.GEOMETRY_COLLECTION_VALUE;
            case FEATURE -> Constants.FEATURE_VALUE;
            case FEATURE_COLLECTION -> Constants.FEATURE_COLLECTION_VALUE;
        };
    }

    public static class Constants {
        private Constants() {
        }

        public static final String POINT_VALUE = "Point";
        public static final String MULTI_POINT_VALUE = "MultiPoint";
        public static final String LINE_STRING_VALUE = "LineString";
        public static final String MULTI_LINE_STRING_VALUE = "MultiLineString";
        public static final String POLYGON_VALUE = "Polygon";
        public static final String MULTI_POLYGON_VALUE = "MultiPolygon";
        public static final String GEOMETRY_COLLECTION_VALUE = "GeometryCollection";
        public static final String FEATURE_VALUE = "Feature";
        public static final String FEATURE_COLLECTION_VALUE = "FeatureCollection";

    }

}
