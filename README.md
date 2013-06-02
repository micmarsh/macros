# macros

Some clojure macro

## Usage

### defntest
```clojure
(defntest addThreeThings
    {[1 2 3] 6
     [3 1 4] 8
     [0 6 7] 13}
     [x y z]
     (+ x y z))
; defines 'add', and runs given test cases, throwing an exception if one doesn't pass
```
### coffee-script inspired macros: cofmap and cdestruct
In [coffeescript](http://www.coffeescript.org)  (and JavaScript in es6) you can do this:
```coffeescript
food = "turkey"
drink = "water"
exercise = "squats"
hashMap = {food, drink, exercise}
#hashMap is {food: food, drink: drink, exercise: exercise}
```
This is super useful, so I wrote "cofmap" to do the same thing in clojure:
```clojure
(def author "Stephen Covey")
(def book "7 Habits")
(def the-map (cofmap author book))
;(= the-map {:author author :book book})
```
Also, I made cdestruct, inspired by [coffee's destructuring assignment](http://coffeescript.org/#destructuring) before I realized that {:keys [...]} was a thing. Here it goes:
```clojure
;writing this:
(cdestruct [(name book) the-map
            A_CONSTANT "sup"]
    (= name "Stephen Covey")
    (= book "7 Habits")
    (= A_CONSTANT "sup"))
;is equivalent to:
(let [{:keys [name book]} the-map
      A_CONSTANT "sup"]
    (= name "Stephen Covey")
    (= book "7 Habits")
    (= A_CONSTANT "sup"))
```
cdestruct is only marginally as useful as I thought it would be when I wrote it, but it was fun writing anyway

##TODO
### defntest
* a "defntest-" form for private functions with a namespace
* Support for '[& args]' form in functions definitions
* Arbitrary ordering of arguments vector and test map relative to one another
* Defining single arguments in test map (use a 'seq?' check)
* Allow pre-evalution input and results in maps (probably related to how macros work in general)
* 'or' results for random functions
* Ability to define multi-arity functions

## License

Copyright © 2013 Michael Marsh

Distributed under the Eclipse Public License, the same as Clojure.
