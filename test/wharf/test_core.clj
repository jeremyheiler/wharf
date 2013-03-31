(ns wharf.test-core
  (require [clojure.test :refer :all]
           [wharf.core :refer :all]))

(deftest test-capitalize
  (is (= "Hello" (capitalize "hello")))
  (is (= "HELLO" (capitalize "HELLO")))
  (is (= "HElLo" (capitalize "hElLo"))))

(deftest test-uncapitalize
  (is (= "hello" (uncapitalize "hello")))
  (is (= "hELLO" (uncapitalize "HELLO")))
  (is (= "hElLo" (uncapitalize "HElLo"))))

(deftest test-dash->upper-camel
  (is (empty? (dash->upper-camel "")))
  ;(is (= "A" (dash->upper-camel "a")))
  (is (= "Foo" (dash->upper-camel "foo")))
  (is (= "FooBar" (dash->upper-camel "foo-bar")))
  ;(is (= "IFoo" (dash->upper-camel "i-foo")))
  ;(is (= "AB" (dash->upper-camel "a-b")))
  )

(deftest test-dash->lower-camel
  ;(is (empty? (dash->lower-camel "")))
  ;(is (= "a" (dash->lower-camel "a")))
  (is (= "foo" (dash->lower-camel "foo")))
  (is (= "fooBar" (dash->lower-camel "foo-bar")))
  ;(is (= "iFoo" (dash->lower-camel "i-foo")))
  ;(is (= "aB" (dash->lower-camel "a-b")))
  )

(deftest test-camel->dash
  (is (empty? (camel->dash "")))
  (is (= "a" (camel->dash "a")))
  (is (= "foo" (camel->dash "foo")))
  (is (= "Foo-Bar" (camel->dash "FooBar")))
  (is (= "foo-Bar" (camel->dash "fooBar")))
  (is (= "I-Foo" (camel->dash "IFoo")))
  (is (= "S-Q-L-Foo" (camel->dash "SQLFoo")))
  (is (= "foo1-Bar" (camel->dash "foo1Bar")))
  (is (= "Foo-No1se" (camel->dash "FooNo1se"))))

(deftest test-dash->underscore
  (is (empty? (dash->underscore "")))
  (is (= "a" (dash->underscore "a")))
  (is (= "foo" (dash->underscore "foo")))
  (is (= "123" (dash->underscore "123")))
  (is (= "foo_bar" (dash->underscore "foo_bar")))
  (is (= "FOO_BAR" (dash->underscore "FOO-BAR")))
  (is (= "foo_bar_baz" (dash->underscore "foo-bar-baz")))
  (is (= "foo1_bar" (dash->underscore "foo1-bar")))
  (is (= "foo_no1se" (dash->underscore "foo-no1se"))))

(deftest test-underscore->dash
  (is (empty? (underscore->dash "")))
  (is (= "a" (underscore->dash "a")))
  (is (= "foo" (underscore->dash "foo")))
  (is (= "123" (underscore->dash "123")))
  (is (= "foo-bar" (underscore->dash "foo_bar")))
  (is (= "FOO-BAR" (underscore->dash "FOO_BAR")))
  (is (= "foo-bar-baz" (underscore->dash "foo_bar_baz")))
  (is (= "foo1-bar" (underscore->dash "foo1_bar")))
  (is (= "foo-no1se" (underscore->dash "foo_no1se"))))
