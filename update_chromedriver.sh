#!/bin/bash

latest_version=$(curl "https://chromedriver.storage.googleapis.com/LATEST_RELEASE" --silent)

current_version=$(cat ./chromedriver/version)

if [ $latest_version = $current_version ]; then
	echo "You chromedriver is updated."
else
	echo "Updating chromedriver..."

    rm chromedriver/chromedriver.exe
    rm chromedriver/version

    curl "https://chromedriver.storage.googleapis.com/$latest_version/chromedriver_win32.zip" > chromedriver/temp.zip

    cd chromedriver

    unzip -o temp.zip

    rm temp.zip

    echo $latest_version > version

    cd ..
    
    git add chromedriver/chromedriver.exe chromedriver/version

    git commit -m "Updating chromedriver version to $latest_version"

    git push
fi
