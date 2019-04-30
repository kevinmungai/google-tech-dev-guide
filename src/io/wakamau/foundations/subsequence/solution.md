# The Challenge

Given a string `S` and a set of words `D`, find the longest word in `D` that is a subsequence of `S`.

Word `W` is a subsequence of `S` if some number of characters, possibly zero, can be deleted from `S` to form `W`, without reordering the remaining characters.

Note: `D` can appear in any format (list, hash table, prefix tree, etc.

For example, given the input of `S = "abppplee"` and `D = {"able", "ale", "apple", "bale", "kangaroo"}` the correct output would be "apple"

- The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
- The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
- The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.
Learning objectives

This question gives you the chance to practice with algorithms and data structures. It’s also a good example of why careful analysis for Big-O performance is often worthwhile, as is careful exploration of common and worst-case input conditions.

# my solution

1. First of all we are going to sort the list of words based on their length by creating a custom comparator.

```clojure
(defn reverse-cmp
  "a comparator for reversing the strings based on length"
  [a b]
  (let [len1 (count a)
        len2 (count b)]
    (if (= len1 len2)
      (compare (.toLowerCase a) (.toLowerCase b))
      (- len2 len1))))

=> (sort reverse-cmp ["able" "kangaroo" "ale" "bale" "apple"])
("kangaroo" "apple" "able" "bale" "ale")
```

2. Next we iterate through the sorted list of words and then check if it is a subsequence of the string `S`. We plan on returning the first word that is found to be a subsequence of s, i.e. we try and fail as early as possible.

```clojure
(defn solution
  [s list-of-words]
  (let [sorted-words (sort reverse-cmp list-of-words)]
    (some #(and (is-subsequence? s %) %) sorted-words)))
```

3. Helper functions here:

```clojure
(defn is-present?
  [s-map [letter-key letter-val]]
  (some-> (s-map letter-key)
          (>= letter-val)))

(defn is-subsequence?
  [s w]
  (let [s-map (frequencies s) word-map (frequencies w)]
    (every? #(is-present? s-map %) (into [] word-map))))
```

# Answer

```clojure
=> (solution "abppplee" ["able" "ale" "apple" "bale" "kangaroo"])
"apple"
```

# Lessons learnt

1. The `frequencies` function was a life saver
```clojure
=> (frequencies "abppplee")
{\a 1, \b 1, \p 3, \l 1, \e 2}
```

2. It is much better to write idiomatic `Clojure` code as it is more easier to understand.
