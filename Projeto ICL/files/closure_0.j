.class closure_0
.super java/lang/Object
.implements closure_interface_int_int
.field public sl Lf1;
.method public <init>()V
	 aload_0
	 invokenonvirtual java/lang/Object/<init>()V
	 return
.end method
.method public apply(I)I
	.limit locals 5
	.limit stack 256
	new f2
	dup
	invokespecial f2/<init>()V
	dup
	aload_0
	getfield closure_0/sl Lf1;
	putfield f2/sl Lf1;
	dup
	iload 1
	putfield f2/x0 I
	astore 4
	aload 4 
	getfield f2/sl Lf1;
	getfield f1/sl Lf0;
	getfield f0/x0 Lref_I;
	aload 4 
	getfield f2/sl Lf1;
	getfield f1/sl Lf0;
	getfield f0/x0 Lref_I;
	getfield ref_I/v I
	aload 4 
	getfield f2/x0 I
	iadd
	putfield ref_I/v I
	ireturn
.end method
