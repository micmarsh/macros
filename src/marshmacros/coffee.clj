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

(defn- destructure-map [symbols]

    (reduce (add-to-map-closure true) {} symbols))

(defn- check-type-and-convert [item]
    (if (list? item)
            (destructure-map item)
        ;else
            item))

(defn- convert-item [i, item]
    (if (odd? i)
            item
        ;else
            (check-type-and-convert item)))


(defntest convert-lists [let-vector]
    {[[(fruit drink) bar sym0 foo]]
        [{fruit :fruit drink :drink} bar sym0 foo]
     [[[sym1 sym2] meat sym3 fruit]]
        [[sym1 sym2] meat sym3 fruit]}
    (vec (map-indexed convert-item let-vector)))

;TODO: at least check array length, maybe in convert-lists
(defmacro cdestruct [let-vector & body-statements]
    (let [declared-things (convert-lists let-vector)]
        `(let ~declared-things ~@body-statements))) ;the & might take care of everything we
                                        ;want in regards to body statements



;(defmacro dcofmap [& symbols])