# Changelog

## 2.13.1 : 2022-08-24

### Changed

* Update the variants of startProcess  to return an instance of Process.

## 2.13.0 : 2022-08-23 *(Do not use)*

### Added

* Update the CommandRunner to have access to the Process object via the startProcess method.

## 2.12.0 : 2022-08-16

### Added

* Introduce OptionalString decorator.

## 2.11.0 : 2022-06-20

### Added

* Introduce IMatcherConfig to be used with RegExMatcher.
* More NoOperation methods.

## 2.10.0 : 2022-06-03

### Added

* Introduce ICommandArray to be used along side CommandRunner.
* Add ListAdder implementation.
* Usage of PMD upon testing.

## 2.9.0 : 2022-05-20

### Added

* Add CloseableLock implementation.
* Add Mutable implementation.

## 2.8.0 : 2022-05-18

### Added

* Add replace method to StringBuilderAppender. 

## 2.7.0 : 2022-05-18

### Added

* Add clear method to StringBuilderAppender. 

## 2.6.0 : 2022-05-17

### Added

* The find and match methods are added in RegExMatcher. 

### Changed

* Deprecate matchByRegEx methods that requires you to use casting to some of the variants.

## 2.5.1 : 2022-05-12

### Changed

* Fix the publishing error on 2.5.0.

## 2.5.0 : 2022-05-10 *(Publishing error)*

### Added

* CommandRunner implementation.
* RegExMatcher implementation.

### Changed

* Deprecated Require.all method in favor or Require.objects method.

## 2.4.0 : 2021-12-13

### Added

* BooleanSupplierKeyedMapExecutor and BooleanSupplierKeyedMapFactory logic mapper.

## 2.3.0 : 2021-05-14

### Added

* NPESilencer implementation.
* Require implementation

## 2.2.0 : 2021-05-09

### Added

* NoOperation implementation of some core functional interfaces.
* Sink interface now has default drainTo method.
* StringKeyedMapExecutor and StringKeyedMapFactory logic mapper.

## 2.1.0 : 2021-04-28

### Added

* StringBuilderAppender can now accepts String in the constructor.
* StringBuilderAppender have a default internal StringBuilder if neither StringBuilder or String was passed in the contructor.
* StringBuilderAppender can append now multiple texts in one method.

## 2.0.0 : 2021-04-26

### Added

* toString method to StringBuilderAppender.
* addInlineLogic method to LogicMapper.
* LogicAND and LogicOR now supports defaultTruthLogic and defaultFalseLogic via constructor.
* StringBuilderAppender now support conditional append.
* StringBuilderAppender now support custom logic for append.

### Changed

* Supplier<Boolean> was replaced with BooleanSupplier.
* Sink is now in the xyz.ronella.trivial.functional package.
* LogicAND and LogicOR are now in xyz.ronella.trivial.command.logic package.
* LogicAND and LogicOR are now related by ILogical interface.

## 1.3.0 : 2021-04-22

### Added

* LogicMapper now has initial and final logic.

## 1.2.0 : 2021-04-21

### Added

* OSType enum

## 1.1.0 : 2019-12-01

### Added

* LogicOR and LogicAND commands.
* Handy Conditions.
* Handy LogicMapper.
* StringBuilderAppender decorator.

## 1.0.0 : 2019-11-29

### Initial Version

