package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LineTest {

    @BeforeEach
    public void setUpStreams() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Mock
    private Canvas canvas;

    @Test
    void drawUnsupported() {
        Line line = new Line(new Point(1, 2), new Point(3, 4), 'x');
        spy(line).draw(canvas);
        verify(canvas, never()).set(any(), anyChar());
    }

    @Test
    void drawVertically() {
        Line line = new Line(new Point(2, 4), new Point(4, 4), 'x');
        spy(line).draw(canvas);
        verify(canvas, times(1)).set(eq(new Point(2, 4)), anyChar());
    }

    @Test
    void drawHorizontally() {
        Line line = new Line(new Point(2, 4), new Point(4, 4), 'x');
        spy(line).draw(canvas);
        verify(canvas, times(1)).set(eq(new Point(2, 4)), anyChar());
    }
}
