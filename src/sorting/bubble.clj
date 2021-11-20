(ns sorting.bubble
  (:require [sorting.util :as util]))

(defn bubble-sort
  [s]
  (let [len-- (dec (count s))
        shuffled-seq (transient s)]
    (time
      (do (print "Bubble sort fast || Num elements:" (util/pprint-number (count s)) "|| ")
          (loop [i 0
                 j 0
                 s shuffled-seq]
            (cond (= i len--) (persistent! s)
                  (= j len--) (recur (inc i)
                                     0
                                     s)
                  :else (let [cur (nth s j)
                              cur++ (nth s (inc j))
                              s (if (> cur cur++)
                                  (assoc! s j cur++
                                          (inc j) cur)
                                  s)]
                          (recur i
                                 (inc j)
                                 s))))
          nil))))

(defn bubble-sort-rand [size]
  (let [rand-seq (shuffle (range size))]
    (bubble-sort rand-seq)))