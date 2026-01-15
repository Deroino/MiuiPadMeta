package com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.FieldFinder.`-Static`.fieldFinder
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.maxmipadinput.hook.hooks.BaseHook
import com.yifeplayte.maxmipadinput.hook.utils.MetaKeyFakeList

object RestoreMetaKeyAndroid11 : BaseHook() {
    override val key = "restore_meta_key_android11"

    override fun hook() {
        loadClass("com.android.server.policy.PhoneWindowManager").fieldFinder()
            .filterByName("sDeliveMetaKeyAppList").first().set(null, MetaKeyFakeList())

        loadClass("com.android.server.policy.MiuiKeyShortcutManager").methodFinder()
            .filterByName("getEnableKsFeature").first().createHook {
                returnConstant(false)
            }
    }
}