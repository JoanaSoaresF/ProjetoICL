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
;Def compile
new f0
dup
invokespecial f0/<init>()V
dup
aload 4
putfield f0/sl Ljava/lang/Object;
astore 4
aload 4
;Fun compile
new closure_0
dup
invokespecial closure_0/<init>()V
dup
aload 4
putfield closure_0/sl Lf0;
putfield f0/x0 Ljava/lang/Object;
;Apply compile
;Id compile
aload 4 
getfield f0/x0 Ljava/lang/Object;
checkcast closure_interface_int_int_int
sipush 10
sipush 0
invokeinterface closure_interface_int_int_int/apply(II)I 3
;Apply compile
;Id compile
aload 4 
getfield f0/x0 Ljava/lang/Object;
checkcast closure_interface_int_int_int
sipush 100
sipush 20
invokeinterface closure_interface_int_int_int/apply(II)I 3
iadd
aload 4
getfield f0/sl Ljava/lang/Object;
astore 4
dup
getstatic java/lang/System/out Ljava/io/PrintStream;
swap
invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
  ; END


       return
.end method

