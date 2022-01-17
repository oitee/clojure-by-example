(ns clojure-by-example.4-clojure-exercises)

;; problem 20

;; Write a function which returns the second to last element from a sequence.

(defn second-last [xs]
  (get xs (- (count xs) 2)))
;; this does not work with lists
;; because 'nth' works with lists and 'get' does not

(second-last [1 2 3])
;; => 2

(second-last '(1 2 3))
;;=> nil

(defn second-last-v2 [xs]
  (last (take (- (count xs) 1) xs)))
;; this works with list


(second-last-v2 '(1 2 3))

(second-last-v2 [1 2 3])


(let [xs [1 2 3]] (get xs (- (count xs) 2)))



;; problem 19
;; Write a function which returns the last element in a sequence.
;; Special restrictions: last

(defn my-last
  [xs]
  (first (drop 1 (reverse xs))))

(my-last [1 2 3])
(my-last '(1 2 3))


;; problem 48
;; The some function takes a predicate function and a collection. 
;; It returns the first logical true value of (predicate x) where x is an item in the collection.

(defn my-some
  [f xs]
  (if (zero? (count xs)) nil
      (if (f (first xs))
        (first xs)
        (my-some f (rest xs)))))


(some #(if (even? %) %) [1 3 5])

(my-some #(if (even? %) %) [1 3 5])

(my-some #(if (even? %) %) '(1 3 5 8))

(= (my-some #{2 7 6} [5 6 7 8]) (some #{2 7 6} [5 6 7 8]))


;; Problem 57
;; A recursive function is a function which calls itself. 
;; This is one of the fundamental techniques used in functional programming.
;; Problem:
(comment (= __ ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5)))

;; answer:
'(5 4 3 2 1)

;; Problem 134
;; Write a function which, given a key and map, 
;; returns true iff the map contains an entry with that key and its value is nil.

(defn contains-nil
  [k, m]
  (if (contains? m k)
    (nil? (get m k))
    false))

(contains-nil :a {:a "a" :b "b"})
(contains-nil "a" {"a" "aa", :b "b"})
(contains-nil :a {:b "bee" :a nil})

(contains? {:a "a" "b" "bee"} "b")


;; Problem 27
;;Write a function which returns true if the given sequence is a palindrome. 
;;Hint: "racecar" does not equal '(\r \a \c \e \c \a \r)

(defn is-palindrome 
  [xs]
  (let [xs-collection (if (string? xs)
                        (clojure.string/split xs #"")
                        xs)]
    (= (reverse xs-collection) xs-collection)))

(is-palindrome [1 2 3 2 1])
(is-palindrome "madam")
(is-palindrome "123")
(is-palindrome "racecar")


;; Alternative Solution to Problem 27 (without using `reverse`)

(defn is-palindrome-v2
 [xs]
 (let [xs-seq (if 
           (string? xs)
            (clojure.string/split xs #"")
            xs)]
   (if (zero? (count xs-seq))
     true 
     (if (= (first xs-seq) (last xs-seq))
            (is-palindrome-v2
             (rest (take (- (count xs-seq) 1) xs-seq)))
            false))))



(is-palindrome-v2 [1 2 3 2 1])
(is-palindrome-v2 [1 2])
(is-palindrome-v2 "madam")
(is-palindrome-v2 "racecar")
(is-palindrome-v2 "raceca1")


;; Problem 26
;; Write a function which returns the first X fibonacci numbers.

(defn fibonacci
  [n]
  (if (= n 1)
    '(1)
    (if (= n 2)
      '(1 1)
      (let [latest (fibonacci (- n 1))
            size (count latest)
            last-element (nth latest (- size 1))
            second-last-element (nth latest (- size 2))
            new-element (+ last-element second-last-element)]
        (conj (vec latest) new-element)
        ))))

(= (fibonacci 8) '(1 1 2 3 5 8 13 21))
(= '(1 2 3 4) (conj (vec '(1 2 3)) 4))


