# boot-copy 

[![Clojars Project](http://clojars.org/cpmcdaniel/boot-copy/latest-version.svg)](http://clojars.org/cpmcdaniel/boot-copy)

A simple `copy` task that allows boot builds to copy files from previous tasks into a specified directory.

[](dependency)
```clojure
[cpmcdaniel/boot-copy "1.0"] ;; latest release
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
       :matching       #{#"\.jar$"}})
       
(deftask build
   "Build my project"
   []
   (comp (jar) (copy)))
```

The build can then be executed with `boot build` or:

```bash
boot jar copy -m '\.jar$' -o /home/foo/backups
```

## Acknowledgements

Thanks to the boot developers and the folks in #hoplon on FreeNode IRC for answering all my annoying questions.

## License

Copyright © 2015 Craig McDaniel

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


[installboot]: https://github.com/boot-clj/boot#install
