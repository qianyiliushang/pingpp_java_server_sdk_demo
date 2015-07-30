#!/bin/sh

FILE=$1

if [ "$FILE" = "" ]
then
    echo "input apk file"
    exit
fi

cert_XSA=`jar tf $FILE | grep SA`

jar xf $FILE $cert_XSA

MD5=`keytool -printcert -file $cert_XSA | grep MD5`
echo $MD5

Manifest_Package=`aapt dump badging $FILE | grep 'package:'`

Launch_Activity_Pacage=`aapt dump badging $FILE | grep 'launchable'`

echo "Manifest_Package: $(echo  $Manifest_Package| cut -d "'" -f2)"

echo "Launch_Activity_Pacage: $(echo $Launch_Activity_Pacage | cut -d "'" -f2)"