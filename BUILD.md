# Build

## Pre-requisite

* Java 21

## Testing

Run the following command to test the application:

```
gradlew clean check
```

> The preceding command must be run from the location where you've cloned the repository.

## Building

Run the following command to build the application:

```
gradlew build
```

> The preceding command must be run from the location where you've cloned the repository.

## Packaging

Run the following command to package the application:

```
gradlew jar
```

> The preceding command must be run from the location where you've cloned the repository.
>
> You can find the actual package in the following location:
>
> ```
> build\lib
> ```

## Generating Verification Metadata

Run the following command to generate the `verification-metadata.xml`:

```
gradlew dependencies --write-verification-metadata sha256,pgp
```

> The preceding command must be run from the location where you've cloned the repository.
>
> The generated `verification-metadata.xml` will be located in the `gradle` directory.