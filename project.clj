(defproject wharf "0.1.0-SNAPSHOT"
  :description "A library for transforming map keys."
  :url "https://github.com/jeremyheiler/wharf"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.blancas/kern "0.6.1"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}})
