# Wharf

> */(h)wôrf/* A level area to which a ship may be moored to load and
unload.

Wharf is a Clojure library for transforming map keys. It uses
`clojure.walk/postwalk` and comes bundled with many key trasnformers.
The use case that pushed Wharf into existance is to be able to
subscribe to and publish JSON APIs with out littering your Clojure
code with underscores and camel case variables.

## Usage

The following examples assume `wharf.core` is required.

```clojure
(require '[wharf.core :refer :all])
```

The main function is `transform-keys`, which will walk the provided
collection and trasnform all the keys using the provided function.

```clojure
(transform-keys clojure.string/upper-case {"foo" "bar"})
;=> {"FOO" "bar"}
```

The function you pass in can be any function that accepts one
argument. The example below uses the `keyword` function.

```clojure
(transform-keys keyword {"foo" "bar" "goo" "gar"})
;=> {:foo "bar", :goo "gar"}
```

One common idiom is to compose functions.

```clojure
(transform-keys (comp keyword uncapitalize) {"Foo" "bar"})
;=> {:foo "bar"}
```

There are also some case-conversion functions available.

```clojure
(transform-keys camel->hyphen {"FooBar" "baz"})
;=> {"foo-bar" "baz"}
```

Here is how you would convert a camel-case string to a dash-case
keyword and vice versa.

```clojure
(transform-keys (comp keyword clojure.string/lower-case camel->hyphen) {"FooBar" "baz"})
;=> {:foo-bar "baz"}

(transform-keys (comp hyphen->upper-camel name) {:foo-bar "baz"})
;=> {"FooBar" "baz"}
```

A complex example.

```clojure
(transform-keys (comp keyword clojure.string/lower-case camel->hyphen)
                {"FooBar" [{"Fancy1" nil "R2D2" nil} {"MoreNo1se" nil}]})
;=> {:foo-bar [{:fancy1 nil, :r2-d2 nil} {:more-no1se nil}]}
```

## License

Copyright © 2014 Jeremy Heiler

Distributed under the Eclipse Public License, the same as Clojure.
