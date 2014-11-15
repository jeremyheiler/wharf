(ns wharf.core
  (:require [clojure.string :as string]
            [clojure.walk :as walk]))

(defn transform-keys
  "Recursively transforms all map keys in coll with t."
  [t coll]
  (let [f (fn [[k v]] [(t k) v])]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) coll)))

(defn capitalize
  "Converts the first character of s to upper-case. This differs from
  clojure.string/captialize because it doesn't touch the rest of s."
  [s]
  (str (.toUpperCase (subs s 0 1)) (subs s 1)))

(defn uncapitalize
  "Converts the first character of s to lower-case."
  [s]
  (str (.toLowerCase (subs s 0 1)) (subs s 1)))

(defn split-on-hyphen
  "Splits a string on hyphens."
  [s]
  (string/split s #"-"))

(defn split-on-underscore
  "Splits a string on undescores."
  [s]
  (string/split s #"_"))

(defn split-camel-case
  "Splits a camel case string into tokens. Consecutive captial lets,
  except for the last one, become a single token."
  [s]
  (-> s
      (.replaceAll "([A-Z]+)([A-Z][a-z])" "$1-$2")
      (.replaceAll "([a-z\\d])([A-Z])" "$1-$2")
      (split-on-hyphen)))

(defn split-camel-case-sticky
  "Splits a camel case string, keeping consecutive capital characters
  attached to the following token."
  [s]
  (split-on-hyphen (.replaceAll s "([a-z\\d])([A-Z])" "$1-$2")))

(defn camel->hyphen
  ([s]
     (camel->hyphen s true))
  ([s sticky-caps]
     (string/join "-" (split-camel-case s))))

(defn camel->underscore
  [s]
  (string/join "_" (split-camel-case s)))

(defn hyphen->upper-camel
  [s]
  (if (seq s)
    (->> s
         (split-on-hyphen)
         (map capitalize)
         (string/join))
    ""))

(defn hyphen->lower-camel
  [s]
  (if (seq s)
    (->> s
         (split-on-hyphen)
         (map capitalize)
         (string/join)
         (uncapitalize))
    ""))

(defn underscore->upper-camel
  [s]
  (->> s
       (split-on-underscore)
       (map capitalize)
       (string/join)))

(defn underscore->lower-camel
  [s]
  (->> s
       (split-on-underscore)
       (map capitalize)
       (string/join)
       (uncapitalize)))

(defn hyphen->underscore
  [s]
  (string/replace s #"-" "_"))

(defn underscore->hyphen
  [s]
  (string/replace s #"_" "-"))
