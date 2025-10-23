#!/bin/bash
cd "$(dirname "$0")"
java -Xss1024k -Xmn256m -Xms512m -Xmx1024m -splash:images/splash.jpg PropertyWindow