# macros

Some clojure macros, so far just "defntest"

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
In coffeescript (probably my other favorite language) you can do this:
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

##TODO
* Allow doc strings in addition to tests and arguments
* Support for '[& args]' form in functions definitions
* Arbitrary ordering of arguments vector and test map relative to one another
* Defining single arguments in test map (use a 'seq?' check)
* Allow pre-evalution input and results in maps (probably related to how macros work in general)
* 'or' results for random functions

* Support for multi-arity functions


## License

Copyright © 2013 Michael Marsh

Distributed under the Eclipse Public License, the same as Clojure.