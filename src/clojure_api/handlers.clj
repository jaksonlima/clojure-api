(ns clojure-api.handlers
  (:require [clojure-api.tweets.database :as d]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all])
  (:gen-class))

(defn post-twitter-handler
  [req]
  (let [tweet-json (:body req)
        saved? (try
                 (d/post-tweet tweet-json)
                 (catch Exception e
                   (do
                     (log/error e)
                     false)))]
    (log/info tweet-json)
    {:status  (if saved? 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved?)
                "error or saving tweet")}))

(defn get-twitter-handler
  [req]
  (log/info req)
  (let [username (-> req :params :username)
        tweets (d/search-tweets-by-username username)]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str tweets)}))