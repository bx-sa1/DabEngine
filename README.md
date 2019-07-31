# DabEngine
[![Build status](https://ci.appveyor.com/api/projects/status/dyfe4xca9bxak6bg?svg=true)](https://ci.appveyor.com/project/LEMEMETEAM/dabengine)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=LEMEMETEAM_DabEngine&metric=alert_status)](https://sonarcloud.io/dashboard?id=LEMEMETEAM_DabEngine)
[![Gitter](https://badges.gitter.im/DabEngine/community.svg)](https://gitter.im/DabEngine/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

![Dab](https://thumbs.dreamstime.com/t/dabbing-person-making-dab-black-glyph-icon-symbol-113558248.jpg)

## About
DabEngine is a 2D/(Soon)3D game engine written in Java. I started it randomly because I wanted to know how other games/engines were made. I hade started in November 2018 and I didn't expect it to take this long :)

## Features
- Entity-Compoent-System
- Batch Rendering
- Custom FrameBuffers
- Custom Shaders
- Text Rendering
- Tiled Map Integration
- 2D/3D lighting
- GLSL #include pre-processor
- Post-Processing Effects
- Uniform Buffer Objects
- VBOs and VAOs
- Camera system (2D and 3D)
- In-Memory Cache and ResourceManager
- Audio playing (not spacial)
- Animating Values
- GUI System (quite bare-bones)
- Event System
- Python Scripting

## Build/Package
The repository is packed with Gradle so once you download it from the github, just open a new Terminal at the root of the DabEngine folder and do `gradle build` to build the class files and such.
Use `gradle sourcesJar` to build a jar of DabEngine with sources. The resukting jar is saved in `build/libs`.

## TODO
There is still quite alot to add to the engine.
However, what is currently there is atleat 98% functional.

Anyone is welcome to contribute to the codebase.
