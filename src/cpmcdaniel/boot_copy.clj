(ns cpmcdaniel.boot-copy
  {:boot/export-tasks true}
  (:require
   [boot.util :as util]
   [boot.core :refer :all]
   [clojure.java.io :as io]))


(deftask copy
  "Copy files from the fileset to another directory.

  Example: (copy jar files to /home/foo/jars):

     $ boot build copy -m '\\.jar$' -o /home/foo/jars"
  [o output-dir PATH str     "The output directory path."
   m matching REGEX #{regex} "The set of regexes matching paths to backup."]
  (let [out-dir (io/file output-dir)]
    (with-pre-wrap fileset
      (let [in-files (->> fileset
                          output-files
                          (by-re matching)
                          (map (juxt tmppath tmpfile)))]
        (doseq [[path in-file] in-files]
          (let [out-file (doto (io/file out-dir path) io/make-parents)]
            (util/info "Copying %s to %s...\n" path out-dir)
            (io/copy in-file out-file)))
        fileset))))
