import com.android.build.gradle.api.AndroidSourceDirectorySet
import com.android.build.gradle.api.AndroidSourceSet
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<AndroidSourceSet>.mainSrcDirs(value: String): AndroidSourceDirectorySet = srcDirs("main", value)
fun NamedDomainObjectContainer<AndroidSourceSet>.testSrcDirs(value: String): AndroidSourceDirectorySet = srcDirs("test", value)

fun NamedDomainObjectContainer<AndroidSourceSet>.srcDirs(name: String, value: String): AndroidSourceDirectorySet = getByName(name)
    .java
    .srcDirs(value)
