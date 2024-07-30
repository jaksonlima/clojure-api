(ns clojure-api.validation
  (:require [clojure-api.tweets.validation :refer :all]
            [clojure.test :refer :all]))

(deftest validation-test
  (testing "Should be valid"
    (is (true? (validate-tweet {:body "test" :username "@lima"}))))

  (testing "Should fail when there is no body"
    (is (false? (validate-tweet {:body "" :username "@lima"}))))

  (testing "Should fail when there is no username"
    (is (false? (validate-tweet {:body "test" :username "lima"})))))

