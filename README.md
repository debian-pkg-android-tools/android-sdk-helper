Helper Tools for Building Android Apps Using Debian's Android SDK
=================================================================

[The Android SDK in Debian](https://packages.debian.org/unstable/android-sdk) is
a restricted environment where only one version of Build-Tools and a subset of
Android target frameworks (android.jar) are available, therefore, most Android
apps are not able to be built using this SDK.

As of the Gradle Plugin for Android 2.2.2 (com.android.tools.build:gradle:2.2.2)
it is very difficult, if not impossible, to let the plugin select the best SDK
components available using an extra plugin. This is due to the fact that the
plugin verifies the integrity of the SDK in the Project.afterEvaluate() closure.
The only way to modify the build script before the verification happens is to
modify the Gradle plugin itself. Therefore, the Gradle plugin packaged in Debian
([libgradle-android-plugin-java](https://packages.debian.org/unstable/libgradle-android-plugin-java))
is patched to do so.

Currently, the patched Gradle plugin does the following if the SDK location is
set to `/usr/lib/android-sdk`:

  * Change `buildToolsVersion` to the latest one installed.
  * If the API Level in `compileSdkVersion` isn't installed, change it to the
    highest API Level installed if the highest one is later than the original
    one, otherwise throw an error.

This package provides a Gradle init script located in
`/usr/share/android-debian-helper/init.gradle` which forces a build script to
use Debian's Gradle Plugin for Android instead of the one downloaded from online
Maven repositories. In order to use this script, run:

```
gradle -I /usr/share/android-debian-helper/init.gradle [targets...]
```

This package will provide other tools if the default building tool for Android
apps is changed in the future.