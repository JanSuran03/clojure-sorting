(ns sorting.merge
  (:require [sorting.util :as util]))

(defn split-vector [v len]
  (let [half-len (Math/ceil (/ len 2))]
    [(subvec v 0 half-len)
     (subvec v half-len len)]))

(defn merge-vectors [[vec1 vec2]]
  (if (seq vec2)
    (loop [v1 vec1
           v2 vec2
           ret (transient [])]
      (cond (and (seq v1) (seq v2))
            (let [first-v1 (first v1)
                  first-v2 (first v2)]
              (if (< first-v1 first-v2)
                (recur (next v1)
                       v2
                       (conj! ret first-v1))
                (recur v1
                       (next v2)
                       (conj! ret first-v2))))

            (seq v1)
            (persistent! (reduce conj!
                                   ret
                                   v1))
            :else
            (persistent! (reduce conj!
                                 ret
                                 v2))))
    vec1))

(defn- merge-sort* [s]
  (let [len (count s)]
    (if (> len 1)
      (let [[v1 v2] (split-vector s len)]
        (merge-vectors [(merge-sort* v1) (merge-sort* v2)]))
      s)))

(defn merge-sort-rand
  [size]
  (let [size (int size)]
    (print "Merge sort: Number of elements: " (util/pprint-integer size) "|| ")
    (time (merge-sort* (shuffle (range size))))
    nil))

(defn merge-sort
  [s]
  (print "Merge sort: Number of elements: " (util/pprint-integer (count s)) "|| ")
  (time (merge-sort* s)))

(defn compare-sort-and-my-trash-sort [size]
  (merge-sort-rand size)
  (let [s (shuffle (range size))]
    (time (sort s))
    nil))