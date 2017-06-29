## Control Flow

### if

```
(if boolean-form
  then-form
  optional-else-form)
```

A Boolean form is just a form that evaluates to a truthy or falsey value. You can also omit the `else` branch. If you do that and the Boolean expression is `false`, Clojure returns `nil`.

`if` uses operand position to associate operands with the `then` and `else` branches: the first operand is the `then` branch, and the second operand is the (optional) `else` branch.

As a result, each branch can have only one form. This is different from most languages, but you can perform more than a single operation by using the `do` operator.


### do

The `do` operator lets you wrap up multiple forms in parentheses and run each of them.

```
(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
; => Success!
; => "By Zeus's hammer!"
```

This operator lets you do multiple things in each of the `if` expression's branches. In this case, two things happen: `Success!` is printed in the REPL, and `"By Zeus's hammer!"` is returned as the value of the entire `if` expression.