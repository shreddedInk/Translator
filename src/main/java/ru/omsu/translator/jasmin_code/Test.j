.class public Test
.super java/lang/Object

.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

.method public static main([Ljava/lang/String;)V
    .limit stack 100
    .limit locals 100
    iconst_1
    istore 0
    iconst_0
    istore 0
    ldc 10
    ldc 10
    imul
    istore 0
    iload 0
    ldc 10
    if_icmpeq L0
    iconst_0
    goto L1
    L0:
    iconst_1
    L1:
    iload 0
    iconst_1
    if_icmpeq L2
    iconst_0
    goto L3
    L2:
    iconst_1
    L3:
    ior
    ifne L8
    iload 0
    ldc 5
    if_icmpne L4
    iconst_0
    goto L5
    L4:
    iconst_1
    L5:
    ifne L6
    goto L7
    L6:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 0
    invokevirtual java/io/PrintStream/print(I)V
    L7:
    goto L10
    L8:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 0
    invokevirtual java/io/PrintStream/print(I)V
    L10:
    return
.end method

