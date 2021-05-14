# Trivial Chunk

A library of trivial codes.

## Requires

* Java 11

## Usage

1. Add the following **maven** dependency to your project:

   | Property    | Value              |
   | ----------- | ------------------ |
   | Group ID    | xyz.ronella.casual |
   | Artifact ID | trivial-chunk      |
   | Version     | 2.3.0              |

   > Using gradle, this can be added as a dependency entry like the following:
   >
   > ```groovy
   > compile group: 'xyz.ronella.casual', name: 'trivial-chunk', version: '2.3.0'
   > ```
   >
   > 

2. Include the following to your **module-info.java**:

   ```java
   requires xyz.ronella.trivial.command;
   requires xyz.ronella.trivial.command.logic;
   requires xyz.ronella.trivial.decorator;
   requires xyz.ronella.trivial.functional;
   requires xyz.ronella.trivial.handy;
   ```

## [User Guide](docs/USER_GUIDE_TOC.md)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb