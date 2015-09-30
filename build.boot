(set-env!
 :source-paths #{"src"}
 :dependencies '[[adzerk/bootlaces "0.1.9" :scope "test"]])


(require '[adzerk.bootlaces :refer :all]
         '[cpmcdaniel.boot-copy :refer :all])

(def +version+ "1.1")

(bootlaces! +version+)

(task-options!
 pom {:project 'cpmcdaniel/boot-copy
      :version +version+
      :description "Boot task to copy files to a specified directory"
      :url "https://github.com/cpmcdaniel/boot-copy"
      :scm {:url "https://github.com/cpmcdaniel/boot-copy"}
      :license {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})
