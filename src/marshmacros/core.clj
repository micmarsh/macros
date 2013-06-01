(ns marshmacros.core
    (:use [marshmacros.test :only [defntest]]))


(defn -main [& args]
    (defntest addThreeThings [x y z]
        "Adds three numbers together"
        {[1 2 3] 6
         [3 1 4] 8
         [0 6 7] 13}
        (+ x y z))
    (println (addThreeThings 1 2 6) ))

