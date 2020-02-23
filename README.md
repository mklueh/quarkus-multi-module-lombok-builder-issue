# Quarkus multi-module Lombok @Builder issue

This project is not functional and is used to describe Issue https://github.com/quarkusio/quarkus/issues/7367


#### Usage

Use the **run.sh** script instead of **:application:quarkusBuild** to build and run the application because of 
another multi-module project related issue https://github.com/quarkusio/quarkus/issues/7365


##### Issue

`Caused by: java.lang.ClassNotFoundException: com.demo.common.model.SharedModel$SharedModelBuilder
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:583)
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:521)
        ... 22 more
`

##### Cause

The @Builder annotation in **ApplicationSpecificModel**, that is located in the
 **application module** is creating an inner builder class as expected(decompiled with JD-GUI)



  
    public static class ApplicationSpecificModelBuilder {
       private String name;
    
       private LocalDateTime createdAt;
    
       public ApplicationSpecificModelBuilder name(String name) {
         this.name = name;
         return this;
       }
    
       public ApplicationSpecificModelBuilder createdAt(LocalDateTime createdAt) {
         this.createdAt = createdAt;
         return this;
       }
    
       public ApplicationSpecificModel build() {
         return new ApplicationSpecificModel(this.name, this.createdAt);
      }
    
      public String toString() {
        return "ApplicationSpecificModel.ApplicationSpecificModelBuilder(name=" + this.name + ", createdAt=" + this.createdAt + ")";
      }
    
    }
    
While the @Builder annotation in the **library module** however is not working as expected. the inner builder class is
missing there.
