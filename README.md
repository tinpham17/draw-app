# draw-app

This is a simple console version of a drawing program.
At this time, the functionality of the program is quite limited but this might change in the future.
In a nutshell, the program should work as follows:
1. Create a new canvas
2. Start drawing on the canvas by issuing various commands
3. Quit

## Usage

- `C w h`: create a new canvas of width w and height h
- `L x1 y1 x2 y2`: create a new line from (x1,y1) to (x2,y2). Currently, only
  horizontal or vertical lines are supported. Horizontal and vertical lines
  will be drawn using the 'x' character. 
- `R x1 y1 x2 y2`: create a new rectangle, whose upper left corner is (x1,y1) and
  lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
  using the 'x' character.
- `B x y c`: fill the entire area connected to (x,y) with "colour" c. The
  behavior of this is the same as that of the "bucket fill" tool in paint
  programs. 
- `Q`: quit the program.
