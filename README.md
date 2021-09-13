# zebra4j - Zebra puzzle generator and solver

[![Build Status](https://app.travis-ci.com/murfffi/zebra4j.svg?branch=main)](https://app.travis-ci.com/murfffi/zebra4j)
[![License](https://img.shields.io/github/license/murfffi/zebra4j)](/LICENSE)
[![Maven Central release](https://maven-badges.herokuapp.com/maven-central/io.github.murfffi/zebra4j/badge.svg)](https://search.maven.org/artifact/io.github.murfffi/zebra4j)
[![public javadoc](https://javadoc.io/badge2/io.github.murfffi/zebra4j/javadoc.svg)](https://javadoc.io/doc/io.github.murfffi/zebra4j)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e85598dea228465188b9e70774983532)](https://www.codacy.com/gh/murfffi/zebra4j/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=murfffi/zebra4j&amp;utm_campaign=Badge_Grade)
[![Docker Pulls](https://img.shields.io/docker/pulls/murfffi/zebracli)](https://hub.docker.com/r/murfffi/zebracli)
[![Open in Visual Studio Code](https://open.vscode.dev/badges/open-in-vscode.svg)](https://open.vscode.dev/murfffi/zebra4j)

## Overview

zebra4j is a generator and solver for [Zebra
puzzles](https://en.wikipedia.org/wiki/Zebra_Puzzle), also known as "logic grid
puzzles". Such libraries are used as backend of interactive games aimed at
children of all ages. [Try it out right in your browser!](https://murfffi.github.io/zebra-apps/demo/)

This library has some unique features, not available in alternative solutions:
- It can describe generated puzzles in multiple languages, not just produce data
  structures. See "Localizing" section for details.
- It supports both puzzles that end with question like "Who owns the zebra?" and
  puzzles that require identifying all attributes of all people.
- zebra4j is available as either 
  [a JAR for Java 8+ applications](https://search.maven.org/artifact/io.github.murfffi/zebra4j/),
  a [JavaScript library](https://github.com/murfffi/zebra-apps), or a native library,
  compiled ahead-of-time with [GraalVM](https://www.graalvm.org/reference-manual/native-image/)
  [coming soon](https://github.com/murfffi/zebra4j/issues/50).
  The native library can be embedded in an app written in another
  language like Python, Go or Rust.

[Demo.java](src/main/java/zebra4j/cli/Demo.java) and unit tests demonstrate how to use
the library in Java. The complete API is searchable in [the javadoc](https://murfffi.github.io/zebra4j/apidocs/).

[SAMPLES.md](SAMPLES.md) contain some sample generated puzzles.

## Download a release

The library is available in the Maven Central. The artifact details are:

- groupId: `io.github.murfffi`
- artifactId: `zebra4j`
- version: [![Maven Central release](https://maven-badges.herokuapp.com/maven-central/io.github.murfffi/zebra4j/badge.svg)](https://search.maven.org/artifact/io.github.murfffi/zebra4j)

With Maven, you can add it as a dependency like this:

```xml
  ...
  <dependencies>
   ...
    <dependency>
      <groupId>io.github.murfffi</groupId>
      <artifactId>zebra4j</artifactId>
      <version>RELEASE</version>
    </dependency>
  ...
```

Maven understands "RELEASE" as latest non-SNAPSHOT version. It is recommended to
replace that with a specific version to prevent getting versions with breaking
changes unintentionally.

## Running locally

You can try a JavaScript build of the library at
<https://murfffi.github.io/zebra-apps/demo/>.

You can also use zebra4j on the command line. The fastest way to do it is with Docker:

```bash
docker run --rm murfffi/zebracli --help
```

The image download is just 14 MB. It is based on a GraalVM native-image build (for amd64 only).

If you don't have Docker locally but have Java, you can download
<https://repo.maven.apache.org/maven2/io/github/murfffi/zebra4j/0.9/zebra4j-0.9-shaded.jar> to
`zebra4j-bundle.jar` and run:

```bash
java -jar zebra4j-bundle.jar --help
```

Unlike the Docker image, the JAR works on any system architecture supported by Java, not just amd64.

## Customizing

The puzzles generated by the library can customized by defining
[Attributes](src/main/java/zebra4j/Attribute.java#L21)
and [Facts](src/main/java/zebra4j/fact/Fact.java#L21).
Attributes are the traits of the people in the puzzle like name or favorite pet,
while Facts are clues that the players get to solve the puzzle. You can select
from the predefined implementations or implement yourself the Java interfaces
with the same names.

`customQuestionPuzzle()` in
[Demo.java](src/main/java/zebra4j/cli/Demo.java)
demonstrates how to select specific types of Attributes and Facts when
generating puzzles.

## Localizing

zebra4j can describe generated puzzles in natural language. Multiple languages
are supported. The built-in attributes and facts can be localized using standard
[Java ResourceBundle](https://docs.oracle.com/javase/tutorial/i18n/resbundle/index.html)
localization. The library comes with English and Bulgarian languages support.
Translations are welcome!

You can also extend and replace the built-in Attributes and Facts, as described
above, to add support for languages that require different sentence structure.

## Install library from source

Requirements:
- Java 8+ JDK with its java executable on the PATH

To install, clone the repository and then run:

```bash
./mvnw install
```

To run a demo:

```bash
./mvnw exec:java
```

To see other CLI options:

```bash
./mvnw exec:java '-Dexec.args=--help'
```

## Submit bugs and feature requests

Please use [GitHub issues](https://github.com/murfffi/zebra4j/issues).

## Logging

The library uses [slf4j-api](http://www.slf4j.org/) as logging API and does not
include or enforce any particular backend.

## Contributing

<!-- https://github.blog/2013-01-31-relative-links-in-markup-files/ -->
See [CONTRIBUTING.md](CONTRIBUTING.md) .
