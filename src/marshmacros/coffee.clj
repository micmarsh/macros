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

(defmacro dcmap [& symbols]
    (reduce (add-to-map-closure true) {} symbols))

(defn- convert-item [i, item]
    ());TODO this: check index first, return item if odd? (<- this exists)
        ; then go to other function that checks type (you're *probably* looking for
        ; list) and convert to map according to dcmap


(defn- convert-lists [let-vector]
    (vec (map-indexed current-symbol let-vector)))

;TODO: at least check array length, maybe in convert-lists
(defmacro cdestruct [let-vector & body-statements]
    (let [declared-things (convert-lists let-vector)]
        `(let declared-things ~@body-statements))) ;the & might take care of everything we
                                        ;want in regards to body statements



;(defmacro dcofmap [& symbols])