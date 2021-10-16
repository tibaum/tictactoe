(ns tictactoe.gui
  (:gen-class :extends javafx.application.Application)
  (:require [tictactoe.view :as main-window])
  (:import [javafx.application Application Platform]))

(defn start-gui []
  (Application/launch (Class/forName "tictactoe.gui") (into-array String [])))

(defn -start [this stage]
  (main-window/construct-view stage))
