package me.fixeddev.commandflow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.stack.ArgumentStack;
import me.fixeddev.commandflow.stack.StackSnapshot;
import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ArgumentPart extends CommandPart {
    @Override
    default @Nullable Component getLineRepresentation() {
        return TextComponent.builder("<" + getName() + ">").build();
    }

    default void parse(CommandContext context, ArgumentStack stack, CommandPart caller) throws ArgumentParseException {
        StackSnapshot snapshot = stack.getSnapshot();

        int oldArgumentsLeft = stack.getArgumentsLeft();
        List<? extends Object> value = parseValue(context, stack, caller);

        List<String> rawArgs = new ArrayList<>();
        int usedArguments = oldArgumentsLeft - stack.getArgumentsLeft();

        if (usedArguments != 0) {
            stack.applySnapshot(snapshot);

            for (int i = 0; i < usedArguments; i++) {
                rawArgs.add(stack.next());
            }
        }

        context.setValues(this, value);
        context.setRaw(this, rawArgs);
    }

    // ignored
    @Override
    default void parse(CommandContext context, ArgumentStack stack) throws ArgumentParseException {
    }

    @Override
    default List<String> getSuggestions(CommandContext commandContext, ArgumentStack stack) {
        if (stack.hasNext()) {
            stack.next();
        }

        return Collections.emptyList();
    }

    default List<? extends Object> parseValue(CommandContext context, ArgumentStack stack, CommandPart caller) throws ArgumentParseException {
        return parseValue(context, stack);
    }

    /**
     * @deprecated Should be replaced with {@link ArgumentPart#parseValue(CommandContext, ArgumentStack, CommandPart)}
     */
    @Deprecated
    List<? extends Object> parseValue(CommandContext context, ArgumentStack stack) throws ArgumentParseException;

    Type getType();
}
