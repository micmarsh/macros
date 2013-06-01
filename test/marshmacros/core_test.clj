(ns marshmacros.core-test
  (:use clojure.test
        marshmacros.core
        [marshmacros.test :only [defntest, fntest]]
        [marshmacros.coffee :only [cofmap]]))

(def add-two-things-anon
  (fntest [one two]
    {[1 2] 3 [10 9] 19} (+ one two)))
;TODO: this shit's not passing for seem reason! need to go into fntest
;to see what's going on. After that, can make a hot closure for construting
;the map in either {:key val} or {val (technically symbol) :key} order.

;Then can get back to planning and implementing state for hotkeys!

(defntest new-woot [thing]
        { [3] "woot 3"
         ["yeah"] "woot yeah"}
        "this function is wootastic"
        (str "woot " thing ))

(deftest defntest-test
  (testing "A defntest function gives its expected output"
    (is (= (new-woot "yeah") "woot yeah"))))

(deftest fntest-test
  (testing "A fntest function gives its expected output"
    (is (= (add-two-things-anon 5 8) 13))))

(def x "lulz")
(def y {:for-the x})

(deftest empty-map
  (testing "Apply macro with no arguments generates an empty map"
    (is (= (cofmap) {}))))

(deftest simple-map
  (testing "A two element map works as expected"
    (is (= (cofmap y x) {:y {:for-the "lulz"} :x "lulz"}))))