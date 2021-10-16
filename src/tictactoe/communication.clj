(ns tictactoe.communication
  (:require [tictactoe.gamelogic :refer :all]
            [tictactoe.printer :refer :all]))

(def initial-gameboard [:_ :_ :_ :_ :_ :_ :_ :_ :_])
(def current-gameboard (ref initial-gameboard))

(defn set-gameboard [tiles]
  (let [gameboard (map #({:x "X" :o "O" :_ ""} %1) (deref current-gameboard))]
    (.setText (nth tiles 0) (nth gameboard 0))
    (.setText (nth tiles 1) (nth gameboard 1))
    (.setText (nth tiles 2) (nth gameboard 2))
    (.setText (nth tiles 3) (nth gameboard 3))
    (.setText (nth tiles 4) (nth gameboard 4))
    (.setText (nth tiles 5) (nth gameboard 5))
    (.setText (nth tiles 6) (nth gameboard 6))
    (.setText (nth tiles 7) (nth gameboard 7))
    (.setText (nth tiles 8) (nth gameboard 8))))

(defn create-activation-configuration [status]
  (if (not (= (status :winner) :_))
    (take 9 (repeat true))
    (map #(not (= :_ %1)) (deref current-gameboard))))

(defn deactivate-played-tiles [status tiles]
  (let [activation-configuration (create-activation-configuration status)]
    (.setDisable (nth tiles 0) (nth activation-configuration 0))
    (.setDisable (nth tiles 1) (nth activation-configuration 1))
    (.setDisable (nth tiles 2) (nth activation-configuration 2))
    (.setDisable (nth tiles 3) (nth activation-configuration 3))
    (.setDisable (nth tiles 4) (nth activation-configuration 4))
    (.setDisable (nth tiles 5) (nth activation-configuration 5))
    (.setDisable (nth tiles 6) (nth activation-configuration 6))
    (.setDisable (nth tiles 7) (nth activation-configuration 7))
    (.setDisable (nth tiles 8) (nth activation-configuration 8))))

(defn display-state [status status-text-field]
  (.setText status-text-field (state-as-text status)))

(defn update-view [tiles status-text-field]
  (let [status (state (deref current-gameboard))]
    (set-gameboard tiles)
    (deactivate-played-tiles status tiles)
    (display-state status status-text-field)))

(defn move-and-update [index tiles status-text-field]
  (dosync
    (ref-set current-gameboard
      (move {:tile (+ index 1) :gameboard (deref current-gameboard)})))
    (update-view tiles status-text-field))

(defn new-game [tiles status-text-field]
  (dosync (ref-set current-gameboard initial-gameboard))

  (.setDisable (nth tiles 0) false)
  (.setDisable (nth tiles 1) false)
  (.setDisable (nth tiles 2) false)
  (.setDisable (nth tiles 3) false)
  (.setDisable (nth tiles 4) false)
  (.setDisable (nth tiles 5) false)
  (.setDisable (nth tiles 6) false)
  (.setDisable (nth tiles 7) false)
  (.setDisable (nth tiles 8) false)

  (.setText (nth tiles 0) "")
  (.setText (nth tiles 1) "")
  (.setText (nth tiles 2) "")
  (.setText (nth tiles 3) "")
  (.setText (nth tiles 4) "")
  (.setText (nth tiles 5) "")
  (.setText (nth tiles 6) "")
  (.setText (nth tiles 7) "")
  (.setText (nth tiles 8) "")

  (.setText status-text-field "New Game"))
