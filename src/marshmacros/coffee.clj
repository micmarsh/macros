(ns marshmacros.coffee)

(defmacro cofmap [& symbols]
    (reduce (fn [map-so-far, current-symbol]
        (assoc
            map-so-far
            (keyword (name current-symbol))
            current-symbol))
    {} symbols ))

;(defmacro dcofmap [& symbols])