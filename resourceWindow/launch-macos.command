#!/bin/bash
cd "$(dirname "$0")"
java -Xss1024k -Xmn256m -Xms512m -Xmx1024m -Dswing.metalTheme=steel -Xdock:name=X-Designer -Xdock:icon=images/splash.jpg -splash:images/splash.jpg ResourceWindow