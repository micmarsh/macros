(ns marshmacros.core-test
  (:use clojure.test
        marshmacros.core
        [marshmacros.test :only [defntest]
         marshmacros.coffee :only [cofmap]]))

(defntest new-woot [thing]
        { [3] "woot 3"
         ["yeah"] "woot yeah"}
        "this function is wootastic"
        (str "woot " thing ))

(deftest a-test
  (testing "A defntest function gives its expected output"
    (is (= (new-woot "yeah") "woot yeah"))))

; (def x "lulz")
; (def y {:for-the x})

; (deftest empty-map
;   (testing "Apply macro with no arguments generates an empty map"
;     (is (= (cofmap) {}))))

; (deftest simple-map
;   (testing "A two element map works as expected"
;     (is (= (cofmap y x) {:y {:for-the "lulz"} :x "lulz"}))))