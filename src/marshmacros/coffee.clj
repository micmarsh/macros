(ns marshmacros.coffee
    (:use [marshmacros.test :only [defntest fntest]]))

(def fruit "apples")
(def meat {:name "beef" :types ["ground", "grass-fed"]})
(def drink "water")

(defn- add-to-map-closure [reverse-order]
    "Returns a function that adds the given symbol to the map in either
    {:symbol symbol} or {symbol :symbol} order"
    (fn [map-so-far, current-symbol]
        (let [map-key (keyword (name current-symbol))
              map-val current-symbol]
            (assoc
                map-so-far
                (if reverse-order map-val map-key)
                (if reverse-order map-key map-val)))))

(defmacro cofmap [& symbols]
 (reduce (add-to-map-closure false) {} symbols))


;(defmacro dcofmap [& symbols])