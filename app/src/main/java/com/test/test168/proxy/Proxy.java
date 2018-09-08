package com.test.test168.proxy;

import java.io.IOException;

public class Proxy {
//    compile 'com.squareup:javapoet:1.11.1'
    public static Object newProxyInstance(Class clazz) throws IOException {
//        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
//                .addSuperinterface(clazz);
//
//        FieldSpec fieldSpec = FieldSpec.builder(clazz, "flyable", Modifier.PRIVATE).build();
//        typeSpecBuilder.addField(fieldSpec);
//
//        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
//                .addModifiers(Modifier.PUBLIC)
//                .addParameter(clazz, "flyable")
//                .addStatement("this.flyable = flyable")
//                .build();
//        typeSpecBuilder.addMethod(constructorMethodSpec);
//
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method method : methods) {
//            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
//                    .addModifiers(Modifier.PUBLIC)
//                    .addAnnotation(Override.class)
//                    .returns(method.getReturnType())
//                    .addStatement("long start = $T.currentTimeMillis()", System.class)
//                    .addCode("\n")
//                    .addStatement("this.flyable." + method.getName() + "()")
//                    .addCode("\n")
//                    .addStatement("long end = $T.currentTimeMillis()", System.class)
//                    .addStatement("$T.out.println(\"Fly Time =\" + (end - start))", System.class)
//                    .build();
//            typeSpecBuilder.addMethod(methodSpec);
//        }
//
//        JavaFile javaFile = JavaFile.builder("com.youngfeng.proxy", typeSpecBuilder.build()).build();
//        // 为了看的更清楚，我将源码文件生成到桌面
//        javaFile.writeTo(new File("/Users/ouyangfeng/Desktop/"));

        return null;
    }

    public static Object newProxyInstance(Class inf, InvocationHandler handler) throws Exception {
//        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
//                .addModifiers(Modifier.PUBLIC)
//                .addSuperinterface(inf);
//
//        FieldSpec fieldSpec = FieldSpec.builder(InvocationHandler.class, "handler", Modifier.PRIVATE).build();
//        typeSpecBuilder.addField(fieldSpec);
//
//        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
//                .addModifiers(Modifier.PUBLIC)
//                .addParameter(InvocationHandler.class, "handler")
//                .addStatement("this.handler = handler")
//                .build();
//
//        typeSpecBuilder.addMethod(constructorMethodSpec);
//
//        Method[] methods = inf.getDeclaredMethods();
//        for (Method method : methods) {
//            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
//                    .addModifiers(Modifier.PUBLIC)
//                    .addAnnotation(Override.class)
//                    .returns(method.getReturnType())
//                    .addCode("try {\n")
//                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class)
//                    // 为了简单起见，这里参数直接写死为空
//                    .addStatement("\tthis.handler.invoke(this, method, null)")
//                    .addCode("} catch(Exception e) {\n")
//                    .addCode("\te.printStackTrace();\n")
//                    .addCode("}\n")
//                    .build();
//            typeSpecBuilder.addMethod(methodSpec);
//        }
//
//        JavaFile javaFile = JavaFile.builder("com.youngfeng.proxy", typeSpecBuilder.build()).build();
//        // 为了看的更清楚，我将源码文件生成到桌面
//        String sourcePath = "/Users/ouyangfeng/Desktop/";
//        javaFile.writeTo(new File(sourcePath));
//
//        // 编译
//        JavaCompiler.compile(new File(sourcePath + "/com/youngfeng/proxy/TimeProxy.java"));
//
//        // 使用反射load到内存
//        URL[] urls = new URL[] {new URL("file:" + sourcePath)};
//        URLClassLoader classLoader = new URLClassLoader(urls);
//        Class clazz = classLoader.loadClass("com.youngfeng.proxy.TimeProxy");
//        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
//        Object obj = constructor.newInstance(handler);

//        return obj;
        return null;
    }
}