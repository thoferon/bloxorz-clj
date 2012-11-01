(ns bloxorz.game
  (:use [bloxorz.block] [bloxorz.playground]))

(def playground-example (set (for [x (range 10) y (range 10)] [x y])))

;; a solution is a list of actions (:left, :right, :up or :down)

(defn next-moves
  [sols]
  (for [sol  sols
        move (list :left :right :up :down)]
    (cons move sol)))

(def solutions
  (lazy-cat '(()) (next-moves solutions)))

(defn safe-move
  [block playground direction]
  (let [new-block (move block direction)]
    (when (contains-block? playground new-block) new-block)))

(defn execute-solution
  [init playground solution]
  (reduce #(safe-move % playground %2) init solution))

(defn resolve-game
  [playground init end]
  (first (filter #(= (execute-solution init playground %) end) solutions)))

;; try (resolve-game playground-example '([5 6]) '([0 0] [0 1]))
