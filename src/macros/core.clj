(ns macros.core
    (:use [macros.test :only [defntest]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main [& args]
    (defntest poo { [3] "poo 3"} [thing] (str "poo " thing ))
    (println (poo 4))
    (defntest addThreeThings
        {[1 2 3] 6
         [3 1 4] 8
         [0 6 7] 13}
         [x y z]
        (+ x y z))
    (println (addThreeThings 1 2 6)))

