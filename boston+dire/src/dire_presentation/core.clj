(ns dire-presentation.core
(:require [dire.core :refer [with-handler!]]))

;;; Define a task to run. It's just a function.
(defn divider [a b]
  (/ a b))

;;; For a task, specify an exception that can be raised and a function to deal with it.
#_(with-handler! #'divider
  java.lang.ArithmeticException
  ;;; 'e' is the exception object, 'args' are the original arguments to the task.
  (fn [e & args] (println "Cannot divide by 0.")))

(with-handler! #'divider
  java.lang.Exception
  ;;; 'e' is the exception object, 'args' are the original arguments to the task.
  (fn [e & args] (println "Some error.")))

(defn zz [a b]
  (println (divider a b))
  (println (/ a b)))
