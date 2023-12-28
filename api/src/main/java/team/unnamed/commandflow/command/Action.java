package team.unnamed.commandflow.command;

import team.unnamed.commandflow.CommandContext;
import team.unnamed.commandflow.CommandManager;
import team.unnamed.commandflow.exception.CommandException;

public interface Action {

    /**
     * A NOP default Action used for {@link Command} instances which action isn't defined
     */
    Action NULL_ACTION = context -> false;

    /**
     * The action to execute when the {@link Command} is being called/run by the {@link CommandManager}.
     *
     * @param context The {@link CommandContext} for this executed command.
     * @return If the action was executed sucessfully or not, if the value is false, the usage will be send to the source.
     * @throws CommandException If an error occurs while executing the action.
     */
    boolean execute(CommandContext context) throws CommandException;

}
