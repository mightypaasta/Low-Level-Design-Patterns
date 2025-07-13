package patterns.behavioral.visitor.shape_problem;

public interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Square square);
    void visit(Triangle triangle);
}

class ScreenDrawer implements ShapeVisitor{

    @Override
    public void visit(Circle circle) {
        System.out.println("Drawing circle on screen with raidus "+circle.radius);
    }

    @Override
    public void visit(Square square) {
        System.out.println("Drawing square on screen with sides "+square.sides);
    }

    @Override
    public void visit(Triangle triangle) {
        System.out.println("Drawing triangle on screen with base and height of "+triangle.base+" "+triangle.height+" respectively.");
    }
}

class PrinterDrawer implements  ShapeVisitor{

    @Override
    public void visit(Circle circle) {
        System.out.println("Printing circle on printer with radius "+circle.radius);
    }

    @Override
    public void visit(Square square) {
        System.out.println("Printing square on printer with side "+square.sides);
    }

    @Override
    public void visit(Triangle triangle) {
        System.out.println("Printing triangle on printer with base and height of "+triangle.base+" "+triangle.height+" respectively.");
    }
}
