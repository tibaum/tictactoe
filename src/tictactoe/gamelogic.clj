(ns tictactoe.gamelogic)

(defn- number-of-moves [gameboard]
  (count (filter #(not (= :_ %1)) gameboard)))


(defn- winner-on-tiles [x y z gameboard]
  (let [first-tile (nth gameboard x)
        second-tile (nth gameboard y)
        third-tile (nth gameboard z)]
    (if (and
          (= first-tile second-tile third-tile)
          (not (= first-tile :_)))
      true false)))


(defn- winner [gameboard]
  (cond
    (winner-on-tiles 0 1 2 gameboard) (nth gameboard 0)
    (winner-on-tiles 3 4 5 gameboard) (nth gameboard 3)
    (winner-on-tiles 6 7 8 gameboard) (nth gameboard 6)
    (winner-on-tiles 0 3 6 gameboard) (nth gameboard 0)
    (winner-on-tiles 1 4 7 gameboard) (nth gameboard 1)
    (winner-on-tiles 2 5 8 gameboard) (nth gameboard 2)
    (winner-on-tiles 0 4 8 gameboard) (nth gameboard 0)
    (winner-on-tiles 2 4 6 gameboard) (nth gameboard 2)
    :else :_))


(defn- next-player [gameboard]
  (cond
    (= 0 (count (filter #(= :_ %1) gameboard))) :_
    (not (= (winner gameboard) :_)) :_
    (even? (number-of-moves gameboard)) :x
    :else :o))


(defn- move-player [gameboard tile]
  (let [player (next-player gameboard)
        tile-content (nth gameboard tile)]
      (if (= tile-content :_)
        (assoc gameboard tile player)
        gameboard)))


(defn move [command]
  (let [gameboard (:gameboard command)
        tile (- (:tile command) 1)]
      (move-player gameboard tile)))


(defn state [gameboard]
  {:winner (winner gameboard)
    :next-player (next-player gameboard)})
