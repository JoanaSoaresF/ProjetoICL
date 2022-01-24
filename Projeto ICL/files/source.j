.class public Main
.super java/lang/Object
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
.method public static main([Ljava/lang/String;)V
	;set limits used by this method
	.limit locals 10
	.limit stack 256
	aconst_null
	astore 4
	;START
	;-----Def compile-----
	new f0
	dup
	invokespecial f0/<init>()V
	dup
	aload 4
	putfield f0/sl Ljava/lang/Object;
	astore 4
	aload 4
	;-----Fun compile-----
	new closure_0
	dup
	invokespecial closure_0/<init>()V
	dup
	aload 4
	putfield closure_0/sl Lf0;
	putfield f0/x0 Ljava/lang/Object;
	aload 4
	;-----New compile-----
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 2
	putfield ref_int/v I
	putfield f0/x1 Lref_int;
	aload 4
	;-----Fun compile-----
	new closure_1
	dup
	invokespecial closure_1/<init>()V
	dup
	aload 4
	putfield closure_1/sl Lf0;
	putfield f0/x2 Ljava/lang/Object;
	aload 4
	;-----Fun compile-----
	new closure_2
	dup
	invokespecial closure_2/<init>()V
	dup
	aload 4
	putfield closure_2/sl Lf0;
	putfield f0/x3 Ljava/lang/Object;
	;-----Def compile-----
	new f4
	dup
	invokespecial f4/<init>()V
	dup
	aload 4
	putfield f4/sl Lf0;
	astore 4
	aload 4
	;-----New compile-----
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 0
	putfield ref_int/v I
	putfield f4/x0 Lref_int;
	aload 4
	;-----New compile-----
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 0
	putfield ref_int/v I
	putfield f4/x1 Lref_int;
	;-----While compile-----
	L0:
	;-----Deref compile-----
	;-----Id compile-----
	aload 4 
	getfield f4/x0 Lref_int;
	getfield ref_int/v I
	sipush 30000
	isub
	iflt L2
	sipush 0
	goto L3
	L2:
 sipush 1
	L3:
	ifeq L1
	;-----Def compile-----
	new f5
	dup
	invokespecial f5/<init>()V
	dup
	aload 4
	putfield f5/sl Lf4;
	astore 4
	aload 4
	;-----Apply compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/sl Lf0;
	getfield f0/x2 Ljava/lang/Object;
	checkcast closure_interface__int
	invokeinterface closure_interface__int/apply()I 1
	putfield f5/x0 I
	aload 4
	;-----Apply compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/sl Lf0;
	getfield f0/x2 Ljava/lang/Object;
	checkcast closure_interface__int
	invokeinterface closure_interface__int/apply()I 1
	putfield f5/x1 I
	;-----Apply compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/sl Lf0;
	getfield f0/x3 Ljava/lang/Object;
	checkcast closure_interface_int_int_bool
	;-----Id compile-----
	aload 4 
	getfield f5/x0 I
	;-----Id compile-----
	aload 4 
	getfield f5/x1 I
	invokeinterface closure_interface_int_int_bool/apply(II)I 3
	ifeq L4
	;-----Assign compile-----
	;-----Deref compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/x1 Lref_int;
	getfield ref_int/v I
	sipush 1
	iadd
	dup
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/x1 Lref_int;
	swap
	putfield ref_int/v I
	goto L5
	L4:
	;-----Deref compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/x1 Lref_int;
	getfield ref_int/v I
	L5:
	pop
	;-----Assign compile-----
	;-----Deref compile-----
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/x0 Lref_int;
	getfield ref_int/v I
	sipush 1
	iadd
	dup
	;-----Id compile-----
	aload 4 
	getfield f5/sl Lf4;
	getfield f4/x0 Lref_int;
	swap
	putfield ref_int/v I
	aload 4
	getfield f5/sl Lf4;
	astore 4
	pop
	goto L0
	L1:
	iconst_0
	pop
	sipush 4
	;-----Deref compile-----
	;-----Id compile-----
	aload 4 
	getfield f4/x1 Lref_int;
	getfield ref_int/v I
	imul
	sipush 100
	imul
	sipush 30000
	idiv
	dup
	getstatic java/lang/System/out Ljava/io/PrintStream;
	swap
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	aload 4
	getfield f4/sl Lf0;
	astore 4
	aload 4
	getfield f0/sl Ljava/lang/Object;
	astore 4
	; END
	return
.end method
