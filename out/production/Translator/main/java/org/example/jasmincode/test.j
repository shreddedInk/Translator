.class public Test
.super java/lang/Object

.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

.method public static main([Ljava/lang/String;)V
    .limit stack 2
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc "Hello World!"
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    return 
.end method

.method table()V
    DefaultLabel: 
    DefaultLabel: 
    Label1: 
    Label2: 
    lookupswitch
    3 : Label1
    5 : Label2
    default : DefaultLabel
    tableswitch 0
    Label1
    Label2
    default : DefaultLabel
.end method

