(ns io.wakamau.foundations.subsequence.subsequence)

(defn reverse-cmp
  "a comparator for reversing the strings based on length"
  [a b]
  (let [len1 (count a)
        len2 (count b)]
    (if (= len1 len2)
      (compare (.toLowerCase a) (.toLowerCase b))
      (- len2 len1))))

(def s "abppplee")
(def d (vector "able" "kangaroo" "ale" "bale" "apple"))

(defn is-present?
  [s-map [letter-key letter-val]]
  (some-> (s-map letter-key)
          (>= letter-val)))

(defn is-subsequence?
  [s w]
  (let [s-map (frequencies s) word-map (frequencies w)]
    (every? #(is-present? s-map %) (into [] word-map))))

(defn solution
  [s list-of-words]
  (let [sorted-words (sort reverse-cmp list-of-words)]
    (some #(and (is-subsequence? s %) %) sorted-words)))
