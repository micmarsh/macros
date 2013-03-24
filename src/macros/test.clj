(ns macros.test)

(defmacro defntest [name tests args & body]
    (let [  result-symbol `(fn [~@args] ~@body)
            result-fn (eval result-symbol)]
        (loop [allkeys (keys tests)]
            (if-not (empty? allkeys)
                (let [input (first allkeys)
                     expected-value (tests input)]
                     (if (not= expected-value (apply result-fn input))
                        (throw (Exception. (str "Test Case Failed:\n"
                            "arugments " (seq input) " don't produce "
                            " result " expected-value \newline))))
                     (recur (rest allkeys)))))
        `(def ~name ~result-symbol)))