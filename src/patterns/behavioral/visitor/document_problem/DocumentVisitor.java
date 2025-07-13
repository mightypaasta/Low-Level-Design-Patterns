package patterns.behavioral.visitor.document_problem;

import java.text.MessageFormat;

public interface DocumentVisitor {
    void visit(Paragraph paragraph);
    void visit(Heading heading);
    void visit(Image image);
}

class XMLExport implements  DocumentVisitor{

    @Override
    public void visit(Paragraph paragraph) {
        System.out.println("<paragraph>"+paragraph.text+"</paragraph>");
    }

    @Override
    public void visit(Heading heading) {
        String message = MessageFormat.format("<heading level={0}>{1}</heading>", heading.level,heading.text);
        System.out.println(message);
    }

    @Override
    public void visit(Image image) {
        String message = MessageFormat.format("<image url=\"{0}\"/>",image.imageUrl);
        System.out.println(message);
    }

}

class Spellchecker implements DocumentVisitor{

    @Override
    public void visit(Paragraph paragraph) {
        System.out.println("Checking spelling for text: "+paragraph.text);
    }

    @Override
    public void visit(Heading heading) {
        System.out.println("Checking spelling for heading text: "+heading.text);
    }

    @Override
    public void visit(Image image) {
        System.out.println("No spelling check for imageUrl : "+image.imageUrl);
    }
}
