#!/bin/bash
for d in */ ; do
    replace "http://javafx.com/javafx/17" "http://javafx.com/javafx" -- $d/*.fxml
    replace "http://javafx.com/fxml/1" "http://javafx.com/fxml" -- $d/*.fxml
done
