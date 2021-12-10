package draw;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean exited = false;
        Canvas canvas = null;
        Scanner sc = new Scanner(System.in);
        while (!exited) {
            System.out.println("enter command:");
            String cmd = sc.hasNextLine() ? sc.nextLine() : "";
            String[] cmdArgs = cmd.split(" ");
            try {
                switch (cmdArgs[0].toLowerCase()) {
                    case "c" -> {
                        canvas = Canvas.create(Integer.parseInt(cmdArgs[1]), Integer.parseInt(cmdArgs[2]));
                        render(canvas);
                    }
                    case "l" -> {
                        if (canvas == null) {
                            System.out.print("canvas not created yet. please ");
                        } else {
                            Line line = new Line(
                                new Point(Integer.parseInt(cmdArgs[1]), Integer.parseInt(cmdArgs[2])),
                                new Point(Integer.parseInt(cmdArgs[3]), Integer.parseInt(cmdArgs[4])),
                                'x'
                            );
                            line.draw(canvas);
                            render(canvas);
                        }
                    }
                    case "r" -> {
                        if (canvas == null) {
                            System.out.print("canvas not created yet. please ");
                        } else {
                            Rectangle rectangle = new Rectangle(
                                new Point(Integer.parseInt(cmdArgs[1]), Integer.parseInt(cmdArgs[2])),
                                new Point(Integer.parseInt(cmdArgs[3]), Integer.parseInt(cmdArgs[4])),
                                'x'
                            );
                            rectangle.draw(canvas);
                            render(canvas);
                        }
                    }
                    case "b" -> {
                        if (canvas == null) {
                            System.out.print("canvas not created yet. please ");
                        } else {
                            BucketFill bucketFill = new BucketFill(
                                new Point(Integer.parseInt(cmdArgs[1]), Integer.parseInt(cmdArgs[2])),
                                'x'
                            );
                            bucketFill.draw(canvas);
                            render(canvas);
                        }
                    }
                    case "q" -> exited = true;
                    default -> System.out.print("invalid command. please ");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("invalid command arguments. please ");
                System.err.println(ex.getMessage());
            }
        }
        System.out.println("exited");
        sc.close();
    }

    /**
     * Renders the canvas to System.in.
     * @param canvas canvas to be rendered
     */
    public static void render(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        Character[][] pixels = canvas.output();
        for (Character[] pixel : pixels) {
            for (Character character : pixel) {
                System.out.print(character);
            }
            System.out.println();
        }
    }
}
