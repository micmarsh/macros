(ns marshmacros.core-test
  (:use clojure.test
        marshmacros.core
        [marshmacros.test :only [defntest, fntest]]
        [marshmacros.coffee :only [cofmap cdestruct]]))

(def add-two-things-anon
  (fntest [one two]
    {[1 5] 6 [10 9] 19} (+ one two)))

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
(def fb {:foo "you" :bar "me"})

(deftest coffee-map
  (testing "Apply macro with no arguments generates an empty map"
    (is (= (cofmap) {})))
  (testing "A two element map works as expected"
    (is (= (cofmap y x) {:y {:for-the "lulz"} :x "lulz"}))))

(deftest destructuring
  (testing "cdestruct destructures a single-key map as expected"
    (cdestruct [(for-the) y]
      (is (= for-the "lulz"))))
  (testing "cdestruct can destructure based on lists and leave other things alone"
    (cdestruct [(foo bar) fb
                us (str foo " and " bar)]
                (is (= foo "you"))
                (is (= us "you and me"))))
  (testing "cdestruct is basically the same thing as {:keys ...}"
    (cdestruct [(bar) fb
                {:keys [foo]} fb]
                (is (= foo "you"))
                (is (= bar "me")))))


;TODO: make a 'cdestruct' macro to replace "let" and allow for coffee-style
;destructuring of maps