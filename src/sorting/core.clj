(ns sorting.core
  (:require [sorting.bubble :as bubble]
            [sorting.merge :as merge]
            [sorting.select :as select]
            [sorting.util :as util]))

(def ^:private bubble* 10000)
(def ^:private select* 5000)
(def ^:private merge* 1000000)
(def ^:private clojure 10000000)

(defn compare-all-sorts []
  (select/select-sort-rand select*)
  (bubble/bubble-sort-rand bubble*)
  (merge/merge-sort-rand merge*)
  (print "Clojure sort: Number of elements:" (util/pprint-integer clojure) "|| ")
  (let [s (shuffle (range clojure))
        _ (time (sort s))]))