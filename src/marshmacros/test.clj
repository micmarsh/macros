(ns marshmacros.test)

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


(defn- get-keyword [item]
    (cond
        (string? item)
            :docs
        (vector? item)
            :args
        (map? item)
            :tests
        :else
            :body))

(defn- classify-args [args]
    (reduce (fn [acc, item]
        (assoc acc (get-keyword item) item))
    {}
    args))

(defn- process-args [args]
    let[{body :body tests :tests
         args :args docs :docs} (classify-args (take 3 args))]
        [tests, args, (or docs "")
            (cons body (drop 3 args))])


(defmacro defntest [name, & arguments]
    (let [  [tests args docs body] (process-args arguments)
            result-symbol `(fn [~@args] ~@body) ]
        (loop-through-tests! (eval result-symbol) tests)
        `(defn ~name ~docs [~@args] ~@body)))

;how to add docs to defntest functions:
;   get-tests-and-args has to go. Instead process an instance of '& args'
;   extract first, second, and third (or a 'take 3') (and '& rest') with some let action
;
;
;