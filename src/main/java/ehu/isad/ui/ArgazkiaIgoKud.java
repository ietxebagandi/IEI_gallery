package ehu.isad.ui;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.util.AuthStore;
import ehu.isad.Main;
import ehu.isad.flickr.FlickrAPI;
import ehu.isad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArgazkiaIgoKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    private boolean logout = false;

    @FXML
    private ComboBox comboBox = new ComboBox();

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClickAtzera(ActionEvent actionEvent) throws Exception {
        System.out.println("gorde");

    }

    @FXML
    public void onClickGorde(ActionEvent actionEvent) throws Exception {
        System.out.println("gorde");
    }

    @FXML
    private void handleDragOver(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws IOException {
        // este metodo es para descargar la imagen a una carpeta de dentro del programa llamada temp
        // Pero hay muchas formas de hacerlo, por ejemplo subir la imagen a flickr directamente y descargarla a la direccion de las imagenes( asi quedaria tal cual la queremos almacenar)
        // La funcion con tem las pasaria a la carpeta temporal y de ahí las pasa a flickr, base de datos o donde toque
        InputStream is = null;
        OutputStream os = null;
        String temp = "C:\\Users\\anderdu\\IdeaProjects\\DASIproject\\src\\main\\resources\\temp";
        List<File> files = event.getDragboard().getFiles();

        ArrayList<String> photoPaths = new ArrayList<>();
        for(File file:files) {
            System.out.println(file.getPath());
            photoPaths.add(file.getPath());
        }

        FlickrAPI api = FlickrAPI.getInstantzia();
        if (api.hasConection()){
            for(File file:files) {
                System.out.println(file.getPath());
                api.ArgazkiaIgo(file.getPath());
            }

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaBildumak bildumak = ListaBildumak.getNireBilduma();
        bildumak.bildumaSartu("bil1");
        bildumak.bildumaSartu("bil2");
        bildumak.bildumaSartu("bil3");

        List<Bilduma> bil = bildumak.lortuBildumak();
        List<String> str = new ArrayList<>();
        str.add("bil1");
        str.add("bil2");

        this.comboBox.getItems().addAll(str);
        System.out.println("str añadidos");

    }

    public void logoutAktibatu() {
        this.logout = true;
    }
}