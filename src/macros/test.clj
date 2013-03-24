(ns macros.test)

(defn- throw-exception! [args, result]
    (throw (Exception. (str "Test Case Failed:\n"
        "arguments " args " don't produce "
        " result " result \newline))))


(defn- test-and-throw! [fn-to-apply, input, expected-value]
    (if (not= expected-value (apply fn-to-apply input))
        (throw-exception! (seq input) expected-value)))

(defn- loop-through-tests! [fn-to-apply, tests]
    (loop [allkeys (keys tests)]
        (if-not (empty? allkeys)
            (let [input (first allkeys)
                 expected-value (tests input)]
                 (test-and-throw! fn-to-apply input expected-value)
                 (recur (rest allkeys))))))

(defmacro defntest [name  first second & body]
    (let [  [tests args] (if (map? first) [first, second] [second, first])
            result-symbol `(fn [~@args] ~@body) ]
        (loop-through-tests! (eval result-symbol) tests)
        `(def ~name ~result-symbol)))