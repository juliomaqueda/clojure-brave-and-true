(ns brave-and-true.chapter5
  (:gen-class))


;; 1. You used (comp :intelligence :attributes) to create a function that returns a character’s intelligence.
;; Create a new function, attr, that you can call like (attr :intelligence) and that does the same thing.
(defn attr [attrib]
  (comp attrib :attributes))


;; 2. Implement the comp function.
(defn my-comp
  ([f] f)
  ([f & fs]
   (fn [& args]
     (f (apply (apply my-comp fs) args)))))


;; 3. Implement the assoc-in function.
;; Hint: use the assoc function and define its parameters as [m [k & ks] v].
(defn my-assoc-in [m [k & ks] v]
  (if (empty? ks)
    (assoc m k v)
    (assoc m k (my-assoc-in (get m k) ks v))))


;; 5. Implement update-in.
(defn my-update [m [& keys] func & args]
  (assoc-in m keys (apply func (get-in m keys) args)))