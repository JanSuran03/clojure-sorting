(ns sorting.bubble
  (:require [sorting.util :as util]))

(defn bubble-sort
  [s]
  (let [len-- (dec (count s))
        shuffled-seq (transient s)]
    (time
      (do (print "Bubble sort fast || Num elements:" (util/pprint-integer (count s)) "|| ")
          (loop [i 0
                 j 0
                 s shuffled-seq]
            (cond (= i len--) (persistent! s)
                  (= j len--) (recur (unchecked-inc-int i)
                                     0
                                     s)
                  :else (let [cur (nth s j)
                              cur++ (nth s (unchecked-inc-int j))
                              s (if (> cur cur++)
                                  (assoc! s j cur++
                                          (unchecked-inc-int j) cur)
                                  s)]
                          (recur i
                                 (unchecked-inc-int j)
                                 s))))
          nil))))

(defn bubble-sort-rand [size]
  (let [rand-seq (shuffle (range size))]
    (bubble-sort rand-seq)))