module ec.edu.espol.proyectpreguntasedd {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectpreguntasedd to javafx.fxml;
    exports ec.edu.espol.proyectpreguntasedd;
}
