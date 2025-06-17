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
    ldc 5
    istore 0
    iconst_1
    istore 0
    iload 0
    ldc 6
    if_icmplt L0
    iconst_0
    goto L1
    L0:
    iconst_1
    L1:
    iload 0
    iand
    ifne L2
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 0
    invokevirtual java/io/PrintStream/print(I)V
    goto L4
    L2:
    new java/util/Scanner
    dup
    getstatic java/lang/System/in  Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    invokevirtual java/util/Scanner/nextInt()I
    istore 0
    L4:
    return
.end method

