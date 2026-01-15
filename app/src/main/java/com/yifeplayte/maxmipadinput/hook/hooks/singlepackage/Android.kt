package com.yifeplayte.maxmipadinput.hook.hooks.singlepackage

import com.yifeplayte.maxmipadinput.hook.hooks.BasePackage
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.DisableAltTabAndroid11
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.DisableAltTabAndroid12
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.DisableCommandTabAndroid11
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.DisableFixedOrientation
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.IgnoreStylusKeyGesture
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.NoMagicPointer
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.RemoveStylusBluetoothRestriction
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.RestoreEsc
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.RestoreMetaKeyAndroid11
import com.yifeplayte.maxmipadinput.hook.hooks.singlepackage.android.RestoreMetaKeyAndroid12

object Android : BasePackage() {
    override val packageName = "android"
    override val hooks = setOf(
        DisableFixedOrientation,
        IgnoreStylusKeyGesture,
        NoMagicPointer,
        RemoveStylusBluetoothRestriction,
        RestoreEsc,
        RestoreMetaKeyAndroid11,
        RestoreMetaKeyAndroid12,
        DisableAltTabAndroid11,
        DisableCommandTabAndroid11,
        DisableAltTabAndroid12,
    )
}