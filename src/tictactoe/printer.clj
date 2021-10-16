(ns tictactoe.printer
  (:require [clojure.string :refer (join)]))

(def player-representation {:x "X" :o "O" :_ "-"})

(defn- char-representation [tile-row]
  (map #(player-representation %1) tile-row))

(defn- make-char-row [tile-row]
  (join " " (char-representation tile-row)))

(defn make-char-rows [gameboard]
  (map make-char-row (partition 3 gameboard)))

(defn print-gameboard [gameboard]
  (println (join "\n" (make-char-rows gameboard))))

(defn state-as-text [state]
  (let [next-player (state :next-player)
        winner (state :winner)]
    (cond
      (not (= winner :_)) (str "Player " (player-representation winner) " has won")
      (= next-player :_) "Tie"
      :else (str "Player " (player-representation next-player) " moves next"))))

(defn print-state [state]
  (println (state-as-text state)))
