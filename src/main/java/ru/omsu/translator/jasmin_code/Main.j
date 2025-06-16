.class public Main
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
        ldc 10

    istore 0
        ldc 15

    istore 1
        iload 0
    ldc 2
    iadd
    iload 1
    imul

    istore 2
        return
    .end method
