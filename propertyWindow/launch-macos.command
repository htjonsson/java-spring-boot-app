#!/bin/bash
cd "$(dirname "$0")"
java -Xss1024k -Xmn256m -Xms512m -Xmx1024m -Xdock:name=application_name -Xdock:icon=images/splash.jpg -splash:images/splash.jpg PropertyWindow