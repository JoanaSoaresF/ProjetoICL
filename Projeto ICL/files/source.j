.class public Main
.super java/lang/Object
;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 10
       .limit stack 256

       ;    1 - the PrintStream object held in java.lang.System.out


       ; place your bytecodes here between START and END
       ; START

aconst_null
astore 4
new f0
dup
invokespecial f0/<init>()V
dup
aload 4
putfield f0/sl Ljava/lang/Object;
astore 4
aload 4
new ref_I
dup
invokespecial ref_I/<init>()V
dup
sipush 0
putfield ref_I/v I
putfield f0/x0 Lref_I;
aload 4
new ref_I
dup
invokespecial ref_I/<init>()V
dup
sipush 10
putfield ref_I/v I
putfield f0/x1 Lref_I;
L0:
aload 4 
getfield f0/x1 Lref_I;
getfield ref_I/v I
sipush 0
isub
ifgt L2
sipush 0
goto L3
L2:
 sipush 1
L3:
ifeq L1
aload 4 
getfield f0/x0 Lref_I;
aload 4 
getfield f0/x0 Lref_I;
getfield ref_I/v I
aload 4 
getfield f0/x1 Lref_I;
getfield ref_I/v I
iadd
putfield ref_I/v I
aload 4 
getfield f0/x1 Lref_I;
aload 4 
getfield f0/x1 Lref_I;
getfield ref_I/v I
sipush 1
isub
putfield ref_I/v I
goto L0
L1:
aload 4 
getfield f0/x0 Lref_I;
getfield ref_I/v I
dup
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
aload 4
getfield f0/sl Ljava/lang/Object;
astore 4
  ; END

       ; convert to String;
       getstatic java/lang/System/out Ljava/io/PrintStream;
       swap
      invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
       return
.end method

