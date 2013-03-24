(ns macros.core
    (:use [macros.test :only [defntest]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
    (defntest poo {[1 2] 3} [x y] (+ x y 4))
    (println (poo 4 9)))

