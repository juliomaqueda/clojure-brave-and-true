(ns brave-and-true.chapter3
  (:gen-class))


;; 2. Write a function that takes a number and adds 100 to it.

(defn add-100 [num]
  (+ 100 num))

(def add-100-v2 #(+ % 100))

(def add-100-v3 (partial + 100))


;; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
;; (def dec9 (dec-maker 9))
;; (dec9 10)
;; => 1

(defn dec-maker [dec-by]
  #(- % dec-by))


;; 4. Write a function, mapset, that works like map except the return value is a set:
;; (mapset inc [1 1 2 2])
;; => #{2 3}

(defn mapset [f elems]
  (loop [remaining elems result #{}]
    (if (empty? remaining)
      result
      (let [[elem & more] remaining]
        (recur more
               (into result (seq [(f elem)])))))))

(defn mapset-v2 [func elems]
  (reduce (fn [result elem]
            (into result (seq [(func elem)])))
          #{}
          elems))


;; 5. Create a function that’s similar to symmetrize-body-parts except that it has to work with
;; weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.

(defn matching-parts-5 [part]
  (if (clojure.string/starts-with? (:name part) "multi-")
    (let [num_parts 5]
      (loop [iteration 1 list_of_parts []]
        (if (> iteration num_parts)
          list_of_parts
          (recur (inc iteration)
                 (into list_of_parts
                       [{:name (clojure.string/replace (:name part) #"^multi-" (str iteration "-"))
                         :size (:size part)}])))))
    (vector part)))

(defn symmetrize-body-parts-5 [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts (matching-parts-5 part)))))))

(defn symmetrize-body-parts-5-v2 [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-parts-5 part)))
          []
          asym-body-parts))


;; 6. Create a function that generalizes symmetrize-body-parts and the function you created in Exercise 5.
;; The new function should take a collection of body parts and the number of matching body parts to add.

(defn matching-parts-multi [part multiplier]
  (if (clojure.string/starts-with? (:name part) "multi-")
    (loop [iteration 1 list_of_parts []]
      (if (> iteration multiplier)
        list_of_parts
        (recur (inc iteration)
               (into list_of_parts
                     [{:name (clojure.string/replace (:name part) #"^multi-" (str iteration "-"))
                       :size (:size part)}]))))
    (vector part)))

(defn symmetrize-body-parts-multi [asym-body-parts multiplier]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts (matching-parts-multi part multiplier)))))))

(defn symmetrize-body-parts-multi-v2 [asym-body-parts multiplier]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-parts-multi part multiplier)))
          []
          asym-body-parts))