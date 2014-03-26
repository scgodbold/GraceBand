In order to build graceband you first must retrive our source code from our git repo located at:

https://github.com/scgodbold/GraceBand

This is a publicly hosted repo so just pull the code down from the master branch and take it into your android development kit. This is all the is required to compile it and at this point you should be ready to install it on your device.

Graceband does have a couple of hardware requirements. First and foremost it must be run on a nexus 7. If you try to launch it on other devices we do not guarentee it will work or look nice as we had no such device to test it on. Secondly it requires a joystick to navigate. You can use some of the base functionality without one but in order to fully explore our application a joystick is required. We also had to purchase a usb female to micro usb male adapter to connect our joytstick to the adroid device.

Once you have it connected you should be good to go. Just launch Graceband and you are ready to play. One note about the joystick. In order to compensate for Grace's lack of fine motor control there is joystick logic written in to wait until the joystick returns to center for a certain period of time until it accepts new input. If you appear to be stuck on something let the joystick 0 itself and try again you should be ok. 

As of now we require either a click of a button on the joystick or touching the specific object on the screen to click. However we are working on a full screen click to allow Grace to use the application independently.
