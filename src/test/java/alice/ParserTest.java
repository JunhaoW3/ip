package alice;

import alice.command.*;
import alice.exceptions.AliceException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_listCommand_returnsListCommand() throws AliceException {
        //normal command results in ListCommand
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parse_byeCommand_returnsExitCommand() throws AliceException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }
}
