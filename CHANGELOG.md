# Changelog

## 3.3.0 : 2025-12-17

### Change

* Update the `ValueHunter` to allow the same instance to be used to find different targets.

## 3.2.0 : 2024-12-16

### New

* Introduce `ValueHunter` class to hunt for the first non-null value from various sources.
* `PathFinder` can now accept custom finder implementation. 

### Change

* Prefer the usage of Optional.orElseGet method instead of Optional.orElse method for lazy evaluation.

## 3.1.0 : 2024-11-30

### New

* Update `OSType` to have the following:
  * Now recognize UNIX and Solaris.
  * Each type can now test if is POSIX compliant.
  * Each type can now provide the command for finding command via PATH environment variable.
* The `OptionalString` can now return the instance of the wrapped `Optional<String>`.

## 3.0.0 : 2024-11-22

### New

* Java 21 is now the source and target version.
* `PathFinder` now support environment variable and system parameters.
* `Require` now has method specific for requiring single object.

### Change

* Make methods more defensive.
* Remove deprecated classes and methods.
* Remove classes with no practical use cases:
  * `Conditions`
  * `NPESilencer`
  * `LogicMapper`
  * `Invoker`
  * `ILogical`
  * `AbstractLogical`
  * `LogicAND`
  * `LogicOR`
* `OSType` now has all caps fields:
  * `WINDOWS`
  * `LINUX`
  * `MAC`
  * `UNKNOWN`
* Rename `RequireAllException` to `ObjectRequiredException`

## 2.22.0 : 2024-11-16

### New

* Add `CommandArray.wrap(List<String> command)`

## 2.21.0 : 2024-11-14

### New

* Checked functional interfaces.
* Update the no operation classes to include checked functional interfaces.

## 2.20.0 : 2024-06-25

### New

* Update on `TextFile` class with the following:
  * Update the constructor allow initialization of encoding and line ending.
  * Add the `setText` method.
  * `getEndOfLine` considers encoding.

* `OSType` to support identifying the OS based on `EndOfLine`.
* Add `StringBuilderDelim` implementation to support `StringBuilderAppender`.

## 2.19.0 : 2024-06-14

### New

* Add default constructor to `Mutable`.
* Add `FileNomen` to extract file name and extension.
* Add `OSType.of()` method to get the `OSType` based on the name provided.
* Add `OSType` to provide the application data directory.
* Add `EndOfLine` can now be provided by the `TextFile`.

## 2.18.0 : 2024-04-09

### New

* Implement `CommandProcessor`.
* Add `CommandArray.wrap` method the can convert a string command to and instance of `ICommandArray`.

### Change

* Deprecate `CommandRunner` in favor of `CommandProcessor`.

## 2.17.1 : 2023-01-24

### Change

* Make the behavior of `appendWhen(String)` the same as the others.

## 2.17.0 : 2023-12-23

### New

* Now requires java 17.
* New `Require.objects` method that support custom error message using `RequireObject` record.

## 2.16.0 : 2023-08-14

### New

* Add `BigDecimalPlus` implementation.
* Add `WhenThen` and `WhenThenReturn` functional interfaces.
* Add `MapPutter` implementation.
* Add `TextFile` implementation.
* Add `CommandLocator` implementation.
* Add `EndOfLine` enumeration.

### Change

* Update `ListAdders` and `StringBuilderAppender` to use `WhenThen` and `WhenThenReturn` interfaces.

## 2.15.0 : 2023-03-24

### New

* Add `getInputStream` to `PathFinder`.

## 2.14.0 : 2022-08-29

### New

* Introduce a handy `PathFinder`.

## 2.13.1 : 2022-08-24

### Change

* Update the variants of `startProcess`  to return an instance of `Process`.

## 2.13.0 : 2022-08-23 *(Do not use)*

### New

* Update the `CommandRunner` to have access to the `Process` object via the `startProcess` method.

## 2.12.0 : 2022-08-16

### New

* Introduce `OptionalString` decorator.

## 2.11.0 : 2022-06-20

### New

* Introduce `IMatcherConfig` to be used with `RegExMatcher`.
* More `NoOperation` methods.

## 2.10.0 : 2022-06-03

### New

* Introduce `ICommandArray` to be used along side `CommandRunner`.
* Add `ListAdder` implementation.
* Usage of PMD upon testing.

## 2.9.0 : 2022-05-20

### New

* Add `CloseableLock` implementation.
* Add `Mutable` implementation.

## 2.8.0 : 2022-05-18

### New

* Add `replace` method to `StringBuilderAppender`.

## 2.7.0 : 2022-05-18

### New

* Add `clear` method to `StringBuilderAppender`.

## 2.6.0 : 2022-05-17

### New

* The `find` and `match` methods are added in `RegExMatcher`.

### Change

* Deprecate `matchByRegEx` methods that requires you to use casting to some of the variants.

## 2.5.1 : 2022-05-12

### Change

* Fix the publishing error on 2.5.0.

## 2.5.0 : 2022-05-10 *(Publishing error)*

### New

* `CommandRunner` implementation.
* `RegExMatcher` implementation.

### Change

* Deprecated `Require.all` method in favor or `Require.objects` method.

## 2.4.0 : 2021-12-13

### New

* `BooleanSupplierKeyedMapExecutor` and `BooleanSupplierKeyedMapFactory` logic mapper.

## 2.3.0 : 2021-05-14

### New

* `NPESilencer` implementation.
* `Require` implementation

## 2.2.0 : 2021-05-09

### New

* `NoOperation` implementation of some core functional interfaces.
* `Sink` interface now has default `drainTo` method.
* `StringKeyedMapExecutor` and `StringKeyedMapFactory` logic mapper.

## 2.1.0 : 2021-04-28

### New

* `StringBuilderAppender` can now accepts `String` in the constructor.
* `StringBuilderAppender` have a default internal `StringBuilder` if neither `StringBuilder` or `String` was passed in the contructor.
* `StringBuilderAppender` can append now multiple texts in one method.

## 2.0.0 : 2021-04-26

### New

* `toString` method to `StringBuilderAppender`.
* `addInlineLogic` method to `LogicMapper`.
* `LogicAND` and `LogicOR` now supports `defaultTruthLogic` and `defaultFalseLogic` via constructor.
* `StringBuilderAppender` now support conditional append.
* `StringBuilderAppender` now support custom logic for append.

### Change

* `Supplier<Boolean>` was replaced with `BooleanSupplier`.
* `Sink` is now in the `xyz.ronella.trivial.functional` package.
* `LogicAND` and `LogicOR` are now in `xyz.ronella.trivial.command.logic` package.
* `LogicAND` and `LogicOR` are now related by `ILogical` interface.

## 1.3.0 : 2021-04-22

### New

* `LogicMapper` now has initial and final logic.

## 1.2.0 : 2021-04-21

### New

* `OSType` enum

## 1.1.0 : 2019-12-01

### New

* `LogicOR` and `LogicAND` commands.
* Handy `Conditions`.
* Handy `LogicMapper`.
* `StringBuilderAppender` decorator.

## 1.0.0 : 2019-11-29

### Initial Version