(ns dire-presentation.ssh
  (:require [dire-presentation.helper :refer [build-session]]
            [clj-ssh.ssh :as ssh]
            [slingshot.slingshot :refer [throw+]]
            [dire.core :refer [with-postcondition! with-handler!]]))

(defn- ssh-wrapper
  "Wrapper for ssh calls. Mainly used to include data about the call and to
  provide and appropriate entry point for exception handling."
  [server session command]
  (merge {:server server} (ssh/ssh session {:cmd command})))

(with-postcondition! #'ssh-wrapper
  ::invalid-command
  (fn [result & _]
    (= 0 (:exit result))))

(with-handler! #'ssh-wrapper
  {:postcondition ::invalid-command}
  (fn [_ result]
    (throw+ (merge result {:type ::invalid-command}))))

(defn run-commands
  "Actually run the commands on the remote server."
  [server commands]
  (let [session (build-session)]
    (ssh/with-connection session
      (doall (for [c commands]
               (ssh-wrapper server session c))))))
