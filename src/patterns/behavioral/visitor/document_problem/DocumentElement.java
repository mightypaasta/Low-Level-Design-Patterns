package patterns.behavioral.visitor.document_problem;

public abstract class DocumentElement {
    abstract void accept(DocumentVisitor visitor);
}

class Paragraph extends DocumentElement{

    String text;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}

class Heading extends  DocumentElement{

    String text;
    int level;

    public Heading(String text, int level){
        this.text = text;
        this.level = level;
    }

    @Override
    void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}

class Image extends DocumentElement {
    String imageUrl;

    public Image(String imageUrl){
        this.imageUrl = imageUrl;
    }

    @Override
    void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}

class Document {
    private java.util.List<DocumentElement> list;

    public Document(){
        this.list = new java.util.ArrayList<DocumentElement>();
    }

    public void addElement(DocumentElement element){
        this.list.add(element);
    }

    public void removeElement(DocumentElement element){
        this.list.remove(element);
    }

    public void process(DocumentVisitor visitor){
        this.list.forEach(element -> {
            element.accept(visitor);
        });
    }
}