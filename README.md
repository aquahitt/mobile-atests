# Before running tests
1. Start _**Appium**_ server
2. Start _**Android**_ emulator
3. Place _**APK**_ file to `apps` directory
4. Set parameters for connection to _**Appium**_ server in `confi.properties`, parameters with prefix: `appium.server`
5. Set _**Android**_ device name to `confi.properties`, parameter with name: `android.device.name`
6. Set _**APK**_ file name to `confi.properties`, parameter with name: `android.apk.file.name`

# Run tests
Execute following command:
```shell
gradle clean test
```