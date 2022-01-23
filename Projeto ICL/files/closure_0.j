.class closure_0
.super java/lang/Object
.implements closure_interface_int_int_int
.field public sl Lf0;
.method public <init>()V
	 aload_0
	 invokenonvirtual java/lang/Object/<init>()V
	 return
.end method
.method public apply(II)I
	.limit locals 5
	.limit stack 256
	new f1
	dup
	invokespecial f1/<init>()V
	dup
	aload_0
	getfield closure_0/sl Lf0;
	putfield f1/sl Lf0;
	dup
	iload 1
	putfield f1/x0 I
	dup
	iload 2
	putfield f1/x1 I
	astore 4
	;Def compile
	new f2
	dup
	invokespecial f2/<init>()V
	dup
	aload 4
	putfield f2/sl Lf1;
	astore 4
	aload 4
	;New compile
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	;Id compile
	aload 4 
	getfield f2/sl Lf1;
	getfield f1/x1 I
	putfield ref_int/v I
	putfield f2/x0 Lref_int;
	aload 4
	;New compile
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	;Id compile
	aload 4 
	getfield f2/sl Lf1;
	getfield f1/x0 I
	putfield ref_int/v I
	putfield f2/x1 Lref_int;
	;While compile
	L0:
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/x1 Lref_int;
	getfield ref_int/v I
	sipush 0
	isub
	ifgt L2
	sipush 0
	goto L3
	L2:
 sipush 1
	L3:
	ifeq L1
	;Assign compile
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/x0 Lref_int;
	getfield ref_int/v I
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/x1 Lref_int;
	getfield ref_int/v I
	iadd
	dup
	;Id compile
	aload 4 
	getfield f2/x0 Lref_int;
	swap
	putfield ref_int/v I
	pop
	;Assign compile
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/x1 Lref_int;
	getfield ref_int/v I
	sipush 1
	isub
	dup
	;Id compile
	aload 4 
	getfield f2/x1 Lref_int;
	swap
	putfield ref_int/v I
	pop
	goto L0
	L1:
	iconst_0
	pop
	;Deref compile
	;Id compile
	aload 4 
	getfield f2/x0 Lref_int;
	getfield ref_int/v I
	aload 4
	getfield f2/sl Lf1;
	astore 4
	ireturn
.end method
