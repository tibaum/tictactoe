(ns tictactoe.console
  (:require [tictactoe.gamelogic :refer :all]
            [tictactoe.printer :refer :all]))

(defn- in-out [gameboard]
  (let [user-input (read)]
    (if (not (nil? (#{1 2 3 4 5 6 7 8 9} user-input)))
      (let [new-gameboard (move {:tile user-input :gameboard gameboard})]
        (print-gameboard new-gameboard)
        (print-state (state new-gameboard))
        (if (not (= ((state new-gameboard) :next-player) :_))
          (in-out new-gameboard))))))

(defn start-console []
  (let [initial-gameboard [:_ :_ :_ :_ :_ :_ :_ :_ :_]]
    (println "Type a number between 1 and 9 to move to a tile")
    (println "or type anything else to end the game.")
    (print-gameboard initial-gameboard)
    (in-out initial-gameboard)))
