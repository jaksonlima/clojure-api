(ns clojure-api.tweets.validation
  (:require [clojure.string :as str]
            [compojure.core :refer :all])
  (:gen-class))

(def minimum-body-length 1)
(def maximum-body-length 140)

(defn validate-tweet
  "validate if a tweet has all the required data"
  [tweet]
  (let [body (:body tweet)
        username (:username tweet)]
    (and
      (not (empty? tweet))
      (<= minimum-body-length (count body) maximum-body-length)
      (= 0 (str/index-of username "@"))
      (> (count username) 2))
    ))