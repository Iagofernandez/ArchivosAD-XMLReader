
package xmlreader;

import serializacion2.Product;
import java.io.*;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLReader {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
//ler dende o ficheiro products.xml que creaches no exercicio XMLwriter 
//os datos que almacenache nel
//e crear un Arraylist de obxectos product 
//imprimindo as variables de ditos    obxectos dende o Arraylist
        
        //LEctura del archivo del eje16
        File fichero = new File("/home/oracle/Desktop/AD/Eje16/productos.xml");
        FileReader ficheroleer = new FileReader(fichero);
        
        //Se da origen a una nueva Factory
        //permite obetener y trabajar con STreams 
        //Lectura de archivos de tipo xml
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader factoryleer = factory.createXMLStreamReader(ficheroleer);
        
        int datos = 0;
        //Array en el que almacenaremos los objetos
        //que estan escritos en el archivo
        ArrayList<Product> Listaproductos = new ArrayList<>();
        
        Product obj;
        String codigo = "";
        String descricion ="";
        Double precio=null;
        
        int num= 0;
        //hasNext devolvera true siempre que haya algun token sin leer
        while (factoryleer.hasNext()){
            //retorna un integer que indica cual es el tipo de dato al que est apuntando
            datos = factoryleer.getEventType();
            //El XMLStreamConstants permite interactuar y hacer llamdos
            //a algunas constnates de XML
            if(datos==XMLStreamConstants.START_ELEMENT){
                String nombre = factoryleer.getLocalName();
                if(nombre=="Codigo"){
                    codigo = factoryleer.getElementText();
                    
                
            }else if(nombre=="Descricion"){
                descricion = factoryleer.getElementText();
            }else if(nombre=="Precio"){
                precio = Double.parseDouble(factoryleer.getElementText());
            }
                if((codigo!="")&&(descricion!="")&&precio!=null){
                    Listaproductos.add(new Product(codigo,descricion,precio));
                    codigo = "";
                    descricion = "";
                    precio = null;
                }
                
            }
             factoryleer.next();
        }
        factoryleer.close();
        
         for (Product product : Listaproductos)
             System.out.println(product);
        
    }
    
}
