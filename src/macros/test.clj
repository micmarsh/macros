(ns macros.test)

(defmacro defntest [name first second & body]
    (let [  [tests, args]
                (if (map? first) [first, second] [second, first])
            result `(fn [~@args] ~@body)]
        (loop [allkeys (keys tests)]
            (println allkeys)
            (if (not= '() allkeys)
                (let [input (first allkeys)
                     expected-value (tests input)]
                     (if (not= expected-value (apply ~result input))
                        (throw (Exception. (str "Test Exception:\n"
                            input " doesn't produce " expected-value))))))
            (recur (rest allkeys)))
        `(def ~name ~result)))
    ;`(defn ~name [~@args] ~@body)))