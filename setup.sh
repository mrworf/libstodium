#!/bin/bash
set -e
#set -x

# Android NDK r12b
export NDK_VERSION="android-ndk-r14b"
export NDK_OSFAMILY="linux"
export SWIG_VERSION="swig-3.0.8"

export NDK_PLATFORM="android-14"

# Require JAVA_HOME
if [ -z "$JAVA_HOME" ]; then
    echo "ERROR You should set JAVA_HOME"
    echo "example: \`export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64\`"
    echo "Exiting!"
    exit 1
fi

function ndk_setup {
    ARCH=$(uname -m)
    if [ $ARCH != "x86_64" ]; then
      $ARCH = "x86"
    fi

    echo "Downloading NDK for ${ARCH}"
    ZIP="${NDK_VERSION}-${NDK_OSFAMILY}-${ARCH}.zip"

    wget https://dl.google.com/android/repository/${ZIP}
    unzip ${ZIP}

    export ANDROID_NDK_HOME=${PWD}/${NDK_VERSION}
}

function ndk_cleanup {
    rm -rf ${NDK_VERSION}*
}

#
# SWIG 2.0
#
function swig_setup {
    cd jni

    wget https://prdownloads.sourceforge.net/swig/${SWIG_VERSION}.tar.gz
    tar -xvf ${SWIG_VERSION}.tar.gz

    cd ${SWIG_VERSION}

    ./autogen.sh
    ./configure
    make -j 5
    sudo make install

    cd ../..
}

function swig_cleanup {
    cd jni

    rm -rf ${SWIG_VERSION}*

    cd ..
}

#
# install libsodium from the repository
#
function setup_libsodium {
    rm -rf libsodium

    git submodule init
    git submodule update

    cd libsodium

    # use stable branch
    git fetch && git checkout origin/stable
    git clean -ffxd

    ./autogen.sh

    # Build local
    ./configure
    make && make check
    sudo make install
    sudo ldconfig

    # Disable minimal in android builds
    #sed --in-place '/--enable-minimal/d' ./dist-build/android-build.sh

    # Build android
    ./dist-build/android-arm.sh
    ./dist-build/android-armv7-a.sh
    ./dist-build/android-armv8-a.sh
    #./dist-build/android-mips32.sh # Not used or supported anymore
    ./dist-build/android-x86.sh
    ./dist-build/android-x86_64.sh
    cd ..
}

# Install swig and compile the JNI with libsodium
function compile_jni {
    cd jni
    ./compile.sh

    export PATH=$PATH:$ANDROID_NDK_HOME
    ndk-build clean
    ndk-build
    cd ..
}

STEP_DOWNLOAD=false
STEP_SODIUM=false
STEP_JNI=true
STEP_CLEANUP=false

POSITIONAL=()
while [[ $# -gt 0 ]]; do
	key="$1"

	case $key in
		--download)
		STEP_DOWNLOAD=true
		shift
		;;
		--sodium)
		STEP_SODIUM=true
		;;
		--no-jni)
		STEP_JNI=false
		;;
		--cleanup)
		STEP_CLEANUP=true
		;;
		--help|-h)
		cat <<EOT
Options for setup.sh:
  --download     Download and setup NDK and SWIG
  --sodium       Initialize and build libsodium
  --no-jni       Don't build the JNI bindings
  --cleanup      Remove NDK and SWIG
  --help         This message
EOT
		exit 0
		;;
		*)    # unknown option
		echo "Invalid argument: $key"
		exit 255
		;;
	esac
done

if $STEP_DOWNLOAD ; then
	echo "Downloading necessary tools..."
	# Add auto-cleanup before the script runs
	ndk_cleanup
	swig_cleanup

	# Download and install
	ndk_setup
	swig_setup
fi

if $STEP_SODIUM ; then
	echo "Building libsodium..."
	setup_libsodium
fi

if $STEP_JNI ; then
	echo "Building libsodium bindings..."
	compile_jni
fi

if $STEP_CLEANUP ; then
	echo "Cleaning up..."
	# Cleanup
	ndk_cleanup
	swig_cleanup
fi
