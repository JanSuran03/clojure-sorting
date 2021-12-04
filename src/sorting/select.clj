(ns sorting.select
  (:require [sorting.util :as util]))

(defn find-min-idx [coll]
  (let [indexed (map-indexed (fn [i val]
                               [i val])
                             coll)]
    (loop [[min-idx min-val :as old-min] (first indexed)
           coll (rest indexed)]
      (if (seq coll)
        (let [[_ val :as new-min] (first coll)]
          (recur (if (< val min-val)
                   new-min
                   old-min)
                 (rest coll)))
        min-idx))))

(defn select-sort [coll]
  (do (print "Select sort || Num elements:" (util/pprint-integer (count coll)) "|| ")
      (time (loop [coll coll
                   ret (transient [])]
              (if (seq coll)
                (let [min-idx (find-min-idx coll)
                      elem (nth coll min-idx)
                      new-coll (concat (take min-idx coll)
                                       (drop (inc min-idx) coll))]
                  (recur new-coll
                         (conj! ret elem)))
                (persistent! ret))))))

(defn select-sort-rand [size]
  (let [rand-seq (shuffle (range size))]
    (select-sort rand-seq)))