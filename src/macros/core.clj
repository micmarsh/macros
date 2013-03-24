(ns macros.core
    (:use [macros.test :only [defntest]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
    (defntest poo {[1 2] 3} [x y] (+ x y ))
    (println (poo 4 9))
    (defntest add
    {'(1 2 3) 6
     '(3 4) 7
     '(6 7) 13}
     [& args]
     (apply + args)))

