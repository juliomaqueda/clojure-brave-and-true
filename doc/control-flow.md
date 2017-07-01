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


### when

The `when` operator is like a combination of `if` and `do`, but with no `else` branch.

```
(when true
  (println "Success!")
  "abra cadabra")
; => Success!
; => "abra cadabra"
```

Use `when` if you want to do multiple things when some condition is true, and you always want to return `nil` when the condition is false.


### Boolean Expressions

Clojure has `true` and `false` values. `nil` is used to indicate no value in Clojure. You can check if a value is `nil` with the appropriately named `nil?` function.

```
(nil? 1)
; => false

(nil? nil)
; => true
```

Both `nil` and `false` are used to represent logical falsiness, whereas all other values are logically truthy.

Clojure uses the Boolean operators `or` and `and`. `or` returns either the first truthy value or the last value. `and` returns the first falsey value or, if no values are falsey, the last truthy value.

```
(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
; => :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
; => false

(or nil)
; => nil

(and :free_wifi :hot_coffee)
; => :hot_coffee

(and :feelin_super_cool nil false)
; => nil
```