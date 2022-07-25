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

(defonce root (.getElementById js/document "app"))

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
  [:div.flex.flex-col.m-2
   [:h2.m-2.text-xl.text-white "Calculator"]
   [:form.flex {}
    [:input.w-20.m-2.p-1.rounded {:type :number, :value @val1, :on-change (set-value val1)}]
    [:select.w-15.m-2.p-1.rounded.bg-white {:on-change (set-value operator)}
     [:option {:key :add} "+"]
     [:option {:key :sub} "-"]
     [:option {:key :mul} "*"]
     [:option {:key :div} "/"]]
    [:input.w-20.m-2.p-1.rounded {:type :number, :value @val2, :on-change (set-value val2)}]
    [:label.m-2.mt-3.text-white "="]
    [:label.m-2.mt-3.text-white (result)]]])

(defn mount-app []
  (d/render [main-page] root))

(defn ^:export init []
  (mount-app))
