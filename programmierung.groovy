apply plugin: 'java'

sourceSets.main.java.srcDir('.')
sourceSets.main.output.classesDir('klassen')

repositories { mavenCentral(); }

def java(String x) {
  def arguments = x.split(" ")
  def t = arguments.tail() as List
  new ByteArrayOutputStream().withStream { os ->
    javaexec {
      main = arguments.head()
      classpath = sourceSets.main.runtimeClasspath
      args = t
      standardOutput = os
    }
    os.toString();
  }
}

ext {
  java = this.&java
}
