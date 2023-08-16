# User Guide - Table of Contents

### Commands
* [Invoker Class](CMD_INVOKER_CLASS.md)

  A utility class that invokes an implementation of some standard functional interfaces in java. This is good for a **command pattern** implementation.

* [LogicAND](CMD_LOGIC_AND.md)

  A class that accepts multiple conditions and if **all conditions are true** it will pass control to the truthLogic otherwise the falseLogic will take it.

* [LogicOR](CMD_LOGIC_OR.md)

  A class that accepts multiple conditions and if **at least one condition is true** it will pass control to the truthLogic otherwise the falseLogic will take it.

### Decorator

* [BigDecimalPlus](DEC_BIGDECIMAL_PLUS.md)

  A decorator class for **operating with BigDecimal**.

* [CloseableLock](DEC_CLOSEABLE_LOCK.md)

  **Wrapping the Lock implementation with CloseableLock makes it Closeable** 

* [ListAdder](DEC_LIST_ADDER.md)

  A decorator of List implementation that exposes **conditional add methods** and ability to **add calculated elements**.

* [MapPutter](DEC_MAP_PUTTER.md)

  A decorator of Map implementation that exposes **conditional put methods** and ability to **put calculated elements**.

* [Mutable](DEC_MUTABLE.md)

  A convenience class that **accepts an immutable object and making it possible to be replaced within itself**. This means keeping the reference of this class intact while changing the immutable object it carries.

* [OptionalString](DEC_OPTIONAL_STRING.md)

  A decorator for Optional of type String that gives more string specific simplification.

* [StringBuilderAppender](DEC_SB_APPENDER.md)

  A decorator for StringBuilder that gives you a chance to add **pre-append** and **post-append** logic on every append operation.

* [TextFile](DEC_TEXTFILE.md)

  A decorator class for **operating with TextFile**.

### Functional
* [Sink Interface](FNC_SINK_INTERFACE.md)

  A functional interface that doesn't accept any argument and doesn't return any output.

* [NoOperation Class](FNC_NO_OPERATION.md)

  The class that supplies a do nothing implementation of some of the functional interfaces. This is created so that you don't have to create it yourself.

* [WhenThen Interface](FNC_WHEN_THEN_INTERFACE.md)

  A consumer interface that provides when method that doesn't return a value.

* [WhenThenReturn Iterface](FNC_WHEN_THEN_RETURN_INTERFACE.md)

  A functional interface that provides when method that return a value.

### Handy Tools
* [CommandLocator](HDY_COMMAND_LOCATOR.md)

  A convenience class for **locating a command**.

* [CommandRunner](HDY_COMMAND_RUNNER.md)

  A convenience class for **creating operating system process**.

* [Conditions](HDY_CONDITIONS.md)

  A collection of logic implementations.

* [EndOfLine](HDY_END_OF_LINE.md)

  An enumeration of EndOfLine.

* [LogicMapper](HDY_LOGIC_MAPPER.md)

  Creates a map of conditions and logic where all the conditions that evaluated to true will perform its corresponding logic.

* [NPESilencer](HDY_NPE_SILENCER.md)

  The class that collects expressions and evaluate them in sequence and return the output of very last expression. 

* [OSType](HDY_OSTYPE.md)

  An enumeration of OSes.

* [PathFinder](HDY_PATH_FINDER.md)

  The PathFinder class find the first existence of the file based on the supplied possible locations. 

  Optionally, if being used to process the file as InputStream, it provides option to retrieve the file from ClassLoader.

* [RegExMatcher](HDY_REGEX_MATCHER.md)

  A handy class to match a RegEx pattern from a text.

* [Require](HDY_REQUIRE_CLASS.md)

  A handy class for requiring some objects.

* [BooleanSupplierKeyedMapExecutor](HDY_BOOLEAN_KEY_MAP_EXEC.md)

  A group of logics that are contained in a map and **doesn't return a value**.

* [BooleanSupplierKeyedMapGenerator](HDY_BOOLEAN_KEY_MAP_GEN.md)

  A group of logics that are contained in a map and **returns a value**. The key must be an implementation of **BooleanSupplier**.

* [StringKeyedMapExecutor](HDY_STR_KEY_MAP_EXEC.md)

  A group of logics that are contained in a map and **doesn't return a value**. The key must be a valid **String**.

* [StringKeyedMapFactory (Deprecated)](HDY_STR_KEY_MAP_FACTORY.md)

  A group of logics that are contained in a map and **returns a value**.

  This is a deprecated class, use [StringKeyedMapGenerator](HDY_STR_KEY_MAP_GEN) instead

* [StringKeyedMapGenerator](HDY_STR_KEY_MAP_GEN.md)

  A group of logics that are contained in a map and **returns a value**. The key must be a valid **String**.

