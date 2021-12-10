/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedConstruction;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class AppTest {
    private static ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUpStreams() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    private void setInput(List<String> input) {
        String format = input.stream().collect(Collectors.joining(System.lineSeparator()));
        format = String.format("%s%s", format, System.lineSeparator());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(format.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
    }

    private String getOutput() {
        return outputStream.toString().trim().replaceAll("\\r\\n?", "\n");
    }

    @ParameterizedTest()
    @ValueSource(strings = {"c 20 4", "c 20 4"})
    void createCanvas(String cmd) {
        try (MockedConstruction<Canvas> mocked = mockConstruction(Canvas.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            Assertions.assertEquals(1, mocked.constructed().size());
            Assertions.assertNotNull(mocked.constructed().get(0));
            verify(mocked.constructed().get(0), times(1)).output();
        }
    }
}
