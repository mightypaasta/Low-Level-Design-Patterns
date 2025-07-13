package patterns.behavioral.visitor.shape_problem;

interface Shape {
    void draw();
    void accept(ShapeVisitor shapeVisitor);
}

class Circle implements  Shape{

    int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("drawing circle of radius "+radius);
    }

    @Override
    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }
}

class Square implements Shape{

    int sides;

    public Square(int sides){
        this.sides = sides;
    }

    @Override
    public void draw() {
        System.out.println("drawing square of side "+sides);
    }

    @Override
    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }
}

class Triangle implements Shape{

    int base, height;

    public Triangle(int base, int height){
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing triangle of base and height "+base+" "+height+"respectively");
    }

    @Override
    public void accept(ShapeVisitor shapeVisitor) {
        shapeVisitor.visit(this);
    }
}
