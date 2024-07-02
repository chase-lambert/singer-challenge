(ns singer.core 
  (:require
   [clojure.data.json :as json]
   [singer.generate-sample-json :refer [generate-sample-json]]))

(defn parse-stream [input]
  (-> (slurp input)
      (json/read-str :key-fn keyword)))

(defn sort-stream [input]
  (sort-by :stream input))

(defn write-stream-sorted [input]
  (println (json/write-str input)))

(defn stream-switches [input]
  (let [streams (map :stream input)]
    (->> (partition 2 1 streams) ;; => (("user" "user") ("user" "user") ("user" "order") ("order" "user")
         (filter (fn [[a b]] (not= a b)))
         (count))))
                
(defn -main []
  (generate-sample-json 10)
  (let [input  (parse-stream "resources/sample-input.json")
        sorted (sort-stream input)
        original-stream-switches (stream-switches input)
        sorted-stream-switches   (stream-switches sorted)]
    (write-stream-sorted sorted)
    (println)
    (println "Results:")
    (println {:before original-stream-switches
              :after  sorted-stream-switches})))

(comment 
  ,)
  
