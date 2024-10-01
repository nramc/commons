[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/nramc/commons/ci-build-workflow.yml?branch=main&style=flat&logoColor=ff0)](https://github.com/nramc/commons/actions/workflows/ci-build-workflow.yml)
[![Release](https://img.shields.io/github/release/nramc/commons.svg?style=for-the-badge?logoColor=fff&style=flat)](https://github.com/nramc/commons/releases)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.nramc.commons/commons-geojson?style=flat&logo=apachemaven&logoColor=%23C71A36&labelColor=rgba(204%2C%20204%2C%20204%2C%200.8)&color=%2303C75A)](https://mvnrepository.com/artifact/io.github.nramc.commons/commons-geojson)
[![Badge](https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=159&style=flat)](https://www.linkedin.com/in/ramachandran-nellaiyappan/)

# Commons GeoJson

[Home](../README.md)

A robust and standards-compliant GeoJSON domain model for Java developers, following
the [RFC-7946](https://datatracker.ietf.org/doc/html/rfc7946) specification.

<hr />

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)
- [Credits](#credits)
- [Contact](#contact)

## Introduction

This project provides a Java-based domain model for working with GeoJSON data, ensuring compliance with RFC 7946.
GeoJSON is a format for encoding a variety of geographic data structures using JSON. This domain model simplifies the
use of GeoJSON in Java applications by providing a strong, object-oriented approach to geospatial data.

The domain model supports GeoJSON geometry types, including:

- Point
- LineString
- Polygon
- MultiPoint
- MultiLineString
- MultiPolygon
- GeometryCollection
- Feature
- FeatureCollection

Additionally, it includes methods for validation, serialization, and conversion between GeoJSON and other geospatial
formats, while ensuring full compliance with [RFC 7946](https://datatracker.ietf.org/doc/html/rfc7946).

## Features

- RFC 7946 Compliance: Fully adheres to the [RFC 7946](https://datatracker.ietf.org/doc/html/rfc7946) GeoJSON standard.
- Geometry Types: Supports all GeoJSON geometry types: Point, LineString, Polygon, and collections.
- Serialization/Deserialization: Easily convert between Java objects and GeoJSON format using Jackson.
- Validation: Built-in validation to ensure that GeoJSON data is well-formed and complies with the specification.
- Extensible: The model is designed to be extensible for custom use cases and geospatial operations.

## Installation

To include this GeoJSON domain model in your Java project, follow these instructions:

### Requirements

- Java 8 or later
- Maven or Gradle as the build system

### Maven Installation

If you're using Maven, add the following dependency to your pom.xml file:

```xml

<dependency>
    <groupId>io.github.nramc.commons</groupId>
    <artifactId>commons-geojson</artifactId>
    <version>${commons-geojson.version}</version>
</dependency>

```

### Gradle Installation

For Gradle, add this line to your build.gradle file:

```groovy
implementation 'io.github.nramc.commons:commons-geojson:1.0.0'

```

Then, run mvn install or gradle build to download the dependencies.

## Usage

Once the library is installed, you can start working with the GeoJSON domain model in your Java application.

### Serialization

```java
import com.github.nramc.commons.geojson.domain.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoJsonExample {
    public static void main(String[] args) throws Exception {
        // Create a Point object with longitude & latitude
        Point point = Point.of(60.8, 20.5);

        // Serialize the Point object to GeoJSON format
        ObjectMapper mapper = new ObjectMapper();
        String geoJson = mapper.writeValueAsString(point);

        // Print GeoJSON
        System.out.println(geoJson);
    }
}

```

### Deserialization

```java
import com.github.nramc.commons.geojson.domain.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoJsonExample {
    public static void main(String[] args) throws Exception {
        // Create a Point object with longitude & latitude
        String geoJson = """
                { "type": "Point", "coordinates": [60.8, 20.5] }""";

        // Deserialize the GeoJSON string to Point object
        ObjectMapper mapper = new ObjectMapper();
        Point point = mapper.readValue(geoJson, Point.class);

        // Print Coordinates
        System.out.println("long: %f lat:%f", point.getCoordinates().getLongitude(), point.getCoordinates().getLatitude());
    }
}

```

## Contributing

Any contributions you make are greatly appreciated.

If you like the project and have a suggestion that would make this better, please fork the repo and create a pull
request. You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'feat: Add the AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

## License

This project is licensed under the MIT License

## Credits

Sincere Thanks to following open source community for their wonderful efforts to make our life much easier.

- [Spring IO](https://spring.io/) - Java Web Framework
- [Spring Rest Doc](https://springdoc.org) - Spring REST Doc with Open API support for Swagger UI
- [OpenRewrite](https://docs.openrewrite.org/) - Automated source code refactoring
- [Docker](https://www.docker.com/) - Containerization
- [Testcontainers](https://testcontainers.com/) - Run containers on-demand for development and testing

## Contact

Ramachandran
Nellaiyappan [Website](https://github.com/nramc) | [Twitter](https://twitter.com/ram_n_74) | [E-Mail](mailto:ramachandrannellai@gmail.com)

## Show your support

Give a ⭐️ if you like this project!
