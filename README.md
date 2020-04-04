

[![Clojars Project](http://clojars.org/cpmcdaniel/boot-copy/latest-version.svg)](http://clojars.org/cpmcdaniel/boot-copy)

A simple `copy` task that allows boot builds to copy files from previous tasks into a specified directory.

[](dependency)
```clojure
[cpmcdaniel/boot-copy "2.0"] ;; latest release
```
[](/dependency)

## Usage

The following examples assume you have [boot installed][installboot] and up to
date.

### Within a project

If you already have a `build.boot`, add the dependency above to `:dependencies`
and `(require '[cpmcdaniel/boot-copy :refer :all])`. The build.boot file
may look like the following:

```clojure
(set-env!
 :source-paths   #{"src/main/java" "src/main/clojure"}
 :resource-paths #{"src/main/resources"}
 :dependencies   '[[cpmcdaniel/boot-copy "<version>" :scope "provided"]]) ;; latest version

(require '[cpmcdaniel.boot-copy :refer :all])

(task-options!
 copy {:output-dir    "/home/foo/backups"
       :include       #{#"\.jar$"}
       :exclude       #{#"\.bak$"}})

(deftask build
   "Build my project"
   []
   (comp (jar) (copy)))
```


The build can then be executed with `boot build` or:

```bash
boot jar copy -i '\.jar$' -o /home/foo/backups
```

TIP: try mixing this with the boot `watch` task to automatically move files when
sources change, like so:

```bash
boot watch jar copy -i '\.jar$' -o /home/foo/backups
```

## License

Copyright © 2020 Craig McDaniel

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


[installboot]: https://github.com/boot-clj/boot#install
