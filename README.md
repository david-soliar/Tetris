# Tetris

A simple Tetris clone written in Java using JavaFX.

Includes score and level system, where higher levels increase game speed.

<p align="center">
  <img src="example1.png" width="45%" style="margin-right: 10px;" />
  <img src="example2.png" width="45%" />
</p>

Controls (arrow keys):
- ← → : Move piece
- ↓ : Soft drop
- ↑ : Rotate piece


## Building and Running
Requirements:
- Java 25
- Maven

### Run
```bash
mvn javafx:run
```

### Build
remove deps & runtine & dist folders if they exist and then run
```bash
mvn clean package

mvn dependency:copy-dependencies -DincludeScope=runtime -DoutputDirectory=deps

jlink --module-path "%JAVA_HOME%\jmods;deps" --add-modules javafx.controls,javafx.fxml --output runtime --strip-debug --compress=2 --no-header-files --no-man-pages

jpackage --name Tetris --input target --main-jar Tetris-1.0.0.jar --main-class davidsoliar.tetris.App --runtime-image runtime --type app-image --dest dist
```
