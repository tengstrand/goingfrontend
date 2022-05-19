(ns com.github.tengstrand.goingfrontend.app
  (:require [reagent.dom :as r]))

(defn main-component []
  [:div
   [:h1 "This is a simple web app!"]])

(defn mount [c]
  (r/render [c] (.getElementById js/document "app")))

(defn main []
  (mount main-component))
