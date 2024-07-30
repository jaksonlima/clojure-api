(ns clojure-api.tweets.database
  (:require [clojure-api.db.db :refer :all]
            [clojure-api.tweets.validation :as v])
  (:import java.util.UUID)
  (:gen-class))


(defn post-tweet
  "Post a tweet to the audience"
  [tweet]
  (let [is-valid (v/validate-tweet tweet)]
    (when is-valid
      (sql-insert-tweet db (assoc tweet :id (UUID/randomUUID))))))

(defn search-tweets-by-username
  [username]
  (let [result (sql-search-tweets-by-username db {:username (str "@" username)})]
    (map #(assoc % :id (str (:id %))) result)))

