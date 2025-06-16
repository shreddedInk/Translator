.class public Main
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
        ldc 5

    istore 0
        ldc 6

    istore 0
        ldc 1

    ifeq ELSE_1
    null
    goto ENDIF_1
    ELSE_1:
    null
    ENDIF_1:
        return
    .end method
