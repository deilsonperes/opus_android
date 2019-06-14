project(jniopus)

cmake_minimum_required(VERSION 3.4.3)

add_library(jniopus SHARED opus_encoder.cpp opus_decoder.cpp)

# android logging
find_library(log-lib log)

add_subdirectory(opus-1.3.1)

target_link_libraries(jniopus opus log)