(ns brave-and-true.chapter5-test
  (:require [clojure.test :refer :all]
            [brave-and-true.chapter5 :refer :all]))


;; chapter5.attr
(deftest attr-test
  (let [character {:name "Smooches McCutes" :attributes {:intelligence 10 :strength 4 :dexterity 5}}
        valid-attrib :intelligence
        invalid-attrib :speed]
    (testing "Testing attr"
      (testing "with valid attribute"
        (is ((attr valid-attrib) character)))
      (testing "with invalid attribute"
        (is (nil? ((attr invalid-attrib) character)))))))


;; chapter5.my-comp
(deftest my-comp-test
  (testing "Testing my-comp with valid functions"
    (is (= 5 ((my-comp inc #(* % 2)) 2)))))


;; chapter5.my-assoc-in
(deftest my-assoc-in-test
  (let [data {:attributes {:intelligence 0}}
        single-key [:attributes]
        multiple-key [:attributes :intelligence]]
    (testing "Testing my-assoc-in"
      (testing "with a single key"
        (is (= {:attributes 50} (my-assoc-in data single-key 50))))
      (testing "with multiple key"
        (is (= {:attributes {:intelligence 50}} (my-assoc-in data multiple-key 50)))))))


;; chapter5.my-update-in
(deftest my-update-in-test
  (let [users [{:name "James" :age 26} {:name "John" :age 43}]
        keys [1 :age]]
    (testing "Testing my-update"
      (testing "with single incremental function"
        (is (= [{:name "James" :age 26} {:name "John" :age 44}] (my-update-in users keys inc))))
      (testing "with function and params"
        (is (= [{:name "James" :age 26} {:name "John" :age 49}] (my-update-in users keys + 1 2 3)))))))


;; Run all tests
(run-tests)