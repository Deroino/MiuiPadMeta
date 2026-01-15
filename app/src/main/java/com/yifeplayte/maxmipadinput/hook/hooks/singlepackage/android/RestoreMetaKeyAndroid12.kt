package com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android

import android.view.KeyEvent
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClassOrNull
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.maxmipadinput.hook.hooks.BaseHook
import com.github.kyuubiran.ezxhelper.Log
import com.github.kyuubiran.ezxhelper.LogExtensions.logexIfThrow

object RestoreMetaKeyAndroid12 : BaseHook() {
    override val key = "restore_meta_key_android12"

    override fun hook() {
        loadClass("com.android.server.policy.PhoneWindowManager").methodFinder()
            .filterByName("interceptKeyBeforeDispatching").first().createHook {
                before { param ->
                    val keyEvent = param.args[1] as KeyEvent
                    if (keyEvent.isMetaPressed) {
                        param.result = 0L
                    }
                }
            }

        runCatching {
            loadClass("com.android.server.policy.MiuiKeyShortcutManager").methodFinder()
                .filterByName("getEnableKsFeature").first().createHook {
                    returnConstant(false)
                }
        }.logexIfThrow("Failed to hook MiuiKeyShortcutManager.getEnableKsFeature")

        runCatching {
            loadClassOrNull("com.android.server.policy.BaseMiuiPhoneWindowManager")?.methodFinder()
                ?.filterByName("handleMetaKey")?.first()?.createHook {
                    returnConstant(null)
                }
        }.logexIfThrow("Failed to hook BaseMiuiPhoneWindowManager.handleMetaKey")
    }
}