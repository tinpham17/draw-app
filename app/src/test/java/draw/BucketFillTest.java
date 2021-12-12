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
public class BucketFillTest {
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
    void draw() {
        when(canvas.get(any())).thenReturn(null);
        when(canvas.get(new Point(2,3))).thenReturn(' ', 'x');
        when(canvas.get(new Point(3,3))).thenReturn(' ', 'x');
        when(canvas.get(new Point(4,3))).thenReturn(' ', 'x');
        when(canvas.get(new Point(5,3))).thenReturn(' ', 'x');
        when(canvas.get(new Point(6,3))).thenReturn(' ', 'x');
        when(canvas.get(new Point(3,4))).thenReturn(' ', 'x');
        when(canvas.get(new Point(4,4))).thenReturn(' ', 'x');
        when(canvas.get(new Point(5,4))).thenReturn(' ', 'x');
        when(canvas.get(new Point(6,4))).thenReturn(' ', 'x');
        when(canvas.get(new Point(4,5))).thenReturn(' ', 'x');
        when(canvas.get(new Point(5,5))).thenReturn(' ', 'x');
        when(canvas.get(new Point(6,5))).thenReturn(' ', 'x');
        when(canvas.get(new Point(5,6))).thenReturn(' ', 'x');
        when(canvas.get(new Point(6,6))).thenReturn(' ', 'x');
        BucketFill bucketFill = new BucketFill(new Point(4, 4), 'x');
        spy(bucketFill).draw(canvas);

        verify(canvas, times(1)).set(new Point(2,3), 'x');
        verify(canvas, times(1)).set(new Point(3,3), 'x');
        verify(canvas, times(1)).set(new Point(4,3), 'x');
        verify(canvas, times(1)).set(new Point(5,3), 'x');
        verify(canvas, times(1)).set(new Point(6,3), 'x');
        verify(canvas, times(1)).set(new Point(3,4), 'x');
        verify(canvas, times(1)).set(new Point(4,4), 'x');
        verify(canvas, times(1)).set(new Point(5,4), 'x');
        verify(canvas, times(1)).set(new Point(6,4), 'x');
        verify(canvas, times(1)).set(new Point(4,5), 'x');
        verify(canvas, times(1)).set(new Point(5,5), 'x');
        verify(canvas, times(1)).set(new Point(6,5), 'x');
        verify(canvas, times(1)).set(new Point(5,6), 'x');
        verify(canvas, times(1)).set(new Point(6,6), 'x');

        verify(canvas, never()).set(new Point(7,6), 'x');
        verify(canvas, never()).set(new Point(1,3), 'x');
    }
}