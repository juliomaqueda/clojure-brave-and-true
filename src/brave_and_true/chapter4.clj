(ns brave-and-true.chapter4
  (:gen-class))


;; 1. Turn the result of your glitter filter into a list of names.

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))


;; 2. Write a function, append, which will append a new suspect to your list of suspects.

(defn append [suspect suspects-list]
  (conj suspects-list suspect))


;; 3. Write a function, validate, which will check that :name and :glitter-index are present when you append.
;; The validate function should accept two arguments: a map of keywords to validating functions, similar
;; to conversions, and the record to be validated.

(defn validate? [suspect validations]
  (reduce #(and %1 %2)
          (map (fn [[key func]]
                 (func suspect key))
               validations)))


;; 4. Write a function that will take your list of maps and convert it back to a CSV string.
;; You’ll need to use the clojure.string/join function.

(defn back-to-csv [suspects-list]
  (clojure.string/join
    "\n"
    (map (fn [suspect] (clojure.string/join "," (vals suspect)))
         suspects-list)))