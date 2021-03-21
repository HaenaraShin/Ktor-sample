# ktor-sample

🔨  An android sample project using Ktor client.

## Dependencies

### build.gradle(root)

```groovy
buildscript {
    ...
    dependencies {
        ...
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30'
        classpath 'org.jetbrains.kotlin:kotlin-serialization:1.4.30'
    }
}
```
### build.gradle(app)

```groovy
plugins {
    ...
    id 'org.jetbrains.kotlin.plugin.serialization'
}

dependencies {
    ...
    implementation 'io.ktor:ktor-client-core:1.5.2'
    implementation 'io.ktor:ktor-client-android:1.5.2'
    implementation 'io.ktor:ktor-client-serialization:1.5.2'
    implementation 'io.ktor:ktor-client-serialization-jvm:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0'
}
```

## Data Model Class

```kotlin
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
```

> If annotation line has a error, use Android Studio 4.2.0

## HttpClient

```kotlin
val client = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            }
        )
    }
}
```
## Calling http method

```kotlin
client.get<List<Cat>>("https://api.thecatapi.com/v1/images/search")
```

## Sample Run

![sample_run](20210321_samplerun.gif)

## LICENSE

```
MIT License

Copyright (c) 2021 Haenala Shin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
