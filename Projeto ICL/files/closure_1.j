.class closure_1
.super java/lang/Object
.implements closure_interface__int
.field public sl Lf0;
.method public <init>()V
	 aload_0
	 invokenonvirtual java/lang/Object/<init>()V
	 return
.end method
.method public apply()I
	.limit locals 5
	.limit stack 256
	new f2
	dup
	invokespecial f2/<init>()V
	dup
	aload_0
	getfield closure_1/sl Lf0;
	putfield f2/sl Lf0;
	astore 4
	;Assign compile
	;Apply compile
	;Id compile
	aload 4 
	getfield f2/sl Lf0;
	getfield f0/x0 Ljava/lang/Object;
	checkcast closure_interface_int_int_int
	sipush 8121
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/sl Lf0;
	getfield f0/x1 Lref_int;
	getfield ref_int/v I
	imul
	sipush 28411
	iadd
	sipush 181
	invokeinterface closure_interface_int_int_int/apply(II)I 3
	dup
	;Id compile
	aload 4 
	getfield f2/sl Lf0;
	getfield f0/x1 Lref_int;
	swap
	putfield ref_int/v I
	pop
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/sl Lf0;
	getfield f0/x1 Lref_int;
	getfield ref_int/v I
	ireturn
.end method
