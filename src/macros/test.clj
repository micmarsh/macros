(ns macros.test)

(defmacro defntest [name first second & body]
    (let [  [tests, args]
                (if (map? first) [first, second] [second, first])
            result-symbol `(fn [~@args] ~@body)
            result-fn (eval result-symbol)]
        (loop [allkeys (keys tests)]
            (println (type allkeys) \space allkeys \space (first allkeys))
            (if-not (empty? allkeys)
                (let [input (first allkeys)
                     expected-value (tests input)]
                     (if (not= expected-value (result-fn 1 2));(apply result-fn input))
                        (throw (Exception. (str "Test Case Failed:\n"
                            input " doesn't produce " expected-value \newline))))))
            (recur (rest allkeys)))
        `(def ~name ~result-symbol)))
    ;`(defn ~name [~@args] ~@body)))