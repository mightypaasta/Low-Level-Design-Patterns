package patterns.behavioral.visitor.shape_problem;

public class Problem1 {
   public void Solve(){
        Circle circle = new Circle(5);
        Square square = new Square(4);
        Triangle triangle = new Triangle(3,4);
        ScreenDrawer screenDrawer = new ScreenDrawer();
        PrinterDrawer printerDrawer = new PrinterDrawer();

        circle.accept(screenDrawer);
        square.accept(screenDrawer);
        triangle.accept(screenDrawer);

        circle.accept(printerDrawer);
        square.accept(printerDrawer);
        triangle.accept(printerDrawer);
   }
}
