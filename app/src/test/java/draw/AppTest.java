package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    private static final InputStream normalIn = System.in;
    private static final PrintStream normalOut = System.out;
    private static ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUpStreams() {
        setOutput();
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(normalIn);
        System.setOut(normalOut);
    }

    private void setInput(List<String> input) {
        String format = input.stream().collect(Collectors.joining(System.lineSeparator()));
        format = String.format("%s%s", format, System.lineSeparator());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(format.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
    }

    private void setOutput() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private String getOutput() {
        return outputStream.toString().trim().replaceAll("\\r\\n?", "\n");
    }

    @ParameterizedTest()
    @ValueSource(strings = {"c 5 10", "c 10 5"})
    void shouldCreateCanvas(String cmd) {
        try (MockedConstruction<Canvas> mocked = mockConstruction(
                Canvas.class,
                (mock, context) -> when(mock.output()).thenReturn(new Character[][]{}))
        ) {
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            assertEquals(1, mocked.constructed().size());
            Canvas canvas = mocked.constructed().get(0);
            assertNotNull(canvas);
            verify(canvas, times(1)).output();
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"c", "c 1", "c x", "c x y", "c 0 4", "c 4 0"})
    void shouldNotCreateCanvas(String cmd) {
        try (MockedConstruction<Canvas> mocked = mockConstruction(
                Canvas.class,
                (mock, context) -> when(mock.output()).thenReturn(new Character[][]{})
        )) {
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            assertEquals(0, mocked.constructed().size());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"l 3 2 3 4"})
    void shouldCreateLine(String cmd) {
        try (MockedConstruction<Line> mocked = mockConstruction(Line.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            Line line = mocked.constructed().get(0);
            assertNotNull(line);
            verify(line, times(1)).draw(any());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"l 1", "l 1 2", "l 1 2 3"})
    void shouldNotCreateLine(String cmd) {
        try (MockedConstruction<Line> mocked = mockConstruction(Line.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            assertEquals(0, mocked.constructed().size());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"r 2 4 5 9"})
    void shouldCreateRectangle(String cmd) {
        try (MockedConstruction<Rectangle> mocked = mockConstruction(Rectangle.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            Rectangle rectangle = mocked.constructed().get(0);
            assertNotNull(rectangle);
            verify(rectangle, times(1)).draw(any());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"r 1", "r 1 2", "r 1 2 3"})
    void shouldNotCreateRectangle(String cmd) {
        try (MockedConstruction<Rectangle> mocked = mockConstruction(Rectangle.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            assertEquals(0, mocked.constructed().size());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"b 5 6 x", "b 1 3 o"})
    void shouldCreateBucketFill(String cmd) {
        try (MockedConstruction<BucketFill> mocked = mockConstruction(BucketFill.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            BucketFill bucketFill = mocked.constructed().get(0);
            assertNotNull(bucketFill);
            verify(bucketFill, times(1)).draw(any());
        }
    }

    @ParameterizedTest()
    @ValueSource(strings = {"b 1", "b x y"})
    void shouldNotCreateBucketFill(String cmd) {
        try (MockedConstruction<BucketFill> mocked = mockConstruction(BucketFill.class)){
            List<String> commands = List.of(cmd, "q");
            setInput(commands);
            App.main(new String[]{""});
            assertEquals(0, mocked.constructed().size());
        }
    }

    @Test()
    void shouldRenderCorrectly() {
        List<String> commands = List.of(
                "c 8 5",
                "l 1 2 3 2",
                "r 3 3 6 5",
                "b 1 4",
                "q"
        );
        setInput(commands);
        App.main(new String[]{""});
        assertEquals("""
                        enter command:
                        ----------
                        |        |
                        |        |
                        |        |
                        |        |
                        |        |
                        ----------
                        enter command:
                        ----------
                        |        |
                        |xxx     |
                        |        |
                        |        |
                        |        |
                        ----------
                        enter command:
                        ----------
                        |        |
                        |xxx     |
                        |  xxxx  |
                        |  x  x  |
                        |  xxxx  |
                        ----------
                        enter command:
                        ----------
                        |        |
                        |xxx     |
                        |xxxxxx  |
                        |xxx  x  |
                        |xxxxxx  |
                        ----------
                        enter command:
                        exited""".replaceAll("\\r\\n?", "\n"),
                getOutput());
    }
}
