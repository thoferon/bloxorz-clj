(ns bloxorz.block)

;; blocks are just lists of positions (1 or 2 ordered) while positions are vectors of integers

(defn- vertical? [[[x1 _] [x2 _]]] (= x1 x2))
(defmulti move
  (fn [block direction] [(count block) (vertical? block) direction]))

(defmethod move [1 false :left]   [[[x y]] _]           (list [(- x 2) y] [(- x 1) y]))
(defmethod move [1 false :right]  [[[x y]] _]           (list [(+ x 1) y] [(+ x 2) y]))
(defmethod move [1 false :up]     [[[x y]] _]           (list [x (+ y 1)] [x (+ y 2)]))
(defmethod move [1 false :down]   [[[x y]] _]           (list [x (- y 2)] [x (- y 1)]))

(defmethod move [2 true :left]    [[[x1 y1] [x2 y2]] _] (list [(dec x1) y1] [(dec x2) y2]))
(defmethod move [2 true :right]   [[[x1 y1] [x2 y2]] _] (list [(inc x1) y1] [(inc x2) y2]))
(defmethod move [2 true :up]      [[[x1 y1] [x2 y2]] _] (list [x1 (inc y2)]))
(defmethod move [2 true :down]    [[[x1 y1] [x2 y2]] _] (list [x1 (dec y1)]))

(defmethod move [2 false  :left]  [[[x1 y1] [x2 y2]] _] (list [(dec x1) y1]))
(defmethod move [2 false  :right] [[[x1 y1] [x2 y2]] _] (list [(inc x2) y1]))
(defmethod move [2 false  :up]    [[[x1 y1] [x2 y2]] _] (list [x1 (inc y1)] [x2 (inc y2)]))
(defmethod move [2 false  :down]  [[[x1 y1] [x2 y2]] _] (list [x1 (dec y1)] [x2 (dec y2)]))

(defmethod move :default [& args] nil)
