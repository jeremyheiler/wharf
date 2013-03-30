(ns wharf.core
  (:require [clojure.string :as string]
            [clojure.walk :as walk]
            [blancas.kern.core :refer :all]))

(defn transform-keys
  "Recursively transforms all map keys in coll with t."
  [t coll]
  (let [f (fn [[k v]] [(t k) v])]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) coll)))

(defn ^String capitalize
  "Converts the first character of s to upper-case. This differs from
  clojure.string/captialize because it doesn't touch the rest of s."
  [s]
  (str (.toUpperCase (subs s 0 1)) (subs s 1)))

(defn ^String uncapitalize
  "Converts the first character of s to lower-case."
  [s]
  (str (.toLowerCase (subs s 0 1)) (subs s 1)))

(defn parse-dash-case
  [s]
  (value (sep-by (sym* \-) (<+> letter (many1 (<|> letter digit)))) s))

(defn parse-underscore-case
  [s]
  (value (sep-by (sym* \_) (<+> letter (many1 (<|> letter digit))))s ))

(defn parse-camel-case
  [s]
  (value (many1 (<+> letter (many0 (<|> lower digit)))) s))

(defn camel->dash
  [s]
  (->> s
       (parse-camel-case)
       (string/join "-")))

(defn camel->underscore
  [s]
  (->> s
       (parse-camel-case)
       (string/join "_")))

(defn dash->upper-camel
  [s]
  (->> s
       (parse-dash-case)
       (map capitalize)
       (string/join)))

(defn dash->lower-camel
  [s]
  (->> s
       (parse-dash-case)
       (map capitalize)
       (string/join)
       (uncapitalize)))

(defn underscore->upper-camel
  [s]
  (->> s
       (parse-underscore-case)
       (map capitalize)
       (string/join)))

(defn underscore->lower-camel
  [s]
  (->> s
       (parse-underscore-case)
       (map capitalize)
       (string/join)
       (uncapitalize)))

(defn dash->underscore
  "Converts s from dash-case to underscore-case."
  [s]
  (string/replace s #"-" "_"))

(defn underscore->dash
  "Converts s from underscore-case to dash-case."
  [s]
  (string/replace s #"_" "-"))
