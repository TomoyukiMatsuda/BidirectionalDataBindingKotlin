package com.android.bidirectionaldatabindingkotlin

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ViewModel : BaseObservable() {

    // これでフォーム入力内容(formText)の getterとsetter が同時にセットできる
    @get:Bindable
    var formText: String = ""
        set(value) {
            // fieldはformTextのこと
            // フォーム入力内容がformTextにセットされる
            field = value
            // View側にrealTimeTextの変更を通知(getRealTimetext()を呼ぶ)
            notifyPropertyChanged(BR.realTimeText)
            // View側にbuttonEnableの変更を通知(isButtonEnable()を呼ぶ)
            notifyPropertyChanged(BR.buttonEnable)
        }

    // フォーム入力内容をフォーム下のTextViewに反映する getter
    @Bindable fun getRealTimeText(): String {
        // return formText でも良いがわかりやすく一旦変数に代入してから return している
        val realTimeText = formText
        return realTimeText
    }

    // formTextの値の有無によってボタン活性・非活性のフラグを返す
    @Bindable fun isButtonEnable(): Boolean {
        return !formText.isNullOrBlank()
    }
}
