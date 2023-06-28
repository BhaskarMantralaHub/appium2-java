# appium2-java

## What is Appium?

## Appium 2 architecture?

![](architecture-diag.png)

## Requirements

https://appium.io/docs/en/2.0/intro/requirements/

* A macOS, Linux, or Windows operating system
* Node.js version in the SemVer range ^14.17.0 || ^16.13.0 || >=18.0.0
* NPM version >= 8 (NPM is usually bundled with Node.js, but can be upgraded independently)

## What is Simulator (IOS)?

## What is Emulator (Android)?

## What is Real device (iOS, Android, Windows, Mac)?

## Download XCode for IOS Simulator 

    https://developer.apple.com/xcode/resources/

> Appstore app

## Setup Appium Server CLI and Drivers (Android and IOS) using Appium Installer

Install Appium Installer from https://www.npmjs.com/package/appium-installer
   > npm i appium-installer -g

> **Use Space key to select option while installing packages**
## Install Appium Server (CLI)

**[Appium Server Installation Logs](appium-server-setup.txt)**

## Setup Android Environment 

Install required packages using following command:
   > appium-installer
    
``` 
    👋 Hello, Appium user ✨
    
    ? Select an option (Use arrow keys)
    ❯ Need help setting up Android Environment to run your Appium test?
    Need help setting up iOS Environment to run your Appium test?
    Install Appium Server
    Install Appium Drivers
    Install Appium Plugin
    Run Appium Doctor
    Launch Emulators/Simulators
    (Move up and down to reveal more choices)
   ```

**[Android Environment Setup Logs](android-environment-setup.txt)**

## Set Android Home Path

* Open Bash profile 
```shell
  vim ~/.zshrc
```

* Set android path as below and save
```
    export ANDROID_HOME=/Users/$USER/Library/Android/sdk
    export PATH=$ANDROID_HOME/platform-tools:$PATH
    export PATH=$ANDROID_HOME/tools:$PATH
    export PATH=$ANDROID_HOME/tools/bin:$PATH
```

* Update profile changes
```shell
  source ~/.zshrc
```
* Check Android home path
```shell
  echo $ANDROID_HOME
```


## Setup Android and IOS drivers

**[Android and iOS Driver Setup Logs](android-ios-driver-setup.txt)**



| Driver Name                | Device  | Config                                                  |
|----------------------------|---------|---------------------------------------------------------|
| appium-uiautomator2-driver | Android | https://github.com/appium/appium-uiautomator2-driver    |
| appium-xcuitest-driver     | iOS     | https://appium.github.io/appium-xcuitest-driver/4.32.7/ |

## Setup Appium Plugins

**[Appium Plugin Setup Logs](appium-plugin-sample-setup.txt)**


## Setup Appium Java Client from Project pom.xml

> java-client **>8** version is required for Appium 2.0
```maven
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>8.5.1</version>
    </dependency>
```

