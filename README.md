# macros

Some clojure macros, so just just "defntest"

## Usage

```clojure
(defntest add [& args]
    {'(1 2 3) 6
     '(3 4) 7
     '(6 7) 13}
     (apply + args))
; defines 'add', and runs given test cases, throwing an exception if one doesn't pass
```


## License

Copyright © 2013 Michael Marsh

Distributed under the Eclipse Public License, the same as Clojure.