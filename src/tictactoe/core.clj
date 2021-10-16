(ns tictactoe.core
  (:gen-class)
  (:require [tictactoe.gui :refer :all]
            [tictactoe.console :refer :all]))

(defn -main [& args]
  (cond
    (= args ["gui"]) (start-gui)
    (= args ["console"]) (start-console)
    :else (println "Parameters:\ngui: start gui version\nconsole: start console version")))
