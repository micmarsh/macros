(ns marshmacros.coffee
    :use [marshmacros.test :only [defntest]])

(def fruit "apples")
(def meat {:name "beef" :types ["ground", "grass-fed"]})
(def drink "water")

(defntest add-to-map [map-so-far, current-symbol]
    {[{} fruit] {:fruit fruit}
     [{:meat meat} drink]
        {:meat meat :drink drink}}
    "Adds the current symbol and it's keyword to the given map"
    (assoc
        map-so-far
        (keyword (name current-symbol))
        current-symbol))

(defmacro cofmap [& symbols]
 (reduce add-to-map {} symbols))

;(defmacro dcofmap [& symbols])