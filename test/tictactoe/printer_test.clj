(ns tictactoe.printer-test
  (:require [clojure.test :refer :all]
            [tictactoe.printer :refer :all]
            [tictactoe.gamelogic :refer :all]))

(deftest make-char-rows-test
  (testing "testing the conversion of a gameboard into a three row character representation"
  (is (= ["X - O" "X O X" "O - -"] (make-char-rows [:x :_ :o :x :o :x :o :_ :_])))))

(deftest state-as-text-when-player-x-has-won-test
  (testing "testing the display text when player x has won"
  (is (= "Player X has won" (state-as-text (state [:x :_ :o :_ :x :o :_ :_ :x]))))))

(deftest state-as-text-when-player-o-has-won-test
  (testing "testing the display text when player o has won"
  (is (= "Player O has won" (state-as-text (state [:x :_ :o :x :o :x :o :_ :_]))))))

(deftest state-as-text-when-tie-test
  (testing "testing the display text when tie"
  (is (= "Tie" (state-as-text (state [:x :o :x :o :x :x :o :x :o]))))))

(deftest state-as-text-when-player-x-moves-next-test
  (testing "testing the display text when player x moves next"
  (is (= "Player X moves next" (state-as-text (state [:x :o :_ :_ :_ :_ :_ :_ :_]))))))

(deftest state-as-text-when-player-o-moves-next-test
  (testing "testing the display text when player o moves next"
  (is (= "Player O moves next" (state-as-text (state [:x :_ :_ :_ :_ :_ :_ :_ :_]))))))
