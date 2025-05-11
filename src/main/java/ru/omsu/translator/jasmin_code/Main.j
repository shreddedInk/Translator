.class public Main
.super java/lang/Object

.field static x I

.method public static main([Ljava/lang/String;)V
.limit stack 10
.limit locals 10

; read(x)
new java/util/Scanner
dup
getstatic java/lang/System/in Ljava/io/InputStream;
invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
invokevirtual java/util/Scanner/nextInt()I
putstatic Main/x I

; write(x)
getstatic java/lang/System/out Ljava/io/PrintStream;
getstatic Main/x I
invokevirtual java/io/PrintStream/println(I)V

return
.end method
