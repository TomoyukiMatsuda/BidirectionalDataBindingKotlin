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
            // View側にformTextの変更を通知
            notifyPropertyChanged(BR.formText)
            // View側にbuttonEnableの変更を通知(isButtonEnable()を呼ぶ)
            notifyPropertyChanged(BR.buttonEnable)
        }

    // formTextの値の有無によってボタン活性・非活性のフラグを返す
    @Bindable fun isButtonEnable(): Boolean {
        return !formText.isNullOrBlank()
    }

    // これだとボタンに活性・非活性が通知されなかった。なんでだろう？
    // @get:Bindable
    // var buttonEnable: Boolean = !formText.isNullOrBlank()
}
