(ns cpmcdaniel.boot-copy
  {:boot/export-tasks true}
  (:require
   [boot.util :as util]
   [boot.core :refer :all]
   [clojure.java.io :as io]))

(defn maybe-by-re
  "Wrapper around boot.core/by-re to only apply it if `res` is non-empty.
  Otherwise, we just return the files unmodified."
  [res files & [negate?]]
  (if (seq res)
    (by-re res files negate?)
    files))

(defn maybe-not-by-re
  [res files]
  (maybe-by-re res files true))

(deftask copy
  "Copy files from the fileset to another directory.

  Example: (copy jar files to /home/foo/jars):

     $ boot build copy -m '\\.jar$' -o /home/foo/jars"
  [o output-dir PATH str     "The output directory path."
   i include REGEX #{regex} "The set of regexes matching paths to include in the copy."
   e exclude REGEX #{regex} "The set of regexes matching paths to exclude from the copy."]
  (let [out-dir (io/file output-dir)]
    (with-pre-wrap fileset
      (let [in-files (->> fileset
                          output-files
                          (maybe-by-re include)
                          (maybe-not-by-re exclude)
                          (map (juxt tmp-path tmp-file)))]
        (doseq [[path in-file] in-files]
          (let [out-file (doto (io/file out-dir path) io/make-parents)]
            (util/info "Copying %s to %s...\n" path out-dir)
            (io/copy in-file out-file)))
        fileset))))
