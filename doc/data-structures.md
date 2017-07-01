## Data Structures

All of Clojure’s data structures are immutable, meaning you can’t change them in place.


### Numbers

Clojure has pretty sophisticated numerical support.

http://clojure.org/data_structures#Data%20Structures-Numbers


### Strings

Clojure only allows double quotes to delineate strings. It doesn’t have string interpolation. It only allows concatenation via the `str` function.


### Maps

Maps are similar to dictionaries or hashes in other languages. They’re a way of associating some value with some other value. The two kinds of maps in Clojure are hash maps and sorted maps.

In this example, `:first-name` and `:last-name` are keywords:

```
{:first-name "Charlie"
 :last-name "McFishwich"}
```

Maps can be nested:

```
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}
```

Notice that map values can be of any type—strings, numbers, maps, vectors, even functions.

Besides using map literals, you can use the `hash-map` function to create a map:

```
(hash-map :a 1 :b 2)
; => {:a 1 :b 2}
```

You can look up values in maps with the `get` function:

```
(get {:a 0 :b 1} :b)
; => 1
 
(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}
```

`get` will return `nil` if it doesn’t find your key, or you can give it a default value to return, such as "unicorns?":

```
(get {:a 0 :b 1} :c "unicorns?")
; => "unicorns?"
```

The `get-in` function lets you look up values in nested maps:

```
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
; => "ho hum"
```

Another way to look up a value in a map is to treat the map like a function with the key as its argument, or use keywords as functions to look up the map values:

```
({:name "The Human Coffeepot"} :name)
; => "The Human Coffeepot"
 
(:name {:name "The Human Coffeepot"})
; => "The Human Coffeepot"
```

### Keywords

They’re primarily used as keys in maps, but also can be used as functions that look up the corresponding value in a data structure. You can provide a default value, as with `get`:

```
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")
; => "No gnome knows homes like Noah knows"
```


### Vectors

A vector is similar to an array, in that it’s a 0-indexed collection. Vector elements can be of any type.

Here we’re returning elements from a vector by index:

```
(get [3 2 1] 0)
; => 3
 
(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
; => {:name "Pugsley Winterbottom"}
```

You can create vectors with the `vector` function:

```
(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]
```

Use the `conj` function to add additional elements to the vector (elements are added to the _end_ of a vector):

```
(conj [1 2 3] 4)
; => [1 2 3 4]
```


### Lists

Lists are similar to vectors in that they’re linear collections of values. But there are some differences. For example, you can’t retrieve list elements with `get`. To write a list literal, just insert the elements into parentheses and use a single quote at the beginning:

```
'(1 2 3 4)
; => (1 2 3 4)
```

Notice that when the REPL prints out the list, it doesn’t include the single quote.

If you want to retrieve an element from a list, you can use the `nth` function:

```
(nth '(:a :b :c) 0)
; => :a
 
(nth '(:a :b :c) 2)
; => :c
```

Using `nth` to retrieve an element from a list is slower than using `get` to retrieve an element from a vector, because Clojure has to traverse all n elements of a list to get to the nth, whereas it only takes a few hops at most to access a vector element by its index.

List values can have any type, and you can create lists with the `list` function:

```
(list 1 "two" {3 4})
; => (1 "two" {3 4})
```

Elements can be added to the beginning of a list by using `conj`:

```
(conj '(1 2 3) 4)
; => (4 1 2 3)
```

When should you use a list and when should you use a vector? A good rule of thumb is that if you need to easily add items to the beginning of a sequence or if you’re writing a macro, you should use a list. Otherwise, you should use a vector.


### Sets

Sets are collections of unique values. Clojure has two kinds of sets: hash sets and sorted sets.

Here’s the literal notation for a hash set:

```
#{"kurt vonnegut" 20 :icicle}
```

You can also use `hash-set` to create a set:

```
(hash-set 1 1 2 2)
; => #{1 2}
```

Note that multiple instances of a value become one unique value in the set. If you try to add a value to a set that already contains that value, it will still have only one of that value:

```
(conj #{:a :b} :b)
; => #{:a :b}
```

You can also create sets from existing vectors and lists by using the `set` function:

```
(set [3 3 3 4 4])
; => #{3 4}
```

You can check for set membership using the `contains?` function, by using `get`, or by using a keyword as a function with the set as its argument. `contains?` returns `true` or `false`, whereas `get` and keyword lookup will return the value if it exists, or `nil` if it doesn’t.

```
(contains? #{:a :b} :a)
; => true
 
(contains? #{:a :b} 3)
; => false
 
(contains? #{nil} nil)
; => true
 
(:a #{:a :b})
; => :a
 
(get #{:a :b} :a)
; => :a
 
(get #{:a :b} "kurt vonnegut")
; => nil
 
(get #{:a nil} nil)
; => nil
```

Notice that using `get` to test whether a set contains `nil` will always return `nil`, which is confusing. `contains?` may be the better option when you’re testing specifically for set membership.