module cat.boscdelacoma.interfacetest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens cat.boscdelacoma.interfacetest to javafx.fxml;
    exports cat.boscdelacoma.interfacetest;
    
        opens cat.boscdelacoma.interfacetest.parc to javafx.fxml;
    exports cat.boscdelacoma.interfacetest.parc;
}
