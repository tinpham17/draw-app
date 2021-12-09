package draw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BucketFillTest {

    private final static Character BLANK = ' ';
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

    @ParameterizedTest
    @ValueSource(chars = {'o', 'c'})
    void draw(Character character) {
        when(canvas.get(eq(new Point(anyInt(), anyInt())))).thenReturn(character);
        when(canvas.get(eq(new Point(intThat(x-> x == 3), intThat(y -> y == 5))))).thenReturn(BLANK, character);
        // when(canvas.get(new Point(intThat(x-> x == 4), intThat(y -> y == 5)))).thenReturn(BLANK, character);
        // when(canvas.get(new Point(intThat(x-> x == 5), intThat(y -> y == 5)))).thenReturn(BLANK, character);
        // when(canvas.get(new Point(intThat(x-> x == 3), intThat(y -> y == 6)))).thenReturn(BLANK, character);
        // when(canvas.get(new Point(intThat(x-> x == 4), intThat(y -> y == 6)))).thenReturn(BLANK, character);
        // when(canvas.get(new Point(intThat(x-> x == 5), intThat(y -> y == 6)))).thenReturn(BLANK, character);
        BucketFill bucketFill = new BucketFill(new Point(4, 5), character);
        spy(bucketFill).draw(canvas);
        // verify(canvas, times(6)).set(eq(new Point(anyInt(), anyInt())), anyChar());
    }
}