(ns macros.core
    (:use [macros.test :only [defntest]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
    (defntest poo {:x :y} [x y] (+ x y))
    (println (poo 4 9)))

