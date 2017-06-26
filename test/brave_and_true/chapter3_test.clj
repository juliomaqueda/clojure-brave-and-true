(ns brave-and-true.chapter3-test
  (:require [clojure.test :refer :all]
            [brave-and-true.chapter3 :refer :all]))


;; chapter3.add-100
(deftest add-100-test
  (let [valid-input 5
        invalid-input "a"]
    (testing "Testing add-100"
      (testing "with a valid input"
        (is (= (+ valid-input 100) (add-100 valid-input))))
      (testing "with an invalid input"
        (is (thrown? Exception (add-100 invalid-input)))))
    (testing "Testing add-100-v2"
      (testing "with a valid input"
        (is (= (+ valid-input 100) (add-100-v2 valid-input))))
      (testing "with an invalid input"
        (is (thrown? Exception (add-100-v2 invalid-input)))))
    (testing "Testing add-100-v3"
      (testing "with a valid number"
        (is (= (+ valid-input 100) (add-100-v3 valid-input))))
      (testing "with an invalid input"
        (is (thrown? Exception (add-100-v3 invalid-input)))))))


;; charpet3.dec-maker
(deftest dec-maker-test
  (testing "Testing dec-maker"
    (let [test-number 96]
      (testing "with a valid input"
        (let [dec-by 7
              expected-result 89]
          (is (= expected-result ((dec-maker dec-by) test-number)))))
      (testing "with an invalid input"
        (let [invalid-input +]
          (is (function? (dec-maker invalid-input)))
          (is (thrown? Exception ((dec-maker invalid-input) test-number))))))))


;; chapter3.mapset
(deftest mapset-test
  (let [valid-input '(1 2 3 2 1 5 3)
        invalid-input +
        expected-result #{1 2 3 5}
        func identity]
    (testing "Testing mapset"
      (testing "with valid input"
        (is (= expected-result (mapset func valid-input))))
      (testing "with invalid input"
        (is (thrown? Exception (mapset func invalid-input)))))
    (testing "Testing mapset-v2"
      (testing "with valid input"
        (is (= expected-result (mapset-v2 func valid-input))))
      (testing "with invalid input"
        (is (thrown? Exception (mapset-v2 func invalid-input)))))))


;; chapter3.symmetrize-body-parts-5
(deftest symmetrize-body-parts-5-test
  (let [body-parts [{:name "head" :size 3}
                    {:name "multi-eye" :size 1}
                    {:name "body" :size 1}]
        expected-result [{:name "head" :size 3}
                         {:name "1-eye" :size 1}
                         {:name "2-eye" :size 1}
                         {:name "3-eye" :size 1}
                         {:name "4-eye" :size 1}
                         {:name "5-eye" :size 1}
                         {:name "body" :size 1}]]
    (testing "Testing symmetrize-body-parts-5"
      (is (= expected-result (symmetrize-body-parts-5 body-parts))))
    (testing "Testing symmetrize-body-parts-5-v2"
      (is (= expected-result (symmetrize-body-parts-5-v2 body-parts))))))


;; chapter3.symmetrize-body-parts-multi
(deftest symmetrize-body-parts-multi-test
  (let [body-parts [{:name "head" :size 3}
                    {:name "multi-eye" :size 1}
                    {:name "body" :size 1}]]
    (testing "Testing symmetrize-body-parts-multi"
      (testing "with a positive multiplier"
        (let [positive-multiplier 3
              expected-result [{:name "head" :size 3}
                               {:name "1-eye" :size 1}
                               {:name "2-eye" :size 1}
                               {:name "3-eye" :size 1}
                               {:name "body" :size 1}]]
          (is (= expected-result (symmetrize-body-parts-multi body-parts positive-multiplier)))))
      (testing "with a negative or zero multiplier"
        (let [negative-multiplier -3
              zero-multiplier 0
              expected-result [{:name "head" :size 3}
                               {:name "body" :size 1}]]
          (is (= expected-result (symmetrize-body-parts-multi body-parts negative-multiplier)))
          (is (= expected-result (symmetrize-body-parts-multi body-parts zero-multiplier))))))
    (testing "Testing symmetrize-body-parts-multi-v2"
      (testing "with a positive multiplier"
        (let [positive-multiplier 3
              expected-result [{:name "head" :size 3}
                               {:name "1-eye" :size 1}
                               {:name "2-eye" :size 1}
                               {:name "3-eye" :size 1}
                               {:name "body" :size 1}]]
          (is (= expected-result (symmetrize-body-parts-multi-v2 body-parts positive-multiplier)))))
      (testing "with a negative or zero multiplier"
        (let [negative-multiplier -3
              zero-multiplier 0
              expected-result [{:name "head" :size 3}
                               {:name "body" :size 1}]]
          (is (= expected-result (symmetrize-body-parts-multi-v2 body-parts negative-multiplier)))
          (is (= expected-result (symmetrize-body-parts-multi-v2 body-parts zero-multiplier))))))))


;; Run all tests
(run-tests)