This is an Eclipse Plugin that inserts a [Builder](http://www.codeproject.com/Articles/30593/Effective-Java#premain9) inner static class within the current class. Use this from the content assist menus that appear when you type Control+space.

The basic builder class created conforms to that described in [Effective Java 2nd Edition](http://www.codeproject.com/Articles/30593/Effective-Java#premain9).  

The extended version creates an additional template validate() method, which is intended to ensure that only valid instances can be constructed.  It assumes the availability of a "Validate" class.  This will have static methods that validate fields and throw [java.lang.IllegalStateException](http://docs.oracle.com/javase/7/docs/api/java/lang/IllegalStateException.html) if invalid.

Field getters are not supplied for the outter class. Eclipse can add them for you if you require them.

Download jar from [download/effectivejava-builder-proposal_1.0.0.201503230926.jar] and drop into <ECLIPSE_HOME>/plugins and re-start eclipse.

To build from source:
* Download sources.
* Import project into workspace.
* Right click on project in package explorer, and select Export -> Plug-in Development -> Deployable plugin-ins and fragments -> Next -> Destination -> Install into host Repository -> Finish.

Yes, this is a very simple plugin, and there is lots of room for improvement.  You could add option menus, templates and many many other things.  Good luck with that.

Acknowledgement:
Martin Monperrus for his Eclipse [Content Assist Example]](https://github.com/monperrus/content-assist-example).

