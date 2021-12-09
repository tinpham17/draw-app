package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RectangleTest {
    private static final PrintStream normalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(normalOut);
    }

    @Mock
    private Canvas canvas;

    @Test
    void drawValid() {
        Rectangle rectangle = new Rectangle(new Point(2, 5), new Point(7, 9), 'x');
        spy(rectangle).draw(null);
        verify(canvas, times(10)).set(eq(new Point(anyInt(), anyInt())), anyChar());
    }

    @ParameterizedTest
    @CsvSource({"", "9,3,4,7", "4,5,4,6", "7,8,9,8"})
    void drawInvalid(int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(new Point(x1, y1), new Point(x2, y2), 'x');
        spy(rectangle).draw(canvas);
        verify(canvas, never()).set(eq(new Point(anyInt(), anyInt())), anyChar());
    }
}
