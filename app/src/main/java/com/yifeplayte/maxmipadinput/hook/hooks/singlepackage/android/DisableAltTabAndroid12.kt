package com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android

import android.view.KeyEvent
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.maxmipadinput.hook.hooks.BaseHook

object DisableAltTabAndroid12 : BaseHook() {
    override val key = "disable_alt_tab_android12"

    override fun hook() {
        loadClass("com.android.server.policy.PhoneWindowManager").methodFinder()
            .filterByName("interceptKeyBeforeDispatching").first().createHook {
                before { param ->
                    val keyEvent = param.args[1] as KeyEvent
                    if (keyEvent.isAltPressed && keyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                        param.result = 0L
                    }
                }
            }
    }
}