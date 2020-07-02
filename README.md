# JDK11-annotation-processor-reproducer
## Running the reproducer

1. Make sure that the `JAVA_HOME` environment variable points to the Java 11 installation.

1. Run the build of the application:

   ``` 
   mvn clean install
   ```

   Compilation won't pass with the issue:
   
   ```shell script
   [INFO] ------------------------------------------------------------------------
   [INFO] Reactor Summary for annotation-processor 1.0-SNAPSHOT:
   [INFO] 
   [INFO] annotation-processor ............................... SUCCESS [  0.304 s]
   [INFO] processor .......................................... SUCCESS [  1.193 s]
   [INFO] annotated-class .................................... FAILURE [  0.079 s]
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD FAILURE
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time:  1.704 s
   [INFO] Finished at: 2020-07-02T18:42:46+02:00
   [INFO] ------------------------------------------------------------------------
   [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile)    
   on project annotated-class: Fatal error compiling: java.lang.annotation.AnnotationTypeMismatchException: Incorrectly typed data
   found for annotation element public abstract org.example.MySecondAnnotation org.example.MyFirstAnnotation.secondAnnotation()
   (Found data of type org.example.MySecondAnnotation) -> [Help 1]
   ```
   
   When Maven compilation is run with `-X` flag it shows the following stack trace:
   
   ```shell script
    org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project annotated-class: Fatal error compiling
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:215)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
        at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
        at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
        at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
        at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke (Method.java:566)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
    Caused by: org.apache.maven.plugin.MojoExecutionException: Fatal error compiling
        at org.apache.maven.plugin.compiler.AbstractCompilerMojo.execute (AbstractCompilerMojo.java:1145)
        at org.apache.maven.plugin.compiler.CompilerMojo.execute (CompilerMojo.java:187)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
        at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
        at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
        at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
        at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke (Method.java:566)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
    Caused by: org.codehaus.plexus.compiler.CompilerException: java.lang.annotation.AnnotationTypeMismatchException: Incorrectly typed data found for annotation element public abstract org.example.MySecondAnnotation org.example.MyFirstAnnotation.secondAnnotation() (Found data of type org.example.MySecondAnnotation)
        at org.codehaus.plexus.compiler.javac.JavaxToolsCompiler.compileInProcess (JavaxToolsCompiler.java:173)
        at org.codehaus.plexus.compiler.javac.JavacCompiler.performCompile (JavacCompiler.java:174)
        at org.apache.maven.plugin.compiler.AbstractCompilerMojo.execute (AbstractCompilerMojo.java:1134)
        at org.apache.maven.plugin.compiler.CompilerMojo.execute (CompilerMojo.java:187)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
        at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
        at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
        at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
        at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke (Method.java:566)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
    Caused by: java.lang.RuntimeException: java.lang.annotation.AnnotationTypeMismatchException: Incorrectly typed data found for annotation element public abstract org.example.MySecondAnnotation org.example.MyFirstAnnotation.secondAnnotation() (Found data of type org.example.MySecondAnnotation)
        at com.sun.tools.javac.api.JavacTaskImpl.handleExceptions (JavacTaskImpl.java:163)
        at com.sun.tools.javac.api.JavacTaskImpl.doCall (JavacTaskImpl.java:100)
        at com.sun.tools.javac.api.JavacTaskImpl.call (JavacTaskImpl.java:94)
        at org.codehaus.plexus.compiler.javac.JavaxToolsCompiler.compileInProcess (JavaxToolsCompiler.java:126)
        at org.codehaus.plexus.compiler.javac.JavacCompiler.performCompile (JavacCompiler.java:174)
        at org.apache.maven.plugin.compiler.AbstractCompilerMojo.execute (AbstractCompilerMojo.java:1134)
        at org.apache.maven.plugin.compiler.CompilerMojo.execute (CompilerMojo.java:187)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
        at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
        at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
        at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
        at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke (Method.java:566)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
    Caused by: java.lang.annotation.AnnotationTypeMismatchException: Incorrectly typed data found for annotation element public abstract org.example.MySecondAnnotation org.example.MyFirstAnnotation.secondAnnotation() (Found data of type org.example.MySecondAnnotation)
        at com.sun.tools.javac.model.AnnotationProxyMaker$ValueVisitor$1AnnotationTypeMismatchExceptionProxy.generateException (AnnotationProxyMaker.java:270)
        at sun.reflect.annotation.AnnotationInvocationHandler.invoke (AnnotationInvocationHandler.java:86)
        at com.sun.proxy.$Proxy27.secondAnnotation (Unknown Source)
        at org.example.Processor.process (Processor.java:22)
        at com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor (JavacProcessingEnvironment.java:980)
        at com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs (JavacProcessingEnvironment.java:896)
        at com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run (JavacProcessingEnvironment.java:1222)
        at com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing (JavacProcessingEnvironment.java:1334)
        at com.sun.tools.javac.main.JavaCompiler.processAnnotations (JavaCompiler.java:1258)
        at com.sun.tools.javac.main.JavaCompiler.compile (JavaCompiler.java:936)
        at com.sun.tools.javac.api.JavacTaskImpl.lambda$doCall$0 (JavacTaskImpl.java:104)
        at com.sun.tools.javac.api.JavacTaskImpl.handleExceptions (JavacTaskImpl.java:147)
        at com.sun.tools.javac.api.JavacTaskImpl.doCall (JavacTaskImpl.java:100)
        at com.sun.tools.javac.api.JavacTaskImpl.call (JavacTaskImpl.java:94)
        at org.codehaus.plexus.compiler.javac.JavaxToolsCompiler.compileInProcess (JavaxToolsCompiler.java:126)
        at org.codehaus.plexus.compiler.javac.JavacCompiler.performCompile (JavacCompiler.java:174)
        at org.apache.maven.plugin.compiler.AbstractCompilerMojo.execute (AbstractCompilerMojo.java:1134)
        at org.apache.maven.plugin.compiler.CompilerMojo.execute (CompilerMojo.java:187)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
        at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
        at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
        at org.apache.maven.cli.MavenCli.execute (MavenCli.java:957)
        at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:289)
        at org.apache.maven.cli.MavenCli.main (MavenCli.java:193)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke (Method.java:566)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
   ```

2. Now uncomment one of the lines in the `org.example.AnnotatedClass` in the `annotated-class` module, i.e. either the line with the import or the line
   with the annotation parameter:

   ```java
   //import org.example.MySecondAnnotation;
   @MyFirstAnnotation(
           //secondAnnotation = @MySecondAnnotation
   )
   public class AnnotatedClass {
   }
   ```

   And run the build again:

   ``` 
      mvn clean install
   ```

   Compilation will pass.
