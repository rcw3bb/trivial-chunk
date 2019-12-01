# Trivial Chunk

A library of trivial codes.

## Requires

* Java 12

## Usage

1. Add the following **maven** dependency to your project:

   | Property    | Value              |
   | ----------- | ------------------ |
   | Group ID    | xyz.ronella.casual |
   | Artifact ID | trivial-chunk      |
   | Version     | 1.1.0              |

   > Using gradle, this can be added as a dependency entry like the following:
   >
   > ```groovy
   > compile group: 'xyz.ronella.casual', name: 'trivial-chunk', version: '1.1.0'
   > ```

2. Include the following to your **module-info.java**:

   ```java
   requires xyz.ronella.trivial.command;
   ```

## [User Guide](docs\USER_GUIDE_TOC.md)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb
