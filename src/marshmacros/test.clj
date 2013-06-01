(ns marshmacros.test)

;to abstract more: fn

(defn- throw-exception! [args, expected-result, actual-result]
    (throw (Exception. (str "Test Case Failed:\n"
        "arguments " args " don't produce "
        " result " expected-result \newline
        "Produced " actual-result " instead"))))

(defn- test-and-throw! [fn-to-apply, input, expected-value]
    (let [actual-result  (apply fn-to-apply input)]
        (if (not= expected-value actual-result)
            (throw-exception! (seq input), expected-value, actual-result))))

(defn- loop-through-tests! [fn-to-apply, tests]
    (loop [allkeys (keys tests)]
        (if-not (empty? allkeys)
            (let [input (first allkeys)
                 expected-value (tests input)]
                 (test-and-throw! fn-to-apply input expected-value)
                 (recur (rest allkeys))))))

(defn- get-tests-and-args [first, second]
    "Orders tests first, then arguments to the function second"
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

(defn- build-body
    "re-assembles the body of the intended function"
    [body macro-args]
       (if-not (nil? body)
            (cons body (drop 3 macro-args))
            (drop 2 macro-args)))

(defn- process-args [macro-args]
    (let[{body :body tests :tests
         args :args docs :docs} (classify-args (take 3 macro-args))]
        [tests, args, (or docs "")
           (build-body body macro-args)]))


(defmacro defntest [name, & arguments]
    (let [  [tests args docs body] (process-args arguments)
            result-symbol `(fn [~@args] ~@body) ]
        (loop-through-tests! (eval result-symbol) tests)
        `(defn ~name ~docs [~@args] ~@body)))

(defmacro fntest [args, tests & body]
    (let [result-symbol `(fn [~@args] ~@body)]
        (loop-through-tests! (eval result-symbol) tests)
        result-symbol))