(ns singer.generate-sample-json
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(defn random-stream-value []
  (rand-nth ["user" "order"]))

(defn generate-task [stream]
  (str "task to perform for " stream " stream"))

(defn generate-object []
  (let [stream-val (random-stream-value)]
    {:type   "report"
     :stream stream-val
     :task   (generate-task stream-val)}))

(defn generate-json-data [num-objects]
  (mapv (fn [_] (generate-object)) (range num-objects)))

(defn write-json-to-file [filename data]
  (with-open [writer (io/writer filename)]
    (json/write data writer :pretty true)))

(defn generate-sample-json [num-objects]
  (let [data (generate-json-data num-objects)
        filename "resources/sample-input.json"]
    (write-json-to-file filename data)))


