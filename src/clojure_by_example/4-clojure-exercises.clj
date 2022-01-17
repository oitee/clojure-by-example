(ns clojure-by-example.4-clojure-exercises)

;; problem 20

;; Write a function which returns the second to last element from a sequence.

(defn second-last [xs]
  (get xs (- (count xs) 2)))
;;! this does not work with lists
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
(contains-nil :a {:b "bee" :a nil })

(contains? {:a "a" "b" "bee"} "b")



 