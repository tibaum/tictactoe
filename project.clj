(defproject tictactoe "1.0"
  :description "Tic Tac Toe Game"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.openjfx/javafx-graphics "13.0.1"] 
                 [org.openjfx/javafx-controls "13.0.1"]]
  :main tictactoe.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init (do (compile 'tictactoe.core)
                  (future (-main)))})
