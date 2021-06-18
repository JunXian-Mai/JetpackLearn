package org.markensic.learn.jetpack.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Markensic implements Plugin<Project> {

  @Override
  void apply(Project project) {
    def ext = project.extensions.create('markensic', MarkensicExtension)


    project.afterEvaluate {
      println "-------------"
      println "Hello ${ext.name}!"
      println "This is a gradle plugin!"
    }

//    def transform = new MarkensicTransform()
//    def baseExtension = project.extensions.getByType(BaseExtension)
//    baseExtension.registerTransform(transform)
  }
}