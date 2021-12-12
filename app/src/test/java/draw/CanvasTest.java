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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CanvasTest {
    @BeforeEach
    public void setUpStreams() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @ParameterizedTest
    @CsvSource({"0, 400", "-10, 400", "400, 0", "400, -10"})
    void createInvalidCanvas(int width, int height) {
        Canvas canvas = Canvas.create(width, height);
        assertNull(canvas);
    }

    @ParameterizedTest
    @CsvSource({"300, 400", "400, 300"})
    void createValidCanvas(int width, int height) {
        Canvas canvas = Canvas.create(width, height);
        assertNotNull(canvas);
    }

    @Test
    void setInsideCanvas() {
        Canvas canvas = Canvas.create(5, 10);
        assertNotNull(canvas);
        Point point = new Point(3, 5);
        Character color = 'x';
        canvas.set(point, color);
        assertEquals(canvas.get(point), color);
    }

    @Test
    void setOutsideCanvas() {
        Canvas canvas = Canvas.create(5, 10);
        assertNotNull(canvas);
        Point point = new Point(12, 12);
        Character color = 'x';
        canvas.set(point, color);
        assertNull(canvas.get(point));
    }

    @Test
    void getCanvasOutput() {
        Canvas canvas = Canvas.create(3, 5);
        assertNotNull(canvas);
        Character color = 'x';
        canvas.set(new Point(2, 3), 'x');
        canvas.set(new Point(1, 4), 'o');
        canvas.set(new Point(10, 10), color);
        Character[][] expected = new Character[][]{
                {'-', '-', '-', '-', '-'},
                {'|', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', '|'},
                {'|', ' ', 'x', ' ', '|'},
                {'|', 'o', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', '|'},
                {'-', '-', '-', '-', '-'},
        };
        Character[][] actual = canvas.output();
        for (int row = 0; row < actual.length; row++) {
            for (int col = 0; col < actual[row].length; col++) {
                assertEquals(actual[row][col], expected[row][col]);
            }
        }
    }
}
