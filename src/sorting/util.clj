(ns sorting.util
  (:require [clojure.string :as str]))

(defn pprint-integer [n]
  (loop [remaining (-> n str str/reverse)
         ret (transient [])
         i 0]
    (if (seq remaining)
      (if (zero? (rem i 3))
        (recur (next remaining)
               (-> ret (conj! \space)
                   (conj! (first remaining)))
               (inc i))
        (recur (next remaining)
               (conj! ret (first remaining))
               (inc i)))
      (->> ret persistent! next (apply str) str/reverse))))