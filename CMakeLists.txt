cmake_minimum_required(VERSION 3.5)
project(laba7)
#set (MY_VAR "hello")
include_directories(${MY_SOURCE_DIR})
set(CMAKE_CXX_STANDARD 11)

add_executable(test main.cpp CompMethods.cpp OverloadM.cpp
        OverloadPer.cpp PerechenMethods.cpp SearchCompMethods.cpp)
