user=> (defn divider-1 [a b]
         (/ a b))
#'user/divider-1
user=> (divider-1 10 2)
5
user=> (divider-1 10 0)
#<ArithmeticException java.lang.ArithmeticException: Divide by zero>
user=> (defn divider-2 [a b]
         (try
           (/ a b)
           (catch java.lang.ArithmeticException e
             (println "Divided by zero"))))
#'user/divider-2
user=> (divider-2 10 0)
Divided by zero
nil
user=> (divider-2 10 2)
5
user=> (divider-2 10 nil)
#<NullPointerException java.lang.NullPointerException>
user=> (defn divider-3 [a b]
         (try
           (/ a b)
           (catch java.lang.ArithmeticException e
             (println "Divided by zero"))
           (catch java.lang.NullPointerException e
             (println "Passed in nil"))))
#'user/divider-3
user=> (divider-3 10 nil)
Passed in nil
nil
user=> (use 'dire.core)
nil
user=> (with-handler! #'divider-1
                      java.lang.ArithmeticException
                      (fn [e & args] (println "Divided by zero")))
{:key #<core$partial$fn__4190 clojure.core$partial$fn__4190@6fa37fac>}
user=> (divider-1 10 0)
Divided by zero
nil
user=> (with-handler! #'divider-1
                      java.lang.NullPointerException
                      (fn [e & args] (println "Passed in nil")))
{:key #<core$partial$fn__4190 clojure.core$partial$fn__4190@48b524aa>}
user=> (divider-1 10 nil)
Passed in nil
nil
