(defproject
  cpmcdaniel/boot-copy
  "2.0"
  :repositories
  [["clojars" {:url "https://repo.clojars.org/"}]
   ["maven-central" {:url "https://repo1.maven.org/maven2"}]
   ["clojars"
    {:url "https://clojars.org/repo/",
     :username "cpmcdaniel",
     :password "s4Viorphotocarhorse"}]]
  :dependencies
  [[adzerk/bootlaces "0.2.0" :scope "test"]
   [onetom/boot-lein-generate "0.1.3" :scope "test"]]
  :source-paths
  ["src"]
  :resource-paths
  ["src"])