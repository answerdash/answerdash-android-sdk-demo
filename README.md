# AnswerDash Android SDK Demo

This repository contains a demo application that integrates AnswerDash Android SDK released by [AnswerDash](http://www.answerdash.com).

If you have any questions, comments, or issues related to the demo app or the sdk then please contact the team by emailing [support@answerdash.com](mailto:support@answerdash.com).

## SDK Requirements

The minimum Android SDK version (minSdkVersion) is 16 (JellyBean - Android 4.1.x)

## Installation

Currently the library is available as an .aar library. In the near future it will be available through maven as well.

* download library file (answerdash-android-sdk.aar)
* in your app module, create a new folder (or use the existing one) called libs and put the .aar file in there
* in the gradle build file, modify the repositories section so it looks like this
```
repositories {
        ...
        flatDir {
            dirs 'libs'
        }
    }
```
* modify the dependencies section (note that if you renamed the .aar file, you have to use the correct name over here)
```
dependencies {
...
...
    compile 'com.android.support:appcompat-v7:24.0.0'
    compile 'com.android.support:design:24.0.0'
    compile 'com.android.support:cardview-v7:24.0.0'
    compile 'com.android.support:percent:24.0.0'

    compile(name: 'answerdash-android-sdk', ext: 'aar', version: '1.0')
}
    
```
note that if you use a different compileSdkVersion than 24, you have to use the correct version for the support imports as well, e.g. com.android.support:support-annotations:23.3.0 for compileSdkVersion 23

## Usage

##### Initialization
AnswerDashSDK should be initialized at the application startup with a valid site ID.
* Create a class that extends android.app.Application
* in the onCreate() method, add the following code: 
```Java
AnswerDash.INSTANCE.setSiteID(this, "YOUR-SITE-ID");
```
* modify the "Application" Tag in AndroidManifest.xml and add a "name" property that sets the class created above, e.g.
```XML
<application
  android:name=".MainApplication"
  ...
```

##### Setting App State
Whenever your application state changes, call the following method to inform the AnswerDashSDK. Calling this method will automatically preload the new questions and answers.
```Java
AnswerDash.INSTANCE.setAppState(APPLICATION, "NEW-APP-STATE");
```
APPLICATION is an instance to the application; in Acitivities you can use **getApplication()** and in Fragments **getActivity().getApplication()**

##### Adding AnswerDash Button
It is possible to add the button in xml and in Java code like any other UI element. In order to get the button floating, you can wrap your existing layout in another layout extending from **FrameLayout** and position the button accordingly (see demo source code).
As width and height you should use "wrap_content" to get the default size; however, if you want to change the size, you can use the size property as described below.

##### Styling AnswerDash Button
You can customize the appearance of the default AnswerDash button using following properties.
In order to use the properties in xml, you have to use a custom namespace, e.g. **xmlns:app="http://schemas.android.com/apk/res-auto"** and use that namespace for the properties.

border color:
``` Java
myAnswerDashButton.setBorderColor(@ColorInt int color)
```

``` Xml
app:borderColor="@android:color/transparent"
```

fill color:
``` Java
myAnswerDashButton.setFillColor(@ColorInt int color)
```

``` Xml
app:fillColor="@color/colorPrimary"
```

icon color:
``` Java
myAnswerDashButton.setIcon(@ColorInt int color)
```

``` Xml
app:iconColor="@android:color/white"
```

size:
``` Java
myAnswerDashButton.setButtonSize(Size-In-Pixels)
```

``` Xml
app:size="72dp"
```

selected state color:

you can use the default selection states and ripples (on API21+) based on the colors you specified for the button, or you can override these values.

``` Java
myAnswerDashButton.setRippleStartFillColor(@ColorInt int color)
```

``` Xml
app:rippleStartFillColor="@color/colorPrimaryDark"
```

``` Java
myAnswerDashButton.setFillColorSelected(@ColorInt int color)
```

``` Xml
app:fillColorSelected="@color/colorPrimaryDark"
```

##### Using your own button implementations
If you want to trigger the AnswerDash popup using your own button or other interaction, call the method below e.g. in the onClick() callback:

``` Java
AnswerDash.INSTANCE.showWebView(CONTEXT)
```
as CONTEXT, you can pass e.g. getContext() or getActivity()

## Contact

You can reach the AnswerDash team at any time by emailing [support@answerdash.com](mailto:support@answerdash.com).

## License

AnswerDashSDK is licensed under the [AnswerDash SDK License](https://github.com/answerdash/release-notes/blob/master/LICENSE.md).
