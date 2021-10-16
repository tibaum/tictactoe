(defproject tictactoe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game"
  :dependencies [[org.clojure/clojure "1.10.3"]]
  :main tictactoe.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init (do (compile 'tictactoe.core)
                  (future (-main)))})
