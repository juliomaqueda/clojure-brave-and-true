(ns brave-and-true.chapter4-test
  (:require [clojure.test :refer :all]
            [brave-and-true.chapter4 :refer :all]))


;; suspects database defined directly as a list of maps, with the same content as reading directly from
;; the tutorial's csv file with: (mapify (parse (slurp filename))), to eschew declaring all that required code.
(def suspects-database '({:name "Edward Cullen", :glitter-index 10}
                         {:name "Bella Swan", :glitter-index 0}
                         {:name "Charlie Swan", :glitter-index 0}
                         {:name "Jacob Black", :glitter-index 3}
                         {:name "Carlisle Cullen", :glitter-index 6}))


;; chapter4.glitter-filter
(deftest glitter-filter-test
  (testing "Testing glitter-filter"
    (let [min-glitter 5
          expected-result '("Edward Cullen" "Carlisle Cullen")]
      (is (= expected-result (glitter-filter min-glitter suspects-database))))))


;; chapter4.append
(deftest append-test
  (testing "Testing append with a new suspect"
    (let [new-suspect {:name "Alice Cullen", :glitter-index 7}
          not-empty? (complement empty?)]
      (is (= (+ 1 (count suspects-database)) (count (append new-suspect suspects-database))))
      (is (empty? (filter #(= (:name %) (:name new-suspect)) suspects-database)))
      (is (not-empty? (filter #(= (:name %) (:name new-suspect)) (append new-suspect suspects-database)))))))


;; chapter4.validate?
(deftest validate?-test
  (testing "Testing validate?"
    (let [suspect {:name "Alice Cullen", :glitter-index 7}]
      (testing "with validations that are met"
        (let [validations {:name contains? :glitter-index contains?}]
          (is (validate? suspect validations))))
      (testing "with validations that are not met"
        (let [validations {:age contains?}]
          (is (not (validate? suspect validations))))))))


;; chapter4.back-to-csv
(deftest back-to-csv-test
  (testing "Testing back-to-csv"
    (let [expected-result "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6"]
      (is (= expected-result (back-to-csv suspects-database))))))


;; Run all tests
(run-tests)