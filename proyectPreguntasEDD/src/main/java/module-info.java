module ec.edu.espol.proyectpreguntasedd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectpreguntasedd to javafx.fxml;
    exports ec.edu.espol.proyectpreguntasedd;
}
