(ns dire-presentation.helper
  (:require [clj-ssh.ssh :as ssh]))

(defn build-session
  "Build the session for ssh/sftp."
  []
  (let [agent (ssh/ssh-agent {:use-system-ssh-agent false})]
    (ssh/add-identity agent {:private-key-path "[YOUR PRIVATE KEY]"})
    (ssh/session agent "localhost" {:username "[YOUR USER]"})))
