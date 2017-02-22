package org.debian.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.invocation.Gradle;

public class DebianAndroidPlugin implements Plugin<Gradle> {

  @Override
  public void apply(Gradle gradle) {
    gradle.rootProject(rootProject -> {
      rootProject.getBuildscript().getRepositories().addFirst(
          rootProject.getRepositories().maven(repo ->
              repo.setUrl("file:///usr/share/maven-repo")
          )
      );
      rootProject.getBuildscript()
                 .getConfigurations()
                 .getByName("classpath")
                 .getResolutionStrategy()
                 .eachDependency(details -> {
        if (details.getRequested().getGroup().equals("com.android.tools.build") &&
            details.getRequested().getName().equals("gradle")) {
          details.useVersion("debian");
        }
      });
    });

  }
}