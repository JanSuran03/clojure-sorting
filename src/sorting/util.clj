(ns sorting.util
  (:require [clojure.string :as str]))

(defn pprint-integer [n]
  (loop [remaining (-> n str str/reverse)
         ret []
         i 0]
    (if (seq remaining)
      (if (zero? (rem i 3))
        (recur (next remaining)
               (conj ret \space (first remaining))
               (inc i))
        (recur (next remaining)
               (conj ret (first remaining))
               (inc i)))
      (->> ret next (apply str) str/reverse))))