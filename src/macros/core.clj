(ns macros.core
    (:use [macros.test :only [defntest]]))

(defn -main [& args]
    (defntest woot { [3] "woot 3"} [thing] (str "woot " thing ))
    (println (woot 4))
    (defntest addThreeThings
        {[1 2 3] 6
         [3 1 4] 8
         [0 6 7] 13}
         [x y z]
        (+ x y z))
    (println (addThreeThings 1 2 6)))

