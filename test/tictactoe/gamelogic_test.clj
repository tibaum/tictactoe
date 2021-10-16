(ns tictactoe.gamelogic-test
  (:require [clojure.test :refer :all]
            [tictactoe.gamelogic :refer :all]))

(deftest first-move-test
  (testing "testing player X moves to first tile"
    (is (= [:x :_ :_ :_ :_ :_ :_ :_ :_]
      (move {:tile 1 :gameboard [:_ :_ :_ :_ :_ :_ :_ :_ :_]})))))

(deftest move-not-possible-test
  (testing "testing player O moves to nineth tile"
    (is (= [:x :_ :_ :_ :_ :_ :_ :_ :o]
      (move {:tile 9 :gameboard [:x :_ :_ :_ :_ :_ :_ :_ :_]})))))

(deftest move-not-possible-on-self-inhabited-test
  (testing "testing move not possible because tile is already inhabited by current player"
    (is (= [:o :_ :_ :x :_ :_ :_ :_ :_]
      (move {:tile 4 :gameboard [:o :_ :_ :x :_ :_ :_ :_ :_]})))))

(deftest move-not-possible-on-foreign-inhabited-test
  (testing "testing move not possible because tile is already inhabited by the other player"
    (is (= [:_ :_ :_ :x :_ :_ :_ :_ :_]
      (move {:tile 4 :gameboard [:_ :_ :_ :x :_ :_ :_ :_ :_]})))))

(deftest state-of-empty-gameboard-test
  (testing "testing the state of the empty gameboard"
    (is (= {:winner :_ :next-player :x} (state [:_ :_ :_ :_ :_ :_ :_ :_ :_])))))

(deftest state-of-gameboard-after-first-move-test
  (testing "testing the state of the gameboard after the first move"
    (is (= {:winner :_ :next-player :o} (state [:_ :_ :_ :_ :x :_ :_ :_ :_])))))

(deftest state-of-tie-gameboard-test
  (testing "testing the state of the gameboard when no player has won"
    (is (= {:winner :_ :next-player :_} (state [:x :x :o :o :o :x :x :o :x])))))

(deftest state-of-winner-x-when-all-tiles-are-set-gameboard-test
  (testing "testing the state of the gameboard when all tiles are set and player X has won"
    (is (= {:winner :x :next-player :_} (state [:x :x :o :x :o :o :x :o :x])))))

(deftest state-of-winner-x-gameboard-test
  (testing "testing the state of the gameboard when player X has won"
    (is (= {:winner :x :next-player :_} (state [:x :_ :o :_ :x :o :_ :_ :x])))))

(deftest state-of-winner-o-gameboard-test
  (testing "testing the state of the gameboard when player O has won"
    (is (= {:winner :o :next-player :_} (state [:x :_ :o :x :o :x :o :_ :_])))))
