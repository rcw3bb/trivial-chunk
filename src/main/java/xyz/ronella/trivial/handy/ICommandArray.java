package xyz.ronella.trivial.handy;

import java.util.List;

/**
 * The implementation of this interface must hold the logic of generating the command as array.
 *
 * @author Ron Webb
 * @since 2.10.0
 */
public interface ICommandArray {

    /**
     * Must hold the implementation of returning the command as array.
     * @return The command as array.
     */
    String[] getCommand();

    List<String> getCommandAsList();

}
