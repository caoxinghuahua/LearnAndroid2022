cmake_minimum_required(VERSION 3.4.1)


#生成的so的名称
add_library(bugly
        SHARED
        bugly.cpp)

target_link_libraries(bugly
        log
        )