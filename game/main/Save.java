
package game.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Save {
   /*public void saveScore(){
       if(Game.SCORE>Game.highScore){
           Game.highScore = Game.SCORE;
            try {
           PrintWriter pw = new PrintWriter("Mentes.txt");
           pw.println(Game.highScore);
           pw.close();
       } catch (Exception e) {
           System.out.println("Nem jó a mentés");
       }
       }
      
   }
   public void loadHighScore(){
       try {
           BufferedReader br = new BufferedReader(new FileReader("Mentes.txt"));
           String mentes = br.readLine();
           while(mentes !=null){
              // System.out.println(mentes);
           }
           br.close();
       } catch (Exception e) {
           System.out.println("Nem jó az olvasás");
       }
   }*/
    public void saveHighscore(){
        if(Game.SCORE>Game.highScore){
           Game.highScore = Game.SCORE;
           try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement(Game.highScore.toString());
            doc.appendChild(rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("file.xml"));
            transformer.transform(source, result);

		System.out.println("File saved!");  
           }
           catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
        }// end of if
    }
    public void loadHighscore(){
         try {

	File fXmlFile = new File("file.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());
        
         }
         catch(Exception e){
             System.out.println(e);
         }
    }
    
  
}
