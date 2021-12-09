package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class CanvasTest {
    private static final PrintStream normalOut = System.out;
    private static ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUpStreams() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(normalOut);
    }

    private String getOutput() {
        return outputStream.toString().trim().replaceAll("\\r\\n?", "\n");
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "2,0", "-1,-1", "-3,4", "5,-2"})
    void getInstanceWithInvalidParams(int width, int height) {
        assertNull(Canvas.create(width, height));
    }

    @ParameterizedTest
    @CsvSource({"1,1", "4,5"})
    void getInstanceWithValidParams(int width, int height) {
        assertInstanceOf(Canvas.class, Canvas.create(width, height));
    }

    @Test
    void draw() {
        spy(requireNonNull(Canvas.create(1, 1))).output();
        assertEquals("""
                ---
                | |
                ---""".replaceAll("\\r\\n?", "\n"),
                getOutput()
        );

        setUpStreams();

        spy(requireNonNull(Canvas.create(20, 4))).output();
        assertEquals("""
                ----------------------
                |                    |
                |                    |
                |                    |
                |                    |
                ----------------------""".replaceAll("\\r\\n?", "\n"),
                getOutput()
        );
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "1,0", "-1,-1", "-1,1", "1,-1", "4,4", "1,4", "4,1"})
    void getSetValueWithInvalidParams(int x, int y) {
        Canvas canvas = spy(requireNonNull(Canvas.create(2, 2)));
        canvas.set(new Point(x, y), 'x');
        assertNull(canvas.get(new Point(x, y)));
    }

    @ParameterizedTest
    @CsvSource({"1,2", "2,1", "2,2"})
    void getSetValueWithValidParams(int x, int y) {
        Canvas canvas = spy(requireNonNull(Canvas.create(2, 2)));
        canvas.set(new Point(x, y), 'c');
        assertEquals('c', canvas.get(new Point(x, y)));
    }

}
