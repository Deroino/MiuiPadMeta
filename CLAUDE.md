# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

MiuiPadMeta is an Xposed module that restores Meta/Win key functionality and disables Alt-Tab hotkeys on MIUI devices (Xiaomi Pad 5 series and MIUI phones with Android 12+). This enables proper keyboard usage in remote desktop applications by bypassing MIUI's keyboard shortcut hijacking.

## Build Commands

```bash
# Build the module (generates APK)
./gradlew assembleDebug

# Build release version
./gradlew assembleRelease

# Clean build
./gradlew clean

# Install debug APK to connected device
./gradlew installDebug
```

## Architecture

### Core Components

- **MainHook.kt**: Entry point that implements IXposedHookLoadPackage, detects Android version and initializes appropriate hooks
- **BaseHook.kt**: Abstract base class for all hook implementations
- **MIUIHotkeyHooksAndroid11.kt**: Hooks for Android 11 (MIUI 12.5/13)
- **MIUIHotkeyHooksAndroid12.kt**: Hooks for Android 12+ (MIUI 13/14, HyperOS)
- **MyFakeList.java**: Custom List implementation to bypass MIUI's app whitelist checks

### Hook Strategy by Android Version

**Android 11 (API 30):**
1. Hook `MiuiKeyShortcutManager.getEnableKsFeature()` to return false
2. Replace `sDeliveMetaKeyAppList` with custom list that bypasses package checks
3. Hook `PhoneWindowManager.interceptKeyBeforeDispatching()` to block Alt-Tab

**Android 12+ (API 31+):**
1. Same MIUI shortcut disable (now in miui-services.jar)
2. Direct Meta key bypass in `PhoneWindowManager.interceptKeyBeforeDispatching()`
3. Alt-Tab blocking as above

**Android 13/HyperOS:**
- Uses Android 12+ hooks (no separate implementation needed)

### Key Technical Details

- **Target Package**: Hooks only target `android` system process
- **Xposed Scope**: Defined in `arrays.xml` - targets "android" package only
- **Dependencies**: Uses EzXHelper library for simplified Xposed development
- **Package ID**: Uses custom package ID 0x45 to avoid conflicts
- **Proguard**: Enabled for release builds with custom rules

## Development Notes

- Module requires Xposed framework (LSPosed recommended)
- Test on actual MIUI devices - emulation won't work for system-level hooks
- Android version detection is critical - wrong hooks will cause crashes
- MIUI system components differ between Android versions and device types
- HyperOS requires additional testing due to tighter Bootloader restrictions

## Testing

Deploy the APK to a rooted MIUI device with Xposed framework and enable the module in LSPosed Manager. Test Meta key functionality and Alt-Tab behavior in remote desktop apps like Microsoft Remote Desktop.