(ns macros.test)

;to abstract more: fn

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

(defn- get-tests-and-args [first, second]
    (if (map? first) [first, second] [second, first]))

(defmacro defntest [name  first second & body]
    (let [  [tests args] (get-tests-and-args first second)
            result-symbol `(fn [~@args] ~@body) ]
        (loop-through-tests! (eval result-symbol) tests)
        `(def ~name ~result-symbol)))