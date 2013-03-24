(ns macros.test)

(defmacro defntest [name first second & body]
    (let [  [tests, args]
                (if (map? first) [first, second] [second, first])
            result `(fn [~@args] ~@body)]
        (println tests)
        (println args)
        `(def ~name ~result)))
    ;`(defn ~name [~@args] ~@body)))