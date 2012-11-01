(ns bloxorz.playground)

;; a playground is a set of positions

(defn contains-block?
  [playground block]
  (every? #(playground %) block))

