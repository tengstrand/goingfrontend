(ns com.github.tengstrand.goingfrontend.app
  (:require [reagent.dom :as d]
            [reagent.core :as r]))

(defonce val1 (r/atom 0))
(defonce val2 (r/atom 0))
(defonce operator (r/atom "+"))

(def op->operator {"+" +
                   "-" -
                   "*" *
                   "/" /})

(defn result []
  (let [op (op->operator @operator +)
        result (op (js/parseInt @val1)
                   (js/parseInt @val2))]
     (if (js/isNaN result)
       ""
       result)))

(defn set-value [val]
  #(reset! val (-> % .-target .-value)))

(defn main-page []
  [:div
   [:h2 "Calculator"]
   [:form {}
    [:input {:type :number, :value @val1, :on-change (set-value val1)}]
    [:select {:on-change (set-value operator)}
     [:option {:key :add} "+"]
     [:option {:key :subtract} "-"]
     [:option {:key :multiply} "*"]
     [:option {:key :divide} "/"]]
    [:input {:type :number, :value @val2, :on-change (set-value val2)}]
    [:label "=" (result)]]])

(defn mount [c]
  (d/render [c] (.getElementById js/document "app")))

(defn main []
  (mount main-page))
