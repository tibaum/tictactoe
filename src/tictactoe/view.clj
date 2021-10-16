(ns tictactoe.view
  (:require [tictactoe.communication :refer :all])
  (:import [javafx.scene.layout RowConstraints ColumnConstraints GridPane Priority]
            [javafx.scene.control Button TextField]
            [javafx.event EventHandler]))

(def empty-row-heigt 10)
(def empty-column-width 10)
(def tile-row-height 100)
(def tile-column-width 100)
(def row-height-for-column-spanning-elements 25)

(defn create-grid-pane []
  (let [gridPane (GridPane.)
        row-contraints (.getRowConstraints gridPane)
        column-constraints (.getColumnConstraints gridPane)]

    (.add row-contraints 0 (RowConstraints. empty-row-heigt))
    (.add row-contraints 1 (RowConstraints. tile-row-height))
    (.add row-contraints 2 (RowConstraints. tile-row-height))
    (.add row-contraints 3 (RowConstraints. tile-row-height))
    (.add row-contraints 4 (RowConstraints. empty-row-heigt))
    (.add row-contraints 5 (RowConstraints. row-height-for-column-spanning-elements))
    (.add row-contraints 6 (RowConstraints. empty-row-heigt))
    (.add row-contraints 7 (RowConstraints. row-height-for-column-spanning-elements))

    (.add column-constraints 0 (ColumnConstraints. empty-column-width))
    (.add column-constraints 1 (ColumnConstraints. tile-column-width))
    (.add column-constraints 2 (ColumnConstraints. tile-column-width))
    (.add column-constraints 3 (ColumnConstraints. tile-column-width))
    (.add column-constraints 4 (ColumnConstraints. empty-column-width))

    gridPane))

(defn create-tile-click-event-handler [index tiles status-text-field]
  (reify EventHandler
    (handle [this event]
      (move-and-update index tiles status-text-field))))

(defn create-tile [index]
  (let [tile (Button.)]
    (GridPane/setHgrow tile Priority/ALWAYS)
    (GridPane/setVgrow tile Priority/ALWAYS)
    (.setMaxSize tile Double/MAX_VALUE Double/MAX_VALUE)
    (.setFocusTraversable tile false)
    tile))

(defn create-new-game-button []
  (let [new-game-button (Button. "New Game")]
    (GridPane/setColumnSpan new-game-button (int 3))
    (.setId new-game-button "new-game")
    (.setMaxSize new-game-button Double/MAX_VALUE Double/MAX_VALUE)
    (.setFocusTraversable new-game-button false)
    new-game-button))

(defn create-status-text-field []
  (let [text-field (TextField.)]
    (GridPane/setColumnSpan text-field (int 5))
    (.setId text-field "game-state")
    (.setDisable text-field true)
    (.setMaxSize text-field Double/MAX_VALUE Double/MAX_VALUE)
    text-field))

(defn create-new-game-click-event-handler [tiles status-text-field]
  (reify EventHandler
    (handle [this event]
      (new-game tiles status-text-field))))

(defn construct-view [stage]
  (let [gridPane (create-grid-pane)
        scene (javafx.scene.Scene. gridPane)
        status-text-field (create-status-text-field)
        new-game-button (create-new-game-button)
        tiles (map create-tile [1 2 3 4 5 6 7 8 9])]

    (.setOnAction (nth tiles 0) (create-tile-click-event-handler 0 tiles status-text-field))
    (.setOnAction (nth tiles 1) (create-tile-click-event-handler 1 tiles status-text-field))
    (.setOnAction (nth tiles 2) (create-tile-click-event-handler 2 tiles status-text-field))
    (.setOnAction (nth tiles 3) (create-tile-click-event-handler 3 tiles status-text-field))
    (.setOnAction (nth tiles 4) (create-tile-click-event-handler 4 tiles status-text-field))
    (.setOnAction (nth tiles 5) (create-tile-click-event-handler 5 tiles status-text-field))
    (.setOnAction (nth tiles 6) (create-tile-click-event-handler 6 tiles status-text-field))
    (.setOnAction (nth tiles 7) (create-tile-click-event-handler 7 tiles status-text-field))
    (.setOnAction (nth tiles 8) (create-tile-click-event-handler 8 tiles status-text-field))

    (.setOnAction new-game-button (create-new-game-click-event-handler tiles status-text-field))

    (.add (.getStylesheets scene) (.toExternalForm (clojure.java.io/resource "tictactoe.css")))
    (.setTitle stage "Tic Tac Toe")
    (.setScene stage scene)

    (.add gridPane (nth tiles 0) 1 1)
    (.add gridPane (nth tiles 1) 2 1)
    (.add gridPane (nth tiles 2) 3 1)
    (.add gridPane (nth tiles 3) 1 2)
    (.add gridPane (nth tiles 4) 2 2)
    (.add gridPane (nth tiles 5) 3 2)
    (.add gridPane (nth tiles 6) 1 3)
    (.add gridPane (nth tiles 7) 2 3)
    (.add gridPane (nth tiles 8) 3 3)

    (.add gridPane status-text-field 0 7)
    (.add gridPane new-game-button 1 5)

    (.setResizable stage false)

    (new-game tiles status-text-field)

    (.show stage)))
