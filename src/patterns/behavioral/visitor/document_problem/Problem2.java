package patterns.behavioral.visitor.document_problem;

public class Problem2 {
    Paragraph para = new Paragraph("Hello world");
    Heading heading = new Heading("Heading 1",1);
    Image image = new Image("imageUrl");

    XMLExport xmlExporter = new XMLExport();
    Spellchecker spellchecker = new Spellchecker();

    Document document = new Document();


//    private void runXMLExtractor(){
//        para.accept(xmlExporter);
//        heading.accept(xmlExporter);
//        image.accept(xmlExporter);
//    }
//
//    private void runSpellchecker(){
//        para.accept(spellchecker);
//        heading.accept(spellchecker);
//        image.accept(spellchecker);
//    }



    public void Solve(){
        document.addElement(para);
        document.addElement(heading);
        document.addElement(image);

        document.process(xmlExporter);
        document.process(spellchecker);
//        runXMLExtractor();
//        runSpellchecker();
    }
}
