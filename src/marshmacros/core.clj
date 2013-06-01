(ns marshmacros.core
    (:use [marshmacros.test :only [defntest]]))


(defn -main [& args]
    "docs"
    (defntest woot [thing]
        { [3] "woot 3"
         ["yeah"] "woot yeah"}
         "this function is wootastic"
         (println "woah fancy function body")
         (str "woot " thing ))
    (println (woot 5))
    (defntest addThreeThings [x y z]
        "Adds three numbers together"
        {[1 2 3] 6
         [3 1 4] 8
         [0 6 7] 13}
        (+ x y z))
    (println (addThreeThings 1 2 6)))

