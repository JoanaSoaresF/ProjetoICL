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
new f1
dup
invokespecial f1/<init>()V
dup
aload 4
putfield f1/sl Lf0;
astore 4
aload 4
new closure_0
dup
invokespecial closure_0/<init>()V
dup
aload 4
putfield closure_0/sl Lf1;
putfield f1/x0 Ljava/lang/Object;
aload 4 
getfield f1/x0 Ljava/lang/Object;
checkcast closure_interface_int_int
sipush 2
invokeinterface closure_interface_int_int/apply(I)I 2
aload 4 
getfield f1/x0 Ljava/lang/Object;
checkcast closure_interface_int_int
sipush 3
invokeinterface closure_interface_int_int/apply(I)I 2
aload 4 
getfield f1/x0 Ljava/lang/Object;
checkcast closure_interface_int_int
sipush 4
invokeinterface closure_interface_int_int/apply(I)I 2
aload 4 
getfield f1/sl Lf0;
getfield f0/x0 Lref_I;
getfield ref_I/v I
dup
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
aload 4
getfield f1/sl Lf0;
astore 4
aload 4
getfield f0/sl Ljava/lang/Object;
astore 4
  ; END

       ; convert to String;
       ;getstatic java/lang/System/out Ljava/io/PrintStream;
       ;swap
      ;invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println
       ;invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
       return
.end method

